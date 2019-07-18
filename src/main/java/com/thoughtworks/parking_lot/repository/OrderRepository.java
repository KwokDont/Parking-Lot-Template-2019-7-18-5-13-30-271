package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
