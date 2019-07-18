package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ParkOrder createOrder(@RequestBody ParkOrder order){
        return orderService.saveOrder(order);
    }

}
