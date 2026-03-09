package com.xworkz.autocare.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration // Defines this class as a source of bean definitions
@EnableTransactionManagement // Enables @Transactional support for JPA
public class DbConfig {

    public DbConfig() {
        // Confirms that the AutoCare Database Config is being loaded
        System.out.println("Created DbConfig for AutoCare: Initializing JPA Configuration.");
    }

    @Bean // Configures the connection pool for MySQL
    public DataSource dataSource() {
        System.out.println("DbConfig: Initializing DataSource...");

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db?createDatabaseIfNotExist=true");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3");

        System.out.println("DbConfig: DataSource URL set to: " + ds.getUrl());
        return ds;
    }

    @Bean // Sets up the JPA EntityManagerFactory (Replacement for Hibernate SessionFactory)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("DbConfig: Creating EntityManagerFactory Bean...");

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); // Links the MySQL connection
        em.setPackagesToScan("com.xworkz.autocare.entity"); // Scans for @Entity classes in autocare

        // Hibernate is used as the JPA provider
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Dialect for MySQL 8
        props.put("hibernate.show_sql", "true"); // Prints SQL queries to console
        props.put("hibernate.hbm2ddl.auto", "update"); // Automatically creates/updates vehicle_table

        em.setJpaProperties(props);
        System.out.println("DbConfig: JPA Properties set (Dialect: MySQL8, HBM2DDL: update)");
        return em;
    }

    @Bean // Manages global transactions for the EntityManager
    public PlatformTransactionManager transactionManager() {
        System.out.println("DbConfig: Initializing JpaTransactionManager...");

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        System.out.println("DbConfig: JpaTransactionManager successfully linked to EntityManagerFactory.");
        return transactionManager;
    }
}