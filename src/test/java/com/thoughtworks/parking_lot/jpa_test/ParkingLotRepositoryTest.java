package com.thoughtworks.parking_lot.jpa_test;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
