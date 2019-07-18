package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.exception.NonePositionException;
import com.thoughtworks.parking_lot.model.ParkOrder;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ParkOrder saveOrder(ParkOrder order, String parkingLotName, String carNo) throws NonePositionException;

    ParkOrder takeCar(ParkOrder order);
}
