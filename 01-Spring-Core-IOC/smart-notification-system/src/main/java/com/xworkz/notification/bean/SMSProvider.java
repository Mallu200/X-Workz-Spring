package com.xworkz.notification.bean;

import org.springframework.stereotype.Component;

// Registers this class as a Spring-managed bean with the unique ID "sms"
@Component("sms")
public class SMSProvider implements NotificationProvider {

    // Overrides the sendMessage method to process and deliver SMS notifications
    public void sendMessage(String msg) {
        System.out.println("SMS Sent: " + msg + " [Standard Rates Apply]");
    }
}