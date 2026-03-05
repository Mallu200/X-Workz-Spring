package com.xworkz.mobile.runner;

import com.xworkz.mobile.bean.Mobile;
import com.xworkz.mobile.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Entry point class to launch and test the Spring-managed mobile application
public class MobileRunner {
    public static void main(String[] args) {

        // Initializes the Spring container using the specified configuration class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        // Requests a new instance of the Mobile bean from the prototype-scoped container
        Mobile myPhone = context.getBean(Mobile.class);

        // Triggers the business logic to display network status and service activation
        myPhone.start();

        // Gracefully shuts down the Spring container and releases managed resources
        context.close();
    }
}