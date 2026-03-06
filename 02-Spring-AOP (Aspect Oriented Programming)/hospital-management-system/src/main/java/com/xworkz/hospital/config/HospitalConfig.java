package com.xworkz.hospital.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 1. Marks this as the central Java-based configuration for your Clinic/Hospital app
@Configuration
// 2. Automatically registers @Service, @Repository, and @Aspect from the hospital package
@ComponentScan("com.xworkz.hospital")
// 3. MANDATORY: Enables Spring's ability to create Proxy objects.
// This allows @Before (Security) and @Around (Logging) to wrap around your Doctor's logic.
@EnableAspectJAutoProxy
public class HospitalConfig {
    // This bootstraps the AppointmentService, PatientRepository, and Security layers.
}