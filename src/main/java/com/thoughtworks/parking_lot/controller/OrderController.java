package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/orders",params = {"lotName","carNo"})
    public ParkOrder createOrder(@RequestBody ParkOrder order, @RequestParam String lotName,@RequestParam String carNo){
        return orderService.saveOrder(order,lotName,carNo);
    }

}
