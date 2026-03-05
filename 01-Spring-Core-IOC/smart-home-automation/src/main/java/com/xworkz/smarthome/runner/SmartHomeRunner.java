package com.xworkz.smarthome.runner;

import com.xworkz.smarthome.bean.HomeHub;
import com.xworkz.smarthome.config.SmartHomeConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The Runner class responsible for bootstrapping the SmartHome Spring Application
public class SmartHomeRunner {
    public static void main(String[] args) {

        // 1. Initialize Container: Loads Configuration and performs Component Scanning
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SmartHomeConfig.class);

        // 2. Request the Singleton Hub: Spring resolves all dependencies (Philips/Samsung)
        HomeHub hub = context.getBean(HomeHub.class);

        // 3. Business Logic: Executes the command using injected SpEL values and the active device
        hub.executeCommand();

        // 4. Close the context: Triggers @PreDestroy for all Singleton beans (Philips/Samsung)
        context.close();
    }
}