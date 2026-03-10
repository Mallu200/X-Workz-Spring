package com.xworkz.stock;

import com.xworkz.stock.config.AppConfig;
import com.xworkz.stock.config.DbConfig;
import com.xworkz.stock.config.WebConfig;
import com.xworkz.stock.service.StockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
// Loads the complete application context to test the interaction between all layers.
@ContextConfiguration(classes = {AppConfig.class, DbConfig.class, WebConfig.class})
public class StockTest {

    @Autowired
    private StockService service;

    // Verifies that the Spring IoC container has successfully injected the StockService bean.
    @Test
    public void testServiceInjection() {
        System.out.println("109-TEST: Starting testServiceInjection...");
        try {
            Assert.assertNotNull("StockService should not be null", service);
            System.out.println("110-TEST: Dependency Injection successful. Service is ready.");
        } catch (AssertionError e) {
            System.err.println("111-TEST: Injection failure: " + e.getMessage());
            throw e;
        }
    }

    // Validates the integration between the Service, Repository, and MySQL Database.
    @Test
    public void testGetAllStock() {
        System.out.println("112-TEST: Starting testGetAllStock to check DB connectivity...");
        try {
            int count = service.getAllStock().size();
            Assert.assertNotNull(service.getAllStock());
            System.out.println("113-TEST: Database fetch successful. Records found: " + count);
        } catch (Exception e) {
            // Catches connection issues or mapping errors during the test run
            System.err.println("114-TEST: Data retrieval test failed: " + e.getMessage());
            Assert.fail("Database test failed due to exception: " + e.getMessage());
        }
    }
}