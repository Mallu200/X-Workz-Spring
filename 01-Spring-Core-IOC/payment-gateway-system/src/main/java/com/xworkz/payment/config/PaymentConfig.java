package com.xworkz.payment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Defines this class as a configuration blueprint for the Spring IoC container
@Configuration
@ComponentScan("com.xworkz.payment")
public class PaymentConfig {
    // Automatically scans the specified package to discover and register all @Component beans
}