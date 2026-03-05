package com.xworkz.smarthome.bean;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Registers this class as a Spring-managed bean with the unique ID "philips"
@Component("philips")
public class PhilipsLight implements SmartDevice {

    // Automatically runs after the bean is created to simulate a Wi-Fi connection handshake
    @PostConstruct
    public void init() {
        System.out.println("Philips: Connecting to Wi-Fi... ");
    }

    // Implementation of the SmartDevice contract to activate lighting logic
    public void turnOn() {
        System.out.println("Philips Light: Brightness set to 100%.");
    }

    // Automatically runs before the bean is destroyed to safely release network resources
    @PreDestroy
    public void cleanup() {
        System.out.println("Philips: Disconnecting from Wi-Fi...");
    }
}