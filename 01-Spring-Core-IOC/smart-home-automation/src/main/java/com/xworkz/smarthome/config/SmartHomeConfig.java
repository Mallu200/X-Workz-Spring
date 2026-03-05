package com.xworkz.smarthome.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Defines this class as the central configuration blueprint for the SmartHome module
@Configuration
@ComponentScan("com.xworkz.smarthome")
public class SmartHomeConfig {
    // Automatically scans 'com.xworkz.smarthome' to discover and register all @Component beans
}