package com.xworkz.db.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Marks this class as a Spring Component for data persistence logic
@Component
public class DataRepository {

    // Final reference ensures the dependency is immutable once injected
    private final DatabaseDriver driver;

    // CONSTRUCTOR INJECTION: Mandatory dependency on the "mysql" driver
    public DataRepository(@Qualifier("mysql") DatabaseDriver driver) {
        this.driver = driver;
        System.out.println("DataRepository: Initialized with MySQL Driver.");
    }

    // Business method to initiate the connection and perform the save operation
    public void saveData() {
        driver.connect();
        System.out.println("DataRepository: Saving record to Database...");
    }
}