package com.xworkz.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to handle digital record-keeping (Auditing)
@Aspect
@Component
public class AuditAspect {

    // ADVICE: @Around wraps the method, allowing logic before AND after the call
    // JOINPOINT: ProceedingJoinPoint allows us to access method arguments and control execution
    @Around("execution(* com.xworkz.library.service.BookService.issueBook(..))")
    public Object createAuditTrail(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. BEFORE Logic: Initialization
        System.out.println("AOP AUDIT: Starting transaction log for digital records...");

        // --- TOPIC: Accessing Method Arguments ---
        // Capture the bookName and memberName passed to the method
        Object[] args = joinPoint.getArgs();
        String book = (String) args[0]; // First argument: bookTitle
        String member = (String) args[1]; // Second argument: memberID

        // 2. PROCEED: Execute the actual business logic in BookService
        Object result = joinPoint.proceed();

        // 3. AFTER Logic: Completion Log
        System.out.println("AOP AUDIT: Successfully logged issuance of: " + book + " to " + member);

        // Return the original result back to the caller
        return result;
    }
}