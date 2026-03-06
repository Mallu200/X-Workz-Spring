package com.xworkz.ecommerce.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to monitor performance for the Ecommerce system
@Aspect
@Component
public class PerformanceAspect {

    // ADVICE: @Around wraps the method, allowing logic before AND after the call
    // JOINPOINT: ProceedingJoinPoint is required to manually trigger the target method
    @Around("execution(* com.xworkz.ecommerce.service.OrderService.placeOrder(..))")
    public Object monitorTiming(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. BEFORE: Start the stopwatch
        System.out.println("AOP MONITOR: Starting performance timer...");
        long startTime = System.currentTimeMillis();

        // 2. PROCEED: Execute the actual business logic (the placeOrder method)
        // This line is where the JDBC/Hibernate code actually runs
        Object result = joinPoint.proceed();

        // 3. AFTER: Stop the stopwatch and calculate duration
        long endTime = System.currentTimeMillis();
        System.out.println("AOP MONITOR: Total Processing Time: " + (endTime - startTime) + "ms");

        // Return the original result (or a modified one) back to the caller
        return result;
    }
}