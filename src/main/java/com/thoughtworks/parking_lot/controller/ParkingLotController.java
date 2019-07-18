package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotRepository repository;

    @PostMapping("/parkinglots")
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot){
        return repository.save(parkingLot);
    }

    @DeleteMapping("/parkinglots/{name}")
    public void deleteParkingLotByName(@PathVariable String name){
        repository.deleteParkingLotByName(name);
    }

    @GetMapping(value = "/parkinglots",params = {"page","pageSize"})
    public Page<ParkingLot> findParkinglotByPage(@RequestParam int page, @RequestParam int pageSize){

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ParkingLot> ParkingLotPage = repository.findAll(pageable);
        return ParkingLotPage;
    }

    @GetMapping("/parkinglots/{name}")
    public ParkingLot findParkingLotByName(@PathVariable String name){
        return repository.findParkingLotByName(name);
    }

    @PatchMapping("/parkinglots")
    public void updateParkingLotCapacity(@RequestParam String name,@RequestParam int capacity){
        repository.updateCapacityByName(name,capacity);
    }

}
