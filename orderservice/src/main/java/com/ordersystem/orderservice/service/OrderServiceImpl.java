package com.ordersystem.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
    
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {

        Optional<Order> savedOrder = orderRepository.findByProduct(order.getProduct());
        if(savedOrder.isPresent()){
            throw new RuntimeException("Order already exist:" + order.getProduct());
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

}
