package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.exception.NonePositionException;
import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders",params = {"lotName","carNo"})
    public ParkOrder createOrder(@RequestBody ParkOrder order, @RequestParam String lotName,@RequestParam String carNo){
        try {
            return orderService.saveOrder(order, lotName, carNo);
        } catch (NonePositionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/orders")
    public ParkOrder takeCar(@RequestBody ParkOrder order){
        return orderService.takeCar(order);
    }
}
