package com.xworkz.banking.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

// Marks this class as an Aspect, which contains "advice" (the code to run)
// and "pointcuts" (where to run it)
@Aspect
@Component
public class LoggingAspect {

    // ADVICE: Runs BEFORE the transferMoney method begins
    // POINTCUT: Matches the specific transferMoney method in the BankService
    @Before("execution(* com.xworkz.banking.service.BankService.transferMoney(..))")
    public void logBefore() {
        System.out.println("Banking LOG: Validating account details... ");
    }

    // ADVICE: Runs AFTER the transferMoney method completes (even if it fails)
    @After("execution(* com.xworkz.banking.service.BankService.transferMoney(..))")
    public void logAfter() {
        System.out.println("Banking LOG: Transaction logged in Database. ");
    }
}