package com.xworkz.shop.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.xworkz.shop")
@EnableTransactionManagement // Enables the power of @Transactional for safe shopping
public class ShopConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jpa_db"); // Shared database with your other modules
        ds.setUsername("root");
        ds.setPassword("Mallu@2K3"); // Your consistent MySQL credentials
        return ds;
    }

    // Best for searching products quickly without the overhead of ORM
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // Maps your Product and Order entities to the MySQL tables
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(ds);
        sf.setPackagesToScan("com.xworkz.shop.entity");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto", "update"); // Auto-creates 'product_table'
        sf.setHibernateProperties(props);
        return sf;
    }

    // Ensures that 'Payment' and 'Inventory Update' happen as one single unit
    @Bean
    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sf) {
        return new HibernateTransactionManager(sf.getObject());
    }
}