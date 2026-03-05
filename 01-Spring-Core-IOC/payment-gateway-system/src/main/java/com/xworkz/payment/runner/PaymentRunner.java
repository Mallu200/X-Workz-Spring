package com.xworkz.payment.runner;

import com.xworkz.payment.bean.OrderService;
import com.xworkz.payment.config.PaymentConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Main entry point class to execute and test the Spring Payment module
public class PaymentRunner {
    public static void main(String[] args) {

        // Bootstraps the Spring Inversion of Control (IoC) container with the PaymentConfig class
        AnnotationConfigApplicationContext container =
                new AnnotationConfigApplicationContext(PaymentConfig.class);

        // Retrieves the first instance of OrderService; Spring performs all dependency injections automatically
        OrderService order1 = container.getBean(OrderService.class);
        order1.placeOrder(500.00);

        // Demonstrates Prototype Scope by requesting a second instance with a unique Order ID and state
        OrderService order2 = container.getBean(OrderService.class);
        order2.placeOrder(1200.00);

        // Closes the container to trigger @PreDestroy methods and release system resources
        container.close();
    }
}