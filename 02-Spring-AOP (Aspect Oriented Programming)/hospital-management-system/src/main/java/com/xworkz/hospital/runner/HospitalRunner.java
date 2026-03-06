package com.xworkz.hospital.runner;

import com.xworkz.hospital.service.PatientService;
import com.xworkz.hospital.config.HospitalConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The entry point to test the secured Patient Service and AOP Layers
public class HospitalRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Initialize the Spring IoC Container
        // This triggers @ComponentScan and @EnableAspectJAutoProxy from HospitalConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HospitalConfig.class);

        // 2. RETRIEVE: Get the PROXY version of PatientService
        // Spring provides a proxy that wraps the real PatientService with your @Aspects
        PatientService service = context.getBean(PatientService.class);

        // 3. EXECUTE: The "Magic" happens here.
        // Triggers: Biometric Check -> Audit Start -> DB Record Retrieval -> Audit End -> Session Encryption
        service.viewPatientRecord("P-9901", "Dr. Mallikarjun");

        // 4. CLEANUP: Close the container to release resources
        context.close();
    }
}