package com.xworkz.mobile.bean;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Registers this class as a Spring Bean with the specific ID "airtel"
@Component("airtel")
public class Airtel implements Service {

    // Runs automatically after the bean is created and properties are injected
    @PostConstruct
    public void init() {
        System.out.println("Airtel: Connecting to Tower... (Init)");
    }

    // Implements the specific activation logic for the Airtel network
    @Override
    public void activate() {
        System.out.println("Airtel 5G is now Active!");
    }

    // Runs automatically just before the Spring container removes the bean
    @PreDestroy
    public void cleanup() {
        System.out.println("Airtel: Closing Connection... (Destroy)");
    }
}