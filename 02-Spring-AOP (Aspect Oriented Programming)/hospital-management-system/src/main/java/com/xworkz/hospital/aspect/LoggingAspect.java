package com.xworkz.hospital.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to handle medical audit logging for the Clinic
@Aspect
@Component
public class LoggingAspect {

    // ADVICE: @Around wraps the method to manage the full lifecycle of the request
    // JOINPOINT: ProceedingJoinPoint allows us to intercept arguments and control execution
    @Around("execution(* com.xworkz.hospital.service.PatientService.viewPatientRecord(..))")
    public Object auditLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. BEFORE Logic: Extract data from the method call
        Object[] args = joinPoint.getArgs();
        String pId = (String) args[0]; // Patient ID
        String dName = (String) args[1]; // Doctor's Name

        System.out.println("AOP-AUDIT: Entry - Doctor [" + dName + "] requested access to [" + pId + "]");

        long start = System.currentTimeMillis();

        // 2. PROCEED: Execute the actual business logic (the PatientService method)
        // This is where the record is retrieved from your MySQL database
        Object result = joinPoint.proceed();

        // 3. AFTER Logic: Measure the duration of the data view
        long end = System.currentTimeMillis();
        System.out.println("AOP-AUDIT: Doctor finished viewing. Total time in record: " + (end - start) + "ms");

        // Return the original result back to the caller (e.g., the UI layer)
        return result;
    }
}