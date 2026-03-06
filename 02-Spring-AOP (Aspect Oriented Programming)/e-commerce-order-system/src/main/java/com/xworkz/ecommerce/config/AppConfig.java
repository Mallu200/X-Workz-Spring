package com.xworkz.ecommerce.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// Marks this as the central Java-based configuration for the Ecommerce module
@Configuration
// Scans 'com.xworkz.ecommerce' to automatically register @Service, @Repository, and @Aspect
@ComponentScan("com.xworkz.ecommerce")
// MANDATORY: Tells Spring to scan for @Aspect beans and create the Proxy objects
// needed for @Before, @After, and @Around to work.
@EnableAspectJAutoProxy
public class AppConfig {
    // This class bootstraps the Inventory, Performance, and Order layers.
}