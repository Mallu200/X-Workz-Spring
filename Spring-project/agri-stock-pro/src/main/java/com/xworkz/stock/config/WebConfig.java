package com.xworkz.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.xworkz.stock")
public class WebConfig implements WebMvcConfigurer {

    // Configures the InternalResourceViewResolver to locate JSP files in the WEB-INF/views directory.
    @Bean
    public ViewResolver viewResolver() {
        System.out.println("27-WEB: Initializing ViewResolver for JSP files...");
        try {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/views/");
            resolver.setSuffix(".jsp");
            System.out.println("28-WEB: ViewResolver set to prefix: /WEB-INF/views/ and suffix: .jsp");
            return resolver;
        } catch (Exception e) {
            // Catches pathing or resolver instantiation errors
            System.err.println("29-WEB: Error setting up ViewResolver: " + e.getMessage());
            return null;
        }
    }

    // Maps URL patterns to physical folder locations for static assets like CSS, JS, and Images.
    @Override
    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        System.out.println("30-WEB: Registering Static Resource Handlers for /resources/**...");
        try {
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
            System.out.println("31-WEB: Static resources successfully mapped.");
        } catch (Exception e) {
            // Helps debug 404 errors for CSS/JS files
            System.err.println("32-WEB: Failed to register resource handlers: " + e.getMessage());
        }
    }
}