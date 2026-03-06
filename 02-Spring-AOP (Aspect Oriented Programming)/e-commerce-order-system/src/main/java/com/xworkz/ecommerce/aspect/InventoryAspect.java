package com.xworkz.ecommerce.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to manage cross-cutting concerns for the Ecommerce module
@Aspect
@Component
public class InventoryAspect {

    // ADVICE: Runs BEFORE the order is saved to the database
    // POINTCUT: Specifically targets the placeOrder method in your OrderService
    @Before("execution(* com.xworkz.ecommerce.service.OrderService.placeOrder(..))")
    public void checkStock() {
        // Pre-processing logic: Ensuring the item isn't sold out before we attempt a transaction
        System.out.println("AOP INVENTORY: Checking warehouse stock availability... 📦");
    }

    // ADVICE: Runs AFTER the order process finishes (Success or Failure)
    @After("execution(* com.xworkz.ecommerce.service.OrderService.placeOrder(..))")
    public void sendNotification() {
        // Post-processing logic: Triggering the communication layer
        System.out.println("AOP NOTIFY: Order confirmation email sent to customer. 📧");
    }
}