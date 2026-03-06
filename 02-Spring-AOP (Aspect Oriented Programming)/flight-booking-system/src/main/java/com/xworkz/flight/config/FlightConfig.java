package com.xworkz.flight.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// Marks this as the Java-based configuration class for the Flight module
@Configuration
// Scans 'com.xworkz.flight' to automatically register @Service, @Repository, and @Aspect
@ComponentScan("com.xworkz.flight")
// MANDATORY: Enables Spring's ability to create Proxy objects for AOP.
// Without this, @Before, @After, and @Around annotations will not execute.
@EnableAspectJAutoProxy
public class FlightConfig {
    // This class bootstraps the FlightService, SecurityAspect, and Transaction layers.
}