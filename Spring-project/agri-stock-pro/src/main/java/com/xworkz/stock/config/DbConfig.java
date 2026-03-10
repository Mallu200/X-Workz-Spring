package com.xworkz.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DbConfig {

    // Configures the physical connection to the MySQL database 'agri_stock_pro'.
    @Bean
    public DataSource dataSource() {
        System.out.println("16-DB: Attempting to initialize DataSource...");
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/agri_stock_pro");
            dataSource.setUsername("root");
            dataSource.setPassword("Mallu@2K3");
            System.out.println("17-DB: DataSource successfully connected to MySQL.");
            return dataSource;
        } catch (Exception e) {
            System.err.println("18-DB: Failed to create DataSource! Check MySQL service/credentials: " + e.getMessage());
            throw e;
        }
    }

    // Sets up the Hibernate EntityManager to manage Java Entities and DB communication.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("19-DB: Configuring LocalContainerEntityManagerFactoryBean...");
        try {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            em.setPackagesToScan("com.xworkz.stock.entity");
            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            em.setJpaProperties(hibernateProperties());
            System.out.println("20-DB: EntityManagerFactory initialized for 'com.xworkz.stock.entity'.");
            return em;
        } catch (Exception e) {
            System.err.println("21-DB: Error during EntityManagerFactory setup: " + e.getMessage());
            throw e;
        }
    }

    // Defines Hibernate behavior such as SQL dialect and automatic table updates.
    private Properties hibernateProperties() {
        System.out.println("22-DB: Loading Hibernate specific properties...");
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            return properties;
        } catch (Exception e) {
            System.err.println("23-DB: Error reading Hibernate properties: " + e.getMessage());
            return new Properties();
        }
    }

    // Manages database transactions to ensure data integrity during CRUD operations.
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println("24-DB: Initializing JpaTransactionManager...");
        try {
            JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
            System.out.println("25-DB: Transaction Manager is ready.");
            return transactionManager;
        } catch (Exception e) {
            System.err.println("26-DB: Transaction Manager initialization failed: " + e.getMessage());
            throw e;
        }
    }
}