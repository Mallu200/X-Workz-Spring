package com.xworkz.stock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Configures the core application context classes for Backend, Database, and Web layers.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("09-INIT: Loading Root Configuration classes...");
        try {
            Class<?>[] configs = new Class[] { AppConfig.class, DbConfig.class, WebConfig.class };
            System.out.println("10-INIT: Root Configs (App, Db, Web) successfully registered.");
            return configs;
        } catch (Exception e) {
            // Catches missing class definitions or classpath issues during startup
            System.err.println("11-INIT: Critical failure loading Root Config: " + e.getMessage());
            return null;
        }
    }

    // Returns null as all configurations are handled by the Root Application Context.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        try {
            System.out.println("12-INIT: Checking for additional Servlet-specific configurations...");
            return null;
        } catch (Exception e) {
            System.err.println("13-INIT: Error in Servlet Config phase: " + e.getMessage());
            return null;
        }
    }

    // Maps the DispatcherServlet to the root URL pattern for handling all incoming requests.
    @Override
    protected String[] getServletMappings() {
        System.out.println("14-INIT: Mapping DispatcherServlet to '/' pattern.");
        try {
            return new String[] { "/" };
        } catch (Exception e) {
            // Ensures mapping failures are visible in the console
            System.err.println("15-INIT: Failed to map Servlet: " + e.getMessage());
            return new String[] {};
        }
    }
}