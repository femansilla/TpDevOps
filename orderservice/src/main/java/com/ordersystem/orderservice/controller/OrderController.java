package com.ordersystem.orderservice.controller;

import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.service.OrderService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Spring annotation to indicate that this class is a REST controller
@RestController
// Base mapping for all request mappings in this controller
@RequestMapping("/api/orders")
public class OrderController {

    
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("id") long id){
        return new ResponseEntity<Optional<Order>>(orderService.getOrderById(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<String>("Order deleted successfully!.", HttpStatus.OK);

    }
}