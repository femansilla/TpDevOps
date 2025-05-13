package com.ordersystem.orderservice.controller;

import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Test case 1: Test Successful Order Registration
// Test case 2: Test Order Registration with Existing product
// Test case 3: Test Order Registration with Invalid Input
// Test case 4: Test Exception Handling

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void registerOrderSuccess() throws Exception {
        // Prepare a valid Order request body
        String OrderJson = "{\"product\": \"Test\", \"quantity\": \"25\", \"price\": \"503\"}";

        // Mock orderService.createOrder to return a successful response
        when(orderService.createOrder(any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Success: product details successfully saved!"));

        // Perform POST request to /user/new with valid Order
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OrderJson))
                // Verify that the response status code is 201 create.
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Success: product details successfully saved!"));
    }

    @Test
    public void registerOrderWithAlreadyExistsOrderFail() throws Exception {
        // Prepare a valid Order request body
        String OrderJson = "{\"product\": \"Test\", \"quantity\": \"25\", \"price\": \"503\"}";

        // Mock orderService.createOrder to return a conflict response
        when(orderService.createOrder(any())).thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).body("Fail: product already exists!"));

        // Perform POST request to /user/new with valid Order
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OrderJson))
                // Verify that the response status code is conflict
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Fail: product already exists!"));
    }

    @Test
    public void registerOrderWithInternalServerErrorFail() throws Exception {
        // Prepare a valid Order request body
        String OrderJson = "{\"product\": \"Test\", \"quantity\": \"25\", \"price\": \"503\"}";

        // Mock orderService.createOrder to return a exception response
        when(orderService.createOrder(any())).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail: Failed to process request now. Try again later"));

        // Perform POST request to /user/new with valid Order
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                // Verify that the response status code is 500 Internal server error
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Fail: Failed to process request now. Try again later"));
    }

    @Test
    public void registerOrderWithInvalidInputFail() throws Exception {
        // Prepare an invalid Order request body with an no name and invalid quantity
        String OrderJson = "{\"product\": \"\", \"quantity\": \"abc\", \"price\": \"503\"}";

        // Perform a POST request to /orders with the invalid Order
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OrderJson))
                // Verify that the response status code is 400 Bad Request
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                // Verify that the response body is has correctly defined errors
                .andExpect(MockMvcResultMatchers.jsonPath("$.product").value("la descripcion del producto es requerida!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value("la cantidad no tiene el formato!"));

        // Verify that the OrderService's createOrder method is not called
        verify(orderService, times(0)).createOrder(any(Order.class));
    }
}