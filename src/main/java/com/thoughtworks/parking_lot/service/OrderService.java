package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order saveOrder(Order order);
}
