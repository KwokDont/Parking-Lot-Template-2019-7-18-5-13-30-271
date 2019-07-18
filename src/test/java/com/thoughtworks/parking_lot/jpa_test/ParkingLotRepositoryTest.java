package com.thoughtworks.parking_lot.jpa_test;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParkingLotRepositoryTest {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Test
    void should_return_parking_lot_when_insert_parking_lot(){
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");
        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);

        Assertions.assertEquals("lot1",parkingLot1.getName());
    }

    @Test
    void should_delete_parking_lot_when_given_name(){
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");
        ParkingLot parkingLot1 = parkingLotRepository.save(parkingLot);
        parkingLotRepository.deleteParkingLotByName("lot1");
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        Assertions.assertEquals("lot1",parkingLot1.getName());
        Assertions.assertEquals(0,parkingLots.size());
    }

    @Test
    void should_return_parking_lot_list_when_find_by_range(){
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");
        ParkingLot parkingLot1 = new ParkingLot("lot2",20,"zha");
        ParkingLot parkingLot2 = new ParkingLot("lot3",20,"zha");
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot,parkingLot1,parkingLot2);
        parkingLotRepository.saveAll(parkingLots);
        Pageable pageable = PageRequest.of(0, 2);

        Page<ParkingLot> parkingLotPage = parkingLotRepository.findAll(pageable);
        List<ParkingLot> parkingLots1 = parkingLotPage.getContent();

        Assertions.assertEquals(2,parkingLots1.size());
    }

    @Test
    void should_return_parking_lot_when_find_by_name(){
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");

        parkingLotRepository.save(parkingLot);
        ParkingLot parkingLot2 = parkingLotRepository.findParkingLotByName(parkingLot.getName());

        Assertions.assertEquals("lot1",parkingLot2.getName());
        Assertions.assertEquals(20,parkingLot2.getCapacity());
    }

    @Test
    void should_update_parking_lot_capacity(){
        ParkingLot parkingLot = new ParkingLot("lot1",20,"zha");

        ParkingLot parkingLot2 = parkingLotRepository.save(parkingLot);
        parkingLot2.setCapacity(25);
        parkingLotRepository.save(parkingLot2);
        ParkingLot parkingLot3 = parkingLotRepository.findParkingLotByName(parkingLot2.getName());

        Assertions.assertEquals(25,parkingLot3.getCapacity());
    }
}
