package com.xworkz.wonderwand.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration // Marks this class as a configuration source for the Spring IoC container
@EnableTransactionManagement // Activates support for @Transactional-driven database operations
public class DbConfig {

    public DbConfig() {
        // Trace to confirm the WonderWand Database Configuration is being loaded during startup
        System.out.println("Created DbConfig: Initializing Hibernate & DB settings for WonderWand.");
    }

    @Bean // Configures the DBCP2 connection pool for MySQL communication
    public DataSource dataSource() {
        System.out.println("DbConfig: Initializing DataSource (BasicDataSource)..."); // DB connection trace

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Specifies the modern MySQL Connector/J driver
        // Automatically creates the schema if it doesn't exist; ideal for dev environments
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db?createDatabaseIfNotExist=true");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3");

        System.out.println("DbConfig: DataSource established for URL: " + ds.getUrl());
        return ds;
    }

    @Bean // Sets up the SessionFactory, the core engine for Hibernate ORM
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println("DbConfig: Creating LocalSessionFactoryBean..."); // Hibernate setup trace

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // Links the MySQL data source
        sessionFactory.setPackagesToScan("com.xworkz.wonderwand.entity"); // Scans for @Entity classes in WonderWand

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Optimizes SQL for MySQL 8
        props.put("hibernate.show_sql", "true"); // Prints generated SQL to the console for easier debugging
        props.put("hibernate.hbm2ddl.auto", "update"); // Automatically updates the table schema based on Java classes

        sessionFactory.setHibernateProperties(props);
        System.out.println("DbConfig: SessionFactory Properties set (Dialect, show_sql, hbm2ddl)");
        return sessionFactory;
    }

    @Bean // Manages the begin, commit, and rollback phases of database transactions
    public PlatformTransactionManager transactionManager() {
        System.out.println("DbConfig: Initializing HibernateTransactionManager..."); // Transaction trace

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        // Links the manager to the SessionFactory to handle Hibernate sessions
        txManager.setSessionFactory(sessionFactory().getObject());

        System.out.println("DbConfig: Transaction Manager successfully linked to SessionFactory.");
        return txManager;
    }
}