package com.xworkz.flight.runner;

import com.xworkz.flight.service.FlightService;
import com.xworkz.flight.config.FlightConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The entry point to test the decoupled Flight Logic and AOP Layers
public class FlightRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Initialize the Spring IoC Container
        // This triggers @ComponentScan and @EnableAspectJAutoProxy from FlightConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(FlightConfig.class);

        // 2. RETRIEVE: Get the PROXY version of FlightService
        // Spring provides a proxy that wraps the real FlightService with your @Aspects
        FlightService service = context.getBean(FlightService.class);

        // 3. EXECUTE: The "Magic" happens here.
        // Triggers: Passport Verify -> Tax Calc -> Seat Reservation -> Invoice Gen -> SMS Notification
        service.bookTicket("Mallikarjun", "Bengaluru to London");

        // 4. CLEANUP: Close the container to release resources
        context.close();
    }
}