package com.xworkz.db.runner;

import com.xworkz.db.bean.MySQLDriver;
import com.xworkz.db.config.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The Runner class responsible for bootstrapping the Spring Database module
public class DatabaseRunner {
    public static void main(String[] args) {

        // 1. Initialize Container: Scans the com.xworkz.db package via DatabaseConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);

        // 2. Fetch the Bean: Retrieves the fully initialized MySQLDriver from the context
        // Spring has already performed @Value injection and @PostConstruct before this line
        MySQLDriver mySQLDriver = context.getBean(MySQLDriver.class);

        // 3. Execution: Triggers the connection logic
        mySQLDriver.connect();

        // 4. Close Context: Shuts down the container and releases resources
        context.close();
    }
}