package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.exception.NonePositionException;
import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkOrder saveOrder(ParkOrder order, String parkingLotName, String carNo) throws NonePositionException {
        ParkingLot parkingLot = parkingLotRepository.findParkingLotByName(parkingLotName);
        if(parkingLot.getCapacity() == 0){
            throw new NonePositionException();
        }else {
            parkingLot.setCapacity(parkingLot.getCapacity() - 1);
            parkingLotRepository.save(parkingLot);
            ParkOrder order1 = new ParkOrder(parkingLotName,carNo,new Date().getTime(),"open");
            return orderRepository.save(order1);
        }
    }

    @Override
    public ParkOrder takeCar(ParkOrder order) {
        return null;
    }
}
