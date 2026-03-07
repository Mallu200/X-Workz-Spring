package com.xworkz.shop.repository;

import com.xworkz.shop.entity.OrderEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// 1. Marks this as a Data Access Object (DAO) within the Spring Context
@Repository
public class ShopRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * TOPIC 1: Hibernate ORM
     * Handles the 'OrderEntity' object.
     * No SQL needed—Hibernate generates the INSERT statement automatically.
     */
    public void saveOrder(OrderEntity order) {
        // Retrieves the session bound to the current Transaction
        sessionFactory.getCurrentSession().save(order);
    }

    /**
     * TOPIC 2: Spring JdbcTemplate
     * Best for simple, direct updates or complex joins where ORM overhead is unnecessary.
     * Manages all JDBC boilerplate (Open/Close Connection, Statements).
     */
    public void updateStock(String product) {
        // Direct SQL update to the 'inventory' table in MySQL
        String sql = "UPDATE inventory SET stock = stock - 1 WHERE item_name = ?";
        jdbcTemplate.update(sql, product);
    }
}