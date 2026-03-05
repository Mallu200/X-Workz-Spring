package com.xworkz.smarthome.bean;

import org.springframework.stereotype.Component;

// Registers this class as a Spring-managed bean with the specific ID "samsung"
@Component("samsung")
public class SamsungAC implements SmartDevice {

    // Implementation of the SmartDevice contract to activate cooling mode
    public void turnOn() {
        System.out.println("Samsung AC: Cooling to 24°C...");
    }
}