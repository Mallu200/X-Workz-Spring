package com.xworkz.ecommerce.runner;

import com.xworkz.ecommerce.service.OrderService;
import com.xworkz.ecommerce.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The entry point to test the decoupled Business Logic and AOP Layers
public class EcommerceRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Initialize the Spring IoC Container
        // This triggers @ComponentScan and @EnableAspectJAutoProxy from AppConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. RETRIEVE: Get the PROXY version of OrderService
        // Spring provides a proxy that wraps the real OrderService with your @Aspects
        OrderService shop = context.getBean(OrderService.class);

        // 3. EXECUTE: The "Magic" happens here.
        // Even though we call one method, 5 separate logic blocks will execute!
        String orderID = shop.placeOrder("MacBook Pro", 1);

        System.out.println("Final Result: Order " + orderID + " is successful.");

        // 4. CLEANUP: Close the container to release resources
        context.close();
    }
}