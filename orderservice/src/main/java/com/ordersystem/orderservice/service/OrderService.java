package com.ordersystem.orderservice.service;

import com.ordersystem.orderservice.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(long id);
    void deleteOrder(long id);
}