package com.xworkz.banking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// Marks this as a Spring Configuration class for AOP-related beans
@Configuration
// Scans the package to register @Component, @Service, and @Aspect beans
@ComponentScan("com.xworkz.banking")
// MANDATORY: Enables Spring's ability to handle @AspectJ and create Proxy objects
@EnableAspectJAutoProxy
public class AppConfig {
    // This class is used to bootstrap the Banking Application with Logging and Security layers
}