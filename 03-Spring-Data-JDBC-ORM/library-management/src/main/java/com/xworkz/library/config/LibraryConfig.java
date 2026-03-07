package com.xworkz.library.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@ComponentScan("com.xworkz.library")
public class LibraryConfig {

    // 1. DATA SOURCE: The bridge to your MySQL instance
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_db");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3"); // Your consistent local credentials
        return ds;
    }

    // 2. JDBC TEMPLATE: The heart of Spring JDBC
    // It removes the need for 'try-catch-finally' blocks and 'Connection/Statement' boilerplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}