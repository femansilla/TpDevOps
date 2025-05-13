package com.ordersystem.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Data
// Lombok annotation to generate an all-args constructor
@AllArgsConstructor
// Lombok annotation to generate a no-args constructor
@NoArgsConstructor
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
    @Size(max = 100, message = "La descripcion del producto debe ser de 100 characteres!")
    @Column(name = "description")
    private String product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "precio")
    private double price;
    
    public Order(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    
}