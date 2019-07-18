package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotRepository repository;

    @PostMapping("/parkinglots")
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot){
        return repository.save(parkingLot);
    }

}
