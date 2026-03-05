package com.xworkz.smarthome.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Marks this as a Singleton bean; only one instance of the HomeHub exists in the container
@Component
@Scope("singleton")
public class HomeHub {

    // Interface reference allowing the hub to control any smart device (Light, AC, etc.)
    private SmartDevice device;

    // SpEL: Dynamically retrieves the Java Runtime Environment version from the system
    @Value("#{systemProperties['java.version']}")
    private String javaVer;

    // SpEL: Calculates a random room temperature between 0 and 30 at the time of bean creation
    @Value("#{T(java.lang.Math).random() * 30}")
    private double currentTemp;

    // Mandatory Constructor Injection: Initially pairs the hub with the "philips" device
    public HomeHub(@Qualifier("philips") SmartDevice device) {
        this.device = device;
        // Note: javaVer may be null here because @Value is injected after the constructor
        System.out.println("Hub: Initialized via Constructor Injection.");
    }

    // Optional Setter Injection: Overrides the initial device with the "samsung" device
    @Autowired
    public void setDevice(@Qualifier("samsung") SmartDevice device) {
        this.device = device;
        System.out.println("Hub: Switching control to Samsung AC via Setter Injection.");
    }

    // Business logic to display environment stats and activate the currently paired device
    public void executeCommand() {
        System.out.println("Runtime JRE: " + javaVer);
        System.out.println("Current Room Temp: " + String.format("%.2f", currentTemp) + "°C");
        device.turnOn();
    }
}