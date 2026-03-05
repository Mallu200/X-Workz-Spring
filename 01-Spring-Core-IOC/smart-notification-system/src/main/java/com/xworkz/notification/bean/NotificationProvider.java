package com.xworkz.notification.bean;

// Defines the contract for various communication channels (Email, SMS, WhatsApp)
public interface NotificationProvider {

    // Abstract method to handle the delivery logic for a text-based message
    void sendMessage(String message);
}