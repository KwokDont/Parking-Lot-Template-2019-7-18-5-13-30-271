package com.thoughtworks.parking_lot.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    void setup(){mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); }

    @Test
    void should_return_parking_lot_when_create_parking_lot() throws Exception{
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");

        when(parkingLotRepository.save(any())).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(post("/parkinglots").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(parkingLot)));

        resultActions.andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("lot1")));
    }

    @Test
    void should_return_parking_lot_when_delete_parking_lot_by_name() throws Exception{
        ResultActions resultActions = mockMvc.perform(delete("/parkinglots/{name}","lot1"));

        resultActions.andExpect(status().isOk());
        verify(parkingLotRepository).deleteParkingLotByName("lot1");
    }

    @Test
    void should_return_parking_lot_list_when_find_by_range() throws Exception{
        Pageable pageable = PageRequest.of(1, 20);
        ResultActions resultActions = mockMvc.perform(get("/parkinglots?page=1&pageSize=20"));

        resultActions.andExpect(status().isOk());
        verify(parkingLotRepository).findAll(pageable);
    }

    @Test
    void should_return_parking_lot_when_find_by_name() throws Exception{
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");

        when(parkingLotRepository.findParkingLotByName(any())).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(get("/parkinglots/{name}","lot1"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("lot1")));
    }

    @Test
    void should_update_capacity_of_specific_parking_lot() throws Exception{
        ParkingLot parkingLot = new ParkingLot("lot1",22,"sanfan");
        ResultActions resultActions = mockMvc.perform(patch("/parkinglots").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(parkingLot)));

        resultActions.andExpect(status().isOk());
        verify(parkingLotRepository).save(any());
    }

}
