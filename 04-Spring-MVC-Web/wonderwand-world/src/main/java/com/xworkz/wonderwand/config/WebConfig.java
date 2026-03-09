package com.xworkz.wonderwand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Marks this class as a source of bean definitions for the Spring IoC Container
@EnableWebMvc // Enables standard Spring MVC features like @Controller and JSR-303 validation
@ComponentScan("com.xworkz.wonderwand") // Scans for @Controller, @Service, and @Repository in the WonderWand package
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        // Trace to confirm the WonderWand Web Configuration is being loaded during server startup
        System.out.println("Created WebConfig: Spring MVC Configuration for WonderWand initialized.");
    }

    @Bean // Defines the bean responsible for resolving logical view names to physical JSP files
    public ViewResolver viewResolver() {
        System.out.println("WebConfig: Registering InternalResourceViewResolver bean..."); // Bean registration trace

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Sets the internal folder path where WonderWand JSP files are securely stored
        resolver.setPrefix("/WEB-INF/views/");

        // Appends the .jsp extension to the logical view name returned by your controllers
        resolver.setSuffix(".jsp");

        System.out.println("WebConfig: ViewResolver configured with Prefix [/WEB-INF/views/] and Suffix [.jsp]");
        return resolver;
    }
}