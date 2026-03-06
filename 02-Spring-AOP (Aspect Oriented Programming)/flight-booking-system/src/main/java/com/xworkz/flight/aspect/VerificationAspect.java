package com.xworkz.flight.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to manage security and notifications for the Flight module
@Aspect
@Component
public class VerificationAspect {

    // ADVICE: Runs BEFORE the ticket is confirmed in the database
    // POINTCUT: Targets the bookTicket method specifically
    @Before("execution(* com.xworkz.flight.service.FlightService.bookTicket(..))")
    public void verifyPassport() {
        // Pre-processing: Ensuring legal travel documents are valid before payment/booking
        System.out.println("AOP-SECURITY: Verifying Passport and Visa validity... 🛂");
    }

    // ADVICE: Runs AFTER the bookTicket method finishes its execution
    @After("execution(* com.xworkz.flight.service.FlightService.bookTicket(..))")
    public void sendSMS() {
        // Post-processing: Triggering external communication services
        System.out.println("AOP-NOTIFY: Booking confirmation sent via SMS to the registered mobile. 📲");
    }
}