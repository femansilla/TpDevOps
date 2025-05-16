package com.ordersystem.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
// Annotates the class as a builder
@Builder
// Annotates the class as a JPA entity, allowing it to be mapped to a database table
@Entity
// Specifies the name of the database table to which this entity is mapped
@Table(name = "orders")
public class Order {
    @Id
    // Specifies the generation strategy for the primary key values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripcion del producto es requerida!")
    @Column(name = "product")
    private String product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    
    public Order(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    
}