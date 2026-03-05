package com.xworkz.notification.bean;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// Registers this class as a Spring-managed bean identified by the unique ID "email"
@Component("email")
public class EmailProvider implements NotificationProvider {

    // Automatically executes after the bean is instantiated to establish the SMTP connection
    @PostConstruct
    public void init() {
        System.out.println("Email Server: Connecting to SMTP...");
    }

    // Overrides the sendMessage method to process and deliver email notifications
    public void sendMessage(String msg) {
        System.out.println("Email Sent: " + msg);
    }

    // Automatically executes during container shutdown to safely release server resources
    @PreDestroy
    public void cleanup() {
        System.out.println("Email Server: Disconnecting... ");
    }
}