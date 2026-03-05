package com.xworkz.mobile.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Marks the class as a Spring-managed component and ensures a new instance is created for every request
@Component
@Scope("prototype")
public class Mobile {

    // Reference to the Service interface to maintain loose coupling
    private Service service;

    // Evaluates a mathematical expression at runtime to set the signal strength
    @Value("#{2 + 3}")
    private int signalStrength;

    // Converts the string to uppercase using SpEL before assigning it to networkName
    @Value("#{'X-Workz-5G'.toUpperCase()}")
    private String networkName;

    // Injects the 'airtel' implementation of Service during the object creation phase
    public Mobile(@Qualifier("airtel") Service service) {
        this.service = service;
        System.out.println("SOP: Mobile instance initialized via Constructor Injection.");
    }

    // Overrides the existing service with the 'jio' implementation after the bean is constructed
    @Autowired
    public void setService(@Qualifier("jio") Service service) {
        this.service = service;
        System.out.println("SOP: Service dependency updated/overridden via Setter Injection.");
    }

    // Displays the current network status and calls the activation logic of the injected service
    public void start() {
        System.out.println("Status: Connected to " + networkName + " [Signal: " + signalStrength + "/5]");
        if (service != null) {
            service.activate();
        } else {
            System.out.println("Error: Service dependency not found.");
        }
    }
}