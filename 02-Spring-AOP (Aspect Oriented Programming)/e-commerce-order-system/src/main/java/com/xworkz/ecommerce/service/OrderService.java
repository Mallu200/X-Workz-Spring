package com.xworkz.ecommerce.service;

import org.springframework.stereotype.Service;

// Marks this as a Service Layer bean, where core business rules reside
@Service
public class OrderService {

    /**
     * The Target Method: Focuses strictly on the order placement logic.
     * AOP Aspects will automatically wrap around this method at runtime.
     */
    public String placeOrder(String item, int quantity) {
        // Business logic: In a real app, this would call a Repository to save to MySQL
        System.out.println("Processing: Placing order for " + quantity + " x " + item + "...");

        // Returns a dummy Order ID to the caller
        return "ORD12345";
    }
}