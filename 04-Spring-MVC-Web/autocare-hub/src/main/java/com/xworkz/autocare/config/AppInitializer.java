package com.xworkz.autocare.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Entry point for the AutoCare application, replacing the traditional web.xml
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override // Specifies configuration for the Root Context (Backend/Service/DB)
    protected Class<?>[] getRootConfigClasses() {
        // Trace to confirm the initialization of the database layer for AutoCare
        System.out.println("AppInitializer: Loading Root Config for AutoCare (DbConfig)...");

        return new Class[] { DbConfig.class };
    }

    @Override // Specifies configuration for the Servlet Context (Web/MVC)
    protected Class<?>[] getServletConfigClasses() {
        // Trace to confirm the initialization of the web layer for AutoCare
        System.out.println("AppInitializer: Loading Servlet Config for AutoCare (WebConfig)...");

        return new Class[] { WebConfig.class };
    }

    @Override // Maps the DispatcherServlet to the root path
    protected String[] getServletMappings() {
        // Trace to confirm all requests are being routed to the DispatcherServlet
        System.out.println("AppInitializer: Mapping DispatcherServlet to '/' for AutoCare project.");

        return new String[] { "/" };
    }
}