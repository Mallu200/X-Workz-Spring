package com.xworkz.securevaultguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration // Defines this class as a bean definition source for the vault
@EnableTransactionManagement // Enables declarative transaction management (@Transactional)
public class DatabaseConfig {

    public DatabaseConfig() {
        // Trace to confirm the SecureVaultGuard persistence layer is initializing
        System.out.println("Created DatabaseConfig: Setting up JPA & MySQL for SecureVaultGuard.");
    }

    @Bean // Configures the JDBC connection to the spring_db schema
    public DataSource dataSource() {
        System.out.println("DatabaseConfig: Initializing DataSource...");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_db");
        dataSource.setUsername("root");
        dataSource.setPassword("Mallu@2K3");
        return dataSource;
    }

    @Bean // Sets up the EntityManagerFactory, the modern replacement for SessionFactory
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("DatabaseConfig: Creating LocalContainerEntityManagerFactoryBean...");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        // Scans the securevaultguard package for @Entity annotated classes
        em.setPackagesToScan("com.xworkz.securevaultguard.entity");

        // Adapts Hibernate to work seamlessly within the JPA specification
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean // Manages the lifecycle of vault transactions (Commit/Rollback)
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println("DatabaseConfig: Initializing JpaTransactionManager...");
        return new JpaTransactionManager(emf);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // Automatically updates the vault table schema to match your Java Entities
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        // Optimizes SQL generation for MySQL 8
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        // Prints the vault's SQL queries to the console for real-time audit debugging
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}