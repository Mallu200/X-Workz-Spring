package com.xworkz.fittrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Marks this class as a source of bean definitions
@EnableWebMvc // Enables Spring MVC features and default configurations
@ComponentScan("com.xworkz.fittrack") // Scans the base package for Spring components
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        // Trace to confirm the configuration class is being instantiated
        System.out.println("Created WebConfig: Spring MVC Configuration initialized.");
    }

    @Bean // Defines a bean to resolve logical view names to physical resources
    public ViewResolver viewResolver() {
        System.out.println("Registering viewResolver bean in WebConfig..."); // SOP for bean registration

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Sets the directory location for the JSP files
        resolver.setPrefix("/WEB-INF/views/");

        // Sets the file extension for the view files
        resolver.setSuffix(".jsp");

        System.out.println("ViewResolver configured with Prefix: /WEB-INF/views/ and Suffix: .jsp");
        return resolver;
    }
}