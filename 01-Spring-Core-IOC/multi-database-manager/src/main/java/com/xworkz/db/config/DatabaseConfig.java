package com.xworkz.db.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Marks this class as the central configuration blueprint for the Database module
@Configuration
@ComponentScan(basePackages = "com.xworkz.db")
public class DatabaseConfig {
    // Automatically scans 'com.xworkz.db' to discover and register MySQLDriver and DataRepository
}