package com.xworkz.shop.service;

import com.xworkz.shop.entity.OrderEntity;
import com.xworkz.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private ShopRepository repository;

    /**
     * TOPIC: @Transactional (The Atomic Unit)
     * This creates a Proxy around the method.
     * Start: Opens a DB transaction.
     * End: Commits if success, Rollbacks if RuntimeException occurs.
     */
    @Transactional
    public void placeOrder(OrderEntity order) {

        // 1. Logic Layer: Save Order using Hibernate ORM
        repository.saveOrder(order);
        System.out.println("Order saved in DB...");

        // 2. Logic Layer: Update Stock using JdbcTemplate (Direct SQL)
        repository.updateStock(order.getProduct());

        // --- TEST CASE: Rollback Simulation ---
        // If the product name is "Out", we simulate a server crash or stock error.
        // Because of @Transactional, the 'Order' saved above will be DELETED from MySQL.
        if(order.getProduct().equalsIgnoreCase("Out")) {
            throw new RuntimeException("Simulated Failure: Order will rollback!");
        }

        System.out.println("Order Processed Successfully!");
    }
}