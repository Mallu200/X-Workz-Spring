package com.xworkz.autocare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Defines the class as a source of Spring bean definitions
@EnableWebMvc // Enables support for @Controller-directed request mapping
@ComponentScan("com.xworkz.autocare") // Scans for @Controller, @Service, and @Repository in AutoCare
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        // Confirms that the AutoCare Web Configuration is loaded by the Servlet Container
        System.out.println("Created WebConfig for AutoCare: Spring MVC scanning initialized.");
    }

    @Bean // Configures how logical view names are converted to actual JSP paths
    public ViewResolver viewResolver() {
        System.out.println("WebConfig: Registering InternalResourceViewResolver bean...");

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Sets the physical location for the AutoCare JSP files
        resolver.setPrefix("/WEB-INF/views/");

        // Sets the expected file extension for views
        resolver.setSuffix(".jsp");

        System.out.println("WebConfig: ViewResolver configured with Prefix [/WEB-INF/views/] and Suffix [.jsp]");
        return resolver;
    }
}