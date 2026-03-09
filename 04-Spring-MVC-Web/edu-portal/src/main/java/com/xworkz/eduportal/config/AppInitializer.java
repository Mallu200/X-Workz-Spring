package com.xworkz.eduportal.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// This class acts as the entrance to the EduPortal application, replacing web.xml
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override // Configures the 'Parent' context (Services, Repositories, and DataSource)
    protected Class<?>[] getRootConfigClasses() {
        // Trace to confirm that the EduPortal backend layer is being initialized
        System.out.println("AppInitializer: Loading Root Application Context (DbConfig) for EduPortal...");

        // Links the Database and Hibernate configuration classes to the Root context
        return new Class[] { DbConfig.class };
    }

    @Override // Configures the 'Child' context (Controllers, ViewResolver, and Web Handlers)
    protected Class<?>[] getServletConfigClasses() {
        // Trace to confirm that the EduPortal web/MVC layer is being initialized
        System.out.println("AppInitializer: Loading Servlet Application Context (WebConfig) for EduPortal...");

        // Links the Spring MVC and ViewResolver configuration classes to the Servlet context
        return new Class[] { WebConfig.class };
    }

    @Override // Defines the URL pattern that the DispatcherServlet will handle
    protected String[] getServletMappings() {
        // Trace to confirm that all incoming web traffic is being routed to Spring
        System.out.println("AppInitializer: Mapping DispatcherServlet to '/' for the EduPortal project.");

        // Standard mapping that ensures Spring handles every request coming to the server
        return new String[] { "/" };
    }
}