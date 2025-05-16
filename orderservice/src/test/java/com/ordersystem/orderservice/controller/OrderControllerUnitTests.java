package com.ordersystem.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Test case 1: Test Successful Order Registration
// Test case 2: Test Order Registration with Existing product
// Test case 3: Test Order Registration with Invalid Input
// Test case 4: Test Exception Handling

@WebMvcTest(OrderController.class)
public class OrderControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    Order order;

    @BeforeEach
    public void setup(){

         order = Order.builder()
                .id(1L)
                .product("product 1")
                .quantity(12)
                .price(25.50)
                .build();

    }

    //Post Controller
    @Test
    public void saveOrderTest() throws Exception{
        // precondition
        given(orderService.createOrder(any(Order.class))).willReturn(order);

        // action
        ResultActions response = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.product",
                        is(order.getProduct())))
                .andExpect(jsonPath("$.quantity",
                        is(order.getQuantity())))
                .andExpect(jsonPath("$.price",
                        is(order.getPrice())));
    }

    //Get Controller
    @Test
    public void getOrderTest() throws Exception{
        // precondition
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);
        ordersList.add(
                Order.builder()
                .id(2L)
                .product("product 2L")
                .quantity(12)
                .price(25.50)
                .build());
        given(orderService.getAllOrders()).willReturn(ordersList);

        // action
        ResultActions response = mockMvc.perform(get("/api/orders"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(ordersList.size())));

    }

    //get by Id controller
    @Test
    public void getByIdOrderTest() throws Exception{
        // precondition
        given(orderService.getOrderById(order.getId())).willReturn(Optional.of(order));

        // action
        ResultActions response = mockMvc.perform(get("/api/orders/{id}", order.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.product", is(order.getProduct())))
                .andExpect(jsonPath("$.quantity", is(order.getQuantity())))
                .andExpect(jsonPath("$.price", is(order.getPrice())));

    }

    // delete order
    @Test
    public void deleteOrderTest() throws Exception{
        // precondition
        willDoNothing().given(orderService).deleteOrder(order.getId());

        // action
        ResultActions response = mockMvc.perform(delete("/api/orders/{id}", order.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}