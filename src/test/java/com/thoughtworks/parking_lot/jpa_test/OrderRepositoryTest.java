package com.thoughtworks.parking_lot.jpa_test;

import com.thoughtworks.parking_lot.model.ParkOrder;
import com.thoughtworks.parking_lot.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void should_save_and_return_order(){
        ParkOrder order = new ParkOrder("lot1", "A380", new Date().getTime(), "open");
        ParkOrder saveOrder = orderRepository.save(order);
        ParkOrder getOrder = orderRepository.findById(saveOrder.getId()).get();

        Assertions.assertEquals("A380",getOrder.getCarNo());
    }
}
