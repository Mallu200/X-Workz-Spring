package com.xworkz.securevaultguard.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Entrance point for the SecureVaultGuard application.
 * Manages the lifecycle of the Root and Servlet ApplicationContexts.
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Trace to confirm high-level service and security beans are loading
        System.out.println("WebInit: Loading Root Context (Database & Security configurations)...");

        // Database and Security go here as they are shared "Root" contexts across the app
        return new Class[] { DatabaseConfig.class, SecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Trace to confirm the Web MVC layer (ViewResolvers, Controllers) is loading
        System.out.println("WebInit: Loading Servlet Context (SpringWebConfig)...");

        // Web/MVC logic resides in the child context
        return new Class[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // Trace to confirm the DispatcherServlet is intercepting all vault traffic
        System.out.println("WebInit: Mapping DispatcherServlet to '/' for SecureVaultGuard.");

        // All requests starting with / will go through the Spring container
        return new String[] { "/" };
    }
}