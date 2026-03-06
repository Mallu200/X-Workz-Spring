package com.xworkz.flight.service;

import org.springframework.stereotype.Service;

// Marks this as a Service Layer bean, where core flight booking rules are defined
@Service
public class FlightService {

    /**
     * The Target Method: Focuses strictly on the flight booking logic.
     * All @Aspects (Security, Audit) will wrap around this method.
     */
    public void bookTicket(String passengerName, String destination) {

        // Business logic: In a real app, this would update the 'tickets' table
        // and 'passengers' table in your MySQL database using JDBC or Hibernate.
        System.out.println("FLIGHT-SYSTEM: Confirming ticket for " + passengerName + " to " + destination + "...");

        // Note: No security or logging code is needed here because of AOP!
    }
}