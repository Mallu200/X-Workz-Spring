package com.xworkz.payment.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Marks the class as a Spring Component and ensures a new object is created for every user request
@Component
@Scope("prototype")
public class OrderService {

    // Reference to the PaymentGateway interface to support multiple providers (GPay, PhonePe)
    private PaymentGateway gateway;

    // Uses SpEL to generate a random order ID by calling the static Math.random() method
    @Value("#{T(java.lang.Math).random() * 1000}")
    private int orderId;

    // Uses SpEL to perform a simple addition to set the default discount percentage
    @Value("#{10 + 5}")
    private int discount;

    // Mandates the injection of the 'phonepe' bean during the initial construction phase
    public OrderService(@Qualifier("phonepe") PaymentGateway gateway) {
        this.gateway = gateway;
    }

    // Automatically overrides the initial gateway with 'gpay' once the bean properties are set
    @Autowired
    public void setGateway(@Qualifier("gpay") PaymentGateway gateway) {
        this.gateway = gateway;
    }

    // Displays order details and delegates the transaction processing to the final injected gateway
    public void placeOrder(double amount) {
        System.out.println("Order ID: " + orderId + " | Discount: " + discount + "%");
        gateway.processPayment(amount);
    }
}