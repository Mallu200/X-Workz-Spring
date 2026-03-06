package com.xworkz.banking.runner;

import com.xworkz.banking.config.AppConfig;
import com.xworkz.banking.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The entry point to test the decoupled Business Logic and AOP Layers
public class BankingRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Initialize the Spring IoC Container
        // This triggers @ComponentScan and @EnableAspectJAutoProxy
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. RETRIEVE: Get the PROXY version of BankService
        // Spring provides a proxy that wraps the real BankService with your @Aspects
        BankService bank = context.getBean(BankService.class);

        // 3. EXECUTE: The "Magic" happens here.
        // Even though we call one method, 5 separate logic blocks will execute!
        bank.transferMoney("Mallu200-Savings", 50000.0);

        // 4. CLEANUP: Close the container to release resources
        context.close();
    }
}