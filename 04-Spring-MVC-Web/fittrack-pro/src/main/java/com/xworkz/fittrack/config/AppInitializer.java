package com.xworkz.fittrack.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Replaces web.xml for Java-based Spring configuration
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override // Specifies the configuration classes for the Root application context
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("AppInitializer: Loading Root Configuration (DbConfig)..."); // SOP for Backend/DB setup

        // Returns the Database and Hibernate configuration classes
        return new Class[] { DbConfig.class };
    }

    @Override // Specifies the configuration classes for the Servlet application context
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("AppInitializer: Loading Servlet Configuration (WebConfig)..."); // SOP for Web/MVC setup

        // Returns the Spring MVC and ViewResolver configuration classes
        return new Class[] { WebConfig.class };
    }

    @Override // Maps the DispatcherServlet to the root URL pattern
    protected String[] getServletMappings() {
        System.out.println("AppInitializer: Mapping DispatcherServlet to '/'"); // SOP for URL mapping

        // Directs all incoming web requests to the Spring DispatcherServlet
        return new String[] { "/" };
    }
}