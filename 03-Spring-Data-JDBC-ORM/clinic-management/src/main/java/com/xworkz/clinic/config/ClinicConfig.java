package com.xworkz.clinic.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.xworkz.clinic")
@EnableTransactionManagement // MANDATORY: Enables the behavior of @Transactional
public class ClinicConfig {

    // 1. DATA SOURCE: The physical connection to your MySQL Server
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_db");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3"); // Your local credentials
        return ds;
    }

    // --- TOPIC 1: JdbcTemplate ---
    // Best for complex, performance-heavy queries
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // --- TOPIC 2: Hibernate Integration (ORM) ---
    // Best for CRUD operations on Patient and Appointment Entities
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan("com.xworkz.clinic.entity");

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", "true"); // Helpful for debugging in console
        props.put("hibernate.hbm2ddl.auto", "update"); // Auto-creates tables in MySQL
        sessionFactory.setHibernateProperties(props);
        return sessionFactory;
    }

    // --- TOPIC 3: Transaction Management ---
    // Ensures that if a booking fails, the database rolls back to a safe state
    @Bean
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sf) {
        return new HibernateTransactionManager(sf.getObject());
    }
}