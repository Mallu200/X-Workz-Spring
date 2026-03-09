package com.xworkz.eduportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Marks this class as a source of bean definitions for the Spring Container
@EnableWebMvc // Enables support for @Controller, @RequestMapping, and JSR-303 validation
@ComponentScan("com.xworkz.eduportal") // Scans for @Controller, @Service, and @Repository in the new package
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        // Trace to confirm the EduPortal Web Configuration is being loaded during startup
        System.out.println("Created WebConfig: Spring MVC Configuration for EduPortal initialized.");
    }

    @Bean // Configures how logical view names (like "index") map to actual JSP files
    public ViewResolver viewResolver() {
        System.out.println("WebConfig: Registering InternalResourceViewResolver bean..."); // Bean registration trace

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Sets the physical folder path where EduPortal JSP files are stored
        resolver.setPrefix("/WEB-INF/views/");

        // Sets the file extension to be appended to the logical view name
        resolver.setSuffix(".jsp");

        System.out.println("WebConfig: ViewResolver configured with Prefix [/WEB-INF/views/] and Suffix [.jsp]");
        return resolver;
    }
}