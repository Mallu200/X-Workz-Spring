package com.xworkz.hr.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration // Marks this class as the Spring configuration source
@ComponentScan("com.xworkz.hr") // Automatically detects beans in the HR package
@EnableTransactionManagement // Enables @Transactional for safe data operations
public class HRConfig {

    @Bean // Defines the physical connection details for MySQL
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_db"); // Shared database for all X-Workz projects
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3"); // Your local MySQL credentials
        return ds;
    }

    @Bean // Integrates Hibernate with Spring to manage HR Entities
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(ds);
        sf.setPackagesToScan("com.xworkz.hr.entity"); // Scans for @Entity classes like Employee

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Optimizes SQL for MySQL 8
        props.put("hibernate.show_sql", "true"); // Displays generated SQL in the console for debugging
        props.put("hibernate.hbm2ddl.auto", "update"); // Auto-creates or updates HR tables in MySQL
        sf.setHibernateProperties(props);
        return sf;
    }

    @Bean // Manages the lifecycle of database transactions for HR tasks
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sf) {
        return new HibernateTransactionManager(sf.getObject());
    }
}