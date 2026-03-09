package com.xworkz.securevaultguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Marks this class as a source of bean definitions for the Spring container
@EnableWebMvc // Enables Spring MVC features like @Controller, @RequestMapping, and validation
@ComponentScan(basePackages = "com.xworkz.securevaultguard") // Scans for @Controller, @Service, and @Repository in the vault package
public class SpringWebConfig {

    public SpringWebConfig() {
        // Trace to confirm the SecureVaultGuard Web Configuration is being loaded during startup
        System.out.println("Created SpringWebConfig: Initializing Web MVC for SecureVaultGuard.");
    }

    @Bean // Configures the ViewResolver to map logical view names to physical JSP files
    public ViewResolver viewResolver() {
        System.out.println("SpringWebConfig: Registering InternalResourceViewResolver bean..."); // Bean registration trace

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Defines the secure internal path for vault-related JSP files
        resolver.setPrefix("/WEB-INF/views/");

        // Appends the .jsp extension to all returned view names
        resolver.setSuffix(".jsp");

        System.out.println("SpringWebConfig: ViewResolver configured with Prefix [/WEB-INF/views/] and Suffix [.jsp]");
        return resolver;
    }
}