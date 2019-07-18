package com.thoughtworks.parking_lot.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.OrderController;
import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    void setup(){mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); }

    @Test
    public void should_create_and_return_order() throws Exception{
        ParkOrder order = new ParkOrder("lot1", "A380", new Date().getTime(), "open");

        when(orderService.saveOrder(any(), any(), any())).thenReturn(order);

        ResultActions resultActions = mockMvc.perform(post("/orders?lotName=lot1&carNo=A380").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(order)));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.carNo",is("A380")));
    }
}
