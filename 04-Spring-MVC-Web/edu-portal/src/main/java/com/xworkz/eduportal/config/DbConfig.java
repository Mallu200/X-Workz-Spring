package com.xworkz.eduportal.config;

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
        // Trace to confirm the EduPortal Database Configuration is being loaded
        System.out.println("Created DbConfig for EduPortal: Initializing Hibernate & DB settings.");
    }

    @Bean // Configures the connection pool for communicating with the MySQL server
    public DataSource dataSource() {
        System.out.println("DbConfig: Initializing DataSource (BasicDataSource)..."); // DB connection trace

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Specifies the modern MySQL JDBC driver
        // Automatically creates the 'spring_db' schema if it doesn't already exist
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db?createDatabaseIfNotExist=true");
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3");

        System.out.println("DbConfig: DataSource established for URL: " + ds.getUrl());
        return ds;
    }

    @Bean // Sets up the Hibernate SessionFactory, the core engine for ORM
    public LocalSessionFactoryBean sessionFactory() {
        System.out.println("DbConfig: Creating LocalSessionFactoryBean..."); // Hibernate setup trace

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource()); // Links the MySQL connection pool
        sessionFactory.setPackagesToScan("com.xworkz.eduportal.entity"); // Scans for @Entity classes in EduPortal

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Ensures Hibernate speaks MySQL 8 SQL
        props.put("hibernate.show_sql", "true"); // Helpful for debugging: prints every SQL query to the console
        props.put("hibernate.hbm2ddl.auto", "update"); // Automatically syncs the database table with your Java Entity

        sessionFactory.setHibernateProperties(props);
        System.out.println("DbConfig: SessionFactory Properties set (MySQL8Dialect, show_sql=true, hbm2ddl=update)");
        return sessionFactory;
    }

    @Bean // Provides a manager to handle commit and rollback logic for Hibernate sessions
    public PlatformTransactionManager transactionManager() {
        System.out.println("DbConfig: Initializing HibernateTransactionManager..."); // Transaction management trace

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        // Retrieves the SessionFactory object from the factory bean to link the manager
        txManager.setSessionFactory(sessionFactory().getObject());

        System.out.println("DbConfig: Transaction Manager successfully linked to SessionFactory.");
        return txManager;
    }
}