package com.xworkz.freshharvest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// This class acts as the entrance to the FreshHarvest application, replacing the traditional web.xml
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override // Configures the 'Root' context (Services, Repositories, and Database beans)
    protected Class<?>[] getRootConfigClasses() {
        // Trace to confirm that the FreshHarvest backend/data layer is being initialized
        System.out.println("AppInitializer: Loading Root Application Context (DbConfig) for FreshHarvest...");

        // Links the Database and Hibernate configuration classes to the Parent context
        return new Class[] { DbConfig.class };
    }

    @Override // Configures the 'Servlet' context (Web Controllers, ViewResolver, and UI Handlers)
    protected Class<?>[] getServletConfigClasses() {
        // Trace to confirm that the FreshHarvest web/MVC layer is being initialized
        System.out.println("AppInitializer: Loading Servlet Application Context (WebConfig) for FreshHarvest...");

        // Links the Spring MVC and ViewResolver configuration classes to the Child context
        return new Class[] { WebConfig.class };
    }

    @Override // Defines the URL pattern that the DispatcherServlet will intercept
    protected String[] getServletMappings() {
        // Trace to confirm that all incoming web traffic is being routed to the Spring container
        System.out.println("AppInitializer: Mapping DispatcherServlet to '/' for the FreshHarvest project.");

        // Directs every request coming to the server to be handled by Spring MVC
        return new String[] { "/" };
    }
}