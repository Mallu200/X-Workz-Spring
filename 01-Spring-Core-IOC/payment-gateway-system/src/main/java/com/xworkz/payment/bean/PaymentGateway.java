package com.xworkz.payment.bean;

// Defines the standard contract for all payment processing implementations
public interface PaymentGateway {

    // Executes the transaction logic for a specified monetary value
    void processPayment(double amount);
}