package com.xworkz.bank.service;

import com.xworkz.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

// Marks this class as a Service component containing business logic for banking operations
@Service
public class BankService {

    // Automatically injects the BankRepository bean to perform database operations
    @Autowired
    private BankRepository repository;

    // SERIALIZABLE ensures highest data integrity by preventing concurrent access to the same records
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transferMoney(int fromAcc, int toAcc, double amount) {
        System.out.println("Service: Initiating transfer of ₹" + amount);

        // Fetches the current balance of the sender from the database
        double fromBalance = repository.getBalance(fromAcc);

        // Checks if the sender has enough funds before proceeding with the withdrawal
        if (fromBalance >= amount) {
            // Deducts the specified amount from the sender's account balance
            repository.updateBalance(fromAcc, fromBalance - amount);

            // Retrieves the current balance of the receiver to prepare for the deposit
            double toBalance = repository.getBalance(toAcc);

            // Adds the specified amount to the receiver's account balance
            repository.updateBalance(toAcc, toBalance + amount);

            System.out.println("Service: Transfer Completed Successfully.");
        } else {
            // Throws a RuntimeException to trigger an automatic rollback of all previous steps
            throw new RuntimeException("Error: Insufficient Funds. Transaction Rolled Back!");
        }
    }
}