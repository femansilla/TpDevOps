package com.ordersystem.orderservice.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;


    @BeforeEach
    public void setup(){

        order = Order.builder()
                .id(1L)
                .product("product 1")
                .quantity(12)
                .price(25.50)
                .build();

    }

    @Test
    public void saveOrderTest(){
        // precondition
        given(orderRepository.save(order)).willReturn(order);

        //action
        Order savedOrder = orderService.createOrder(order);

        // verify the output
        System.out.println(savedOrder);
        assertThat(savedOrder).isNotNull();
    }

    @Test
    public void getOrderById(){
        // precondition
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));

        // action
        Order existingOrder = orderService.getOrderById(order.getId()).get();

        // verify
        System.out.println(existingOrder);
        assertThat(existingOrder).isNotNull();

    }


    @Test
    public void getAllOrder(){
        Order order1 = Order.builder()
                .id(2L)
                .product("product 2L")
                .quantity(12)
                .price(25.50)
                .build();

        // precondition
        given(orderRepository.findAll()).willReturn(List.of(order,order1));

        // action
        List<Order> orderList = orderService.getAllOrders();

        // verify
        System.out.println(orderList);
        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isGreaterThan(1);
    }

    @Test
    public void deleteOrder(){

        // precondition
        willDoNothing().given(orderRepository).deleteById(order.getId());

        // action
        orderService.deleteOrder(order.getId());

        // verify
        verify(orderRepository, times(1)).deleteById(order.getId());
    }


}