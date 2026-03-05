package com.xworkz.mobile.bean;

import org.springframework.stereotype.Component;

// Registers this class as a Spring-managed bean with the unique ID "jio"
@Component("jio")
public class Jio implements Service {

    // Overrides the activate method to provide Jio-specific network logic
    @Override
    public void activate() {
        System.out.println("Jio 4G/5G is now Active!");
    }
}