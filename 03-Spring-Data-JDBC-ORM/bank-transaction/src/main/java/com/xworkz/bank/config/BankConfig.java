package com.xworkz.bank.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

// Marks this class as a source of bean definitions for the Spring container
@Configuration
// Specifies the base package to scan for @Component, @Service, and @Repository classes
@ComponentScan("com.xworkz.bank")
// Enables Spring's annotation-driven transaction management capability (required for @Transactional)
@EnableTransactionManagement
public class BankConfig {

    // Configures the database connection details using your local MySQL credentials
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_db");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3");
        return ds;
    }

    // Creates the JdbcTemplate bean to simplify SQL operations and handle resource management
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // Defines the transaction manager to coordinate commit and rollback operations for the DataSource
    @Bean
    public PlatformTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}