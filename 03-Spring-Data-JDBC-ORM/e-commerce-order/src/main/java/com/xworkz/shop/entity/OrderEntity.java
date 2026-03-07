package com.xworkz.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 1. Marks this class as a JPA Entity for Hibernate to manage
@Entity
// 2. Maps this entity to the 'orders' table in MySQL
@Table(name = "orders")
// 3. Lombok annotations to handle Boilerplate (Getters, Setters, Constructors)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    // 4. Primary Key with Auto-Increment strategy (MySQL compatible)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    // 5. Business fields for the shopping transaction
    private String product; // Name of the item (e.g., "Gaming Mouse")
    private double amount;  // Final price paid
}