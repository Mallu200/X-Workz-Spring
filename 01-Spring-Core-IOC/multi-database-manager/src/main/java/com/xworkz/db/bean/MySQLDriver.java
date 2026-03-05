package com.xworkz.db.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

// Registers this class as a Spring bean with the specific ID "mysql"
@Component("mysql")
public class MySQLDriver implements DatabaseDriver {

    // SpEL: Performs a mathematical calculation (5 * 1000) to set the timeout value in milliseconds
    @Value("#{5 * 1000}")
    private int timeout;

    // Runs automatically after the bean is created to simulate loading the JDBC driver
    @PostConstruct
    public void init() {
        System.out.println("MySQL: Driver loaded. Timeout set to: " + timeout + "ms");
    }

    // Implementation of the DatabaseDriver contract to establish a connection
    public void connect() {
        System.out.println("MySQL: Successfully connected to localDB. ");
    }
}