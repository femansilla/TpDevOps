package com.ordersystem.orderservice.repository;

import com.ordersystem.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByProduct(String product);
}