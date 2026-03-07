package com.xworkz.bank.runner;

import com.xworkz.bank.config.BankConfig;
import com.xworkz.bank.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The main entry point to execute and test the Banking Transaction module
public class BankRunner {
    public static void main(String[] args) {

        // 1. Initialize Container: Bootstraps the application using the Java-based configuration
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BankConfig.class);

        // 2. Fetch the Service: Retrieves the transactional BankService bean from the context
        BankService service = context.getBean(BankService.class);

        try {
            // 3. Execute Business Logic: Attempts to transfer ₹5000 from Account ID 1 to Account ID 2
            // Because of @Transactional, this either succeeds fully or fails fully
            service.transferMoney(1, 2, 5000.0);
            System.out.println("Main: Transaction process completed.");
        } catch (Exception e) {
            // 4. Handle Failures: Catches any RuntimeException (like Insufficient Funds) to show the error
            System.err.println("Main: Transaction halted. Reason: " + e.getMessage());
        }

        // 5. Close Context: Shuts down the Spring container and releases database resources
        context.close();
    }
}