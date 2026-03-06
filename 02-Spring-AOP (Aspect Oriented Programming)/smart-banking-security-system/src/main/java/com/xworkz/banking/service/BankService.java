package com.xworkz.banking.service;

import org.springframework.stereotype.Service;

// Marks this as a Service Layer bean, where business rules are defined
@Service
public class BankService {

    // The Core Business Logic (Target Method)
    public void transferMoney(String toAccount, double amount) {
        // Business logic only: No logging or security code here!
        System.out.println("Processing: Transferring ₹" + amount + " to " + toAccount);
    }
}