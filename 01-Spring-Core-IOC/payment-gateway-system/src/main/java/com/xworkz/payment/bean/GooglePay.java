package com.xworkz.payment.bean;

import org.springframework.stereotype.Component;

// Registers this class as a Spring-managed bean with the specific ID "gpay"
@Component("gpay")
public class GooglePay implements PaymentGateway {

    // Overrides the processPayment method to handle transactions through GooglePay
    public void processPayment(double amount) {
        System.out.println("Paid ₹" + amount + " via GooglePay.");
    }
}