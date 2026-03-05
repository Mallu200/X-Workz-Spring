package com.xworkz.notification.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// Marks the class as a Spring Component and creates a new object for every call to getBean()
@Component
@Scope("prototype")
public class AlertManager {

    // Reference to the NotificationProvider interface to support Email or SMS delivery
    private NotificationProvider provider;

    // SpEL: Dynamically retrieves the Operating System name from the host environment
    @Value("#{systemProperties['os.name']}")
    private String systemOS;

    // SpEL: Generates a unique 8-character identifier using Java's UUID utility
    @Value("#{T(java.util.UUID).randomUUID().toString().substring(0,8)}")
    private String alertId;

    // Injects the 'email' provider as the default notification channel during construction
    public AlertManager(@Qualifier("email") NotificationProvider provider) {
        this.provider = provider;
        // Note: systemOS might be null here because @Value is injected AFTER the constructor
        System.out.println("AlertManager: Instance created via Constructor Injection.");
    }

    // Overrides the initial provider with 'sms' once the bean properties are being populated
    @Autowired
    public void setProvider(@Qualifier("sms") NotificationProvider provider) {
        this.provider = provider;
        System.out.println("AlertManager: Switching to SMS Provider via Setter Injection.");
    }

    // Business method to display system info and route the message to the final provider
    public void triggerAlert(String text) {
        System.out.println("Alert ID: [" + alertId + "] on OS: " + systemOS + " - Processing...");
        provider.sendMessage(text);
    }
}