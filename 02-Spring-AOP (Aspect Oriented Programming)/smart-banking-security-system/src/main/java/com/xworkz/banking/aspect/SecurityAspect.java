package com.xworkz.banking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Marks this class as a specialized manager for method behavior
@Aspect
@Component
public class SecurityAspect {

    // ADVICE: @Around allows us to intercept the call BEFORE and AFTER execution
    // JOINPOINT: ProceedingJoinPoint is only available for @Around advice
    @Around("execution(* com.xworkz.banking.service.BankService.transferMoney(..))")
    public Object securityLayer(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. BEFORE Logic: Security / MFA Check
        System.out.println("Banking SECURITY: MFA Check Initiated... ");

        // Start timer for performance monitoring
        long start = System.currentTimeMillis();

        // 2. PROCEED: This line triggers the actual transferMoney() method in BankService
        // If you don't call joinPoint.proceed(), the real method NEVER runs!
        Object result = joinPoint.proceed();

        // 3. AFTER Logic: Performance calculation
        long end = System.currentTimeMillis();
        System.out.println("Banking PERFORMANCE: Transfer took " + (end - start) + "ms");

        // Return the original result of the method to the caller
        return result;
    }
}