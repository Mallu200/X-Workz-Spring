package com.xworkz.hospital.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to manage patient data privacy for the Clinic module
@Aspect
@Component
public class PrivacyAspect {

    // ADVICE: Runs BEFORE the sensitive medical record is accessed
    // POINTCUT: Specifically targets the viewPatientRecord method in PatientService
    @Before("execution(* com.xworkz.hospital.service.PatientService.viewPatientRecord(..))")
    public void checkAccess() {
        // Pre-processing: Authentication of the Doctor's identity
        System.out.println("AOP-PRIVACY: Checking Doctor's biometric and digital signature... 🔐");
    }

    // ADVICE: Runs AFTER the record viewing process is completed
    @After("execution(* com.xworkz.hospital.service.PatientService.viewPatientRecord(..))")
    public void sessionClose() {
        // Post-processing: Immediate cleanup and encryption of the view session
        System.out.println("AOP-PRIVACY: Sensitive data view closed. Session encrypted. 🛡️");
    }
}