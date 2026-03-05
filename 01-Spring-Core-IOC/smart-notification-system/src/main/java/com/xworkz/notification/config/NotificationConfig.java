package com.xworkz.notification.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Marks this class as the central configuration for the Notification module
@Configuration
@ComponentScan("com.xworkz.notification")
public class NotificationConfig {
    // Spring will scan all sub-packages of 'com.xworkz.notification' to find @Component classes
}