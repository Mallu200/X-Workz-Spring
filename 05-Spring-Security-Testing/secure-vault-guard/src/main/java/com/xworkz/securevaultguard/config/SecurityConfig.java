package com.xworkz.securevaultguard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Marks this as a core security configuration source
@EnableWebSecurity // Activates the Spring Security filter chain for all incoming requests
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enables method-level security using @PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        // Trace to confirm the SecureVaultGuard Security layer is active during startup
        System.out.println("Created SecurityConfig: Initializing Access Control for Vault Evidence.");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfig: Configuring In-Memory Authentication for Vault Roles...");

        // Step 1: Define Users and Roles (In-Memory for rapid testing of the vault)
        auth.inMemoryAuthentication()
                .withUser("lead").password("123").roles("MANAGER") // Full access to edit and delete
                .and()
                .withUser("analyst").password("123").roles("EDITOR") // Can view and register new items
                .and()
                .withUser("auditor").password("123").roles("VIEWER"); // Read-only access to logs
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig: Defining URL-level authorization rules...");

        // Step 2: Define URL-level security and redirection logic
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll() // Public landing page accessible to all
                .antMatchers("/evidence/viewAll").hasAnyRole("MANAGER", "EDITOR", "VIEWER") // Shared view access
                .antMatchers("/evidence/register").hasAnyRole("MANAGER", "EDITOR") // Restricted to content creators
                .anyRequest().authenticated() // All other vault URLs require an active session
                .and()
                .formLogin().permitAll() // Redirects unauthorized users to the default Spring login
                .and()
                .logout().logoutSuccessUrl("/").permitAll(); // Secure session termination
    }

    @Bean // Defines how passwords are processed; currently plain-text for lab debugging
    public PasswordEncoder passwordEncoder() {
        System.out.println("SecurityConfig: Registering NoOpPasswordEncoder (Development Mode).");
        // Warning: For production projects, we switch this to BCryptPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }
}