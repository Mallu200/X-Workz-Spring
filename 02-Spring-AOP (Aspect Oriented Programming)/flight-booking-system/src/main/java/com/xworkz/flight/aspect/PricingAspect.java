package com.xworkz.flight.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to handle financial calculations (Pricing/Tax)
@Aspect
@Component
public class PricingAspect {

    // ADVICE: @Around wraps the method, allowing logic before AND after the call
    // JOINPOINT: ProceedingJoinPoint is required to trigger the actual booking
    @Around("execution(* com.xworkz.flight.service.FlightService.bookTicket(..))")
    public Object calculateFees(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. BEFORE Logic: Calculation
        System.out.println("AOP-PRICING: Calculating tax and convenience fees...");

        // --- TOPIC: Accessing Method Arguments ---
        // Capture the passengerName passed to the method
        Object[] args = joinPoint.getArgs();
        String name = (String) args[0];

        // 2. PROCEED: This line triggers the actual bookTicket() method in FlightService
        // Without this, the flight will NEVER be booked!
        Object result = joinPoint.proceed();

        // 3. AFTER Logic: Finalization
        System.out.println("AOP-PRICING: Final price generated for " + name + ". Total: ₹7500.");

        // Return the original result (or a modified receipt) back to the caller
        return result;
    }
}