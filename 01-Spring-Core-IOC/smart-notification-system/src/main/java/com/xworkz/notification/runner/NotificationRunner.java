package com.xworkz.notification.runner;

import com.xworkz.notification.bean.AlertManager;
import com.xworkz.notification.config.NotificationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The Runner class responsible for bootstrapping the Spring IoC Container
public class NotificationRunner {
    public static void main(String[] args) {

        // 1. Initialize Container: Scans @ComponentScan package and creates beans
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NotificationConfig.class);

        // 2. Get the bean: Spring resolves the @Qualifier and @Value (SpEL) logic
        // Since AlertManager is @Scope("prototype"), a fresh object is created here
        AlertManager user1Alert = context.getBean(AlertManager.class);
        user1Alert.triggerAlert("Your X-Workz-Spring project is live!");

        // 3. Close the context: Triggers @PreDestroy in Singleton beans (like EmailProvider)
        context.close();
    }
}