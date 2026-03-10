package com.xworkz.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = "com.xworkz.stock")
public class AppConfig {

    // Configures a BCrypt bean for secure one-way password hashing across the application.
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("06-CONFIG: Initializing BCryptPasswordEncoder bean...");
        try {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println("07-CONFIG: PasswordEncoder bean successfully created.");
            return encoder;
        } catch (Exception e) {
            // Logs initialization failures such as library version mismatches
            System.err.println("08-CONFIG: Error during PasswordEncoder initialization: " + e.getMessage());
            throw new RuntimeException("Failed to initialize security beans", e);
        }
    }
}