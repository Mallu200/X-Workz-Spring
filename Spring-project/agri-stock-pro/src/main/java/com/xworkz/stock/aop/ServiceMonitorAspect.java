package com.xworkz.stock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitorAspect {

    // Monitors execution time and handles exceptions for all StockService methods.
    @Around("execution(* com.xworkz.stock.service.StockService.*(..))")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        String methodName = joinPoint.getSignature().getName();

        try {
            System.out.println("03-AOP: Starting execution of method: " + methodName);

            result = joinPoint.proceed(); // Executes the actual service method

            long end = System.currentTimeMillis();
            System.out.println("04-AOP: Successfully executed " + methodName + " in " + (end - start) + "ms");
        } catch (Exception e) {
            // Standardized error handling for all service calls
            System.err.println("05-AOP: Exception occurred in " + methodName + " - Message: " + e.getMessage());
            throw e; // Rethrowing ensures the service layer or controller still knows something went wrong
        }

        return result;
    }
}