package com.xworkz.mobile.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Marks this class as a source of bean definitions for the Spring ApplicationContext
@Configuration
@ComponentScan(basePackages = "com.xworkz.mobile")
public class SpringConfig {
    // Configures the container to scan the specified package for @Component classes
}