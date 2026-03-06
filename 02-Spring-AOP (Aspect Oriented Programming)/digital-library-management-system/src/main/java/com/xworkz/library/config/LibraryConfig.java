package com.xworkz.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// Marks this as the central Java-based configuration for the Library module
@Configuration
// Scans 'com.xworkz.library' to automatically register @Service, @Repository, and @Aspect
@ComponentScan("com.xworkz.library")
// MANDATORY: Enables Spring's ability to create Proxy objects for AOP.
// Without this, @Before, @After, and @Around annotations will be ignored.
@EnableAspectJAutoProxy
public class LibraryConfig {
    // This class bootstraps the BookService, AuditAspect, and Transaction layers.
}