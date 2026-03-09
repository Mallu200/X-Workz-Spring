package com.xworkz.fittrack.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement // Enables declarative transaction management using @Transactional
public class DbConfig {

    public DbConfig() {
        // Confirms that the Database Configuration class is loaded by Spring
        System.out.println("Created DbConfig: Database and Hibernate configuration initialized.");
    }

    @Bean // Configures the database connection pool using DBCP2
    public DataSource dataSource() {
        System.out.println("Initializing DataSource Bean..."); // Trace for DB connection start

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Specifies the MySQL JDBC driver
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db?createDatabaseIfNotExist=true"); // Connection URL
        ds.setUsername("root"); // Database user identity
        ds.setPassword("Mallu@2K3"); // Database access credential

        System.out.println("DataSource configured for URL: " + ds.getUrl());
        return ds;
    }

    @Bean // Integrates Hibernate with Spring and manages the SessionFactory
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println("Initializing SessionFactory Bean..."); // Trace for Hibernate setup

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // Links the data source to Hibernate
        sessionFactory.setPackagesToScan("com.xworkz.fittrack.entity"); // Scans for @Entity classes

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Sets SQL dialect for MySQL 8
        props.put("hibernate.show_sql", "true"); // Displays executed SQL in the console
        props.put("hibernate.hbm2ddl.auto", "update"); // Automatically syncs DB schema with entities

        sessionFactory.setHibernateProperties(props);
        System.out.println("SessionFactory Properties set: MySQL8Dialect, show_sql=true, hbm2ddl=update");
        return sessionFactory;
    }

    @Bean // Manages database transactions for the Hibernate session
    public PlatformTransactionManager transactionManager() {
        System.out.println("Initializing Hibernate Transaction Manager..."); // Trace for Tx management

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject()); // Links manager to the SessionFactory

        System.out.println("Transaction Manager successfully linked to SessionFactory.");
        return txManager;
    }
}