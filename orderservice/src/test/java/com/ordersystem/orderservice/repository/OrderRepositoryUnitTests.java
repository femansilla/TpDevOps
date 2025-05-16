package com.ordersystem.orderservice.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.ordersystem.orderservice.model.Order;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class OrderRepositoryUnitTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Test 1:Save Order Test")
    @Rollback(value = false)
    public void createOrderTest(){

        //Action
        Order order = Order.builder()
                .product("product 1")
                .quantity(12)
                .price(25.50)
                .build();

        orderRepository.save(order);

        //Verify
        System.out.println(order);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    public void getOrderTest(){

        //Action
        Order order = orderRepository.findById(1L).get();
        //Verify
        System.out.println(order);
        Assertions.assertThat(order.getId()).isEqualTo(1L);
    }

    @Test
    public void getListOfOrdersTest(){
        //Action
        List<Order> orders = orderRepository.findAll();
        //Verify
        System.out.println(orders);
        Assertions.assertThat(orders.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void deleteOrderTest(){
        //Action
        orderRepository.deleteById(1L);
        Optional<Order> orderOptional = orderRepository.findById(1L);

        //Verify
        Assertions.assertThat(orderOptional).isEmpty();
    }

}
