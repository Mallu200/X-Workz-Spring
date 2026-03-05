package com.xworkz.payment.bean;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Registers the class in the Spring Container with the unique identifier "phonepe"
@Component("phonepe")
public class PhonePe implements PaymentGateway {

    // Logic executed by the container immediately after dependency injection is complete
    @PostConstruct
    public void init() {
        System.out.println("PhonePe: Secure connection established.");
    }

    // Implementation of the payment contract to process the specified currency amount
    public void processPayment(double amount) {
        System.out.println("Paid ₹" + amount + " via PhonePe.");
    }

    // Logic executed by the container just before the bean is destroyed or context closed
    @PreDestroy
    public void cleanup() {
        System.out.println("PhonePe: Session closed safely.");
    }
}