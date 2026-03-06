package com.xworkz.library.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

// Marks this as an Aspect to handle cross-cutting concerns for the Library module
@Aspect
@Component
public class MembershipAspect {

    // ADVICE: Runs BEFORE the book is issued in the database
    // POINTCUT: Specifically targets the issueBook method in your BookService
    @Before("execution(* com.xworkz.library.service.BookService.issueBook(..))")
    public void verifyMember() {
        // Validation logic: Ensure the user has a valid card before proceeding
        System.out.println("AOP MEMBERSHIP: Verifying member ID and active status... ✅");
    }

    // ADVICE: Runs AFTER the book issuance process completes
    @After("execution(* com.xworkz.library.service.BookService.issueBook(..))")
    public void updateLedger() {
        // Post-processing logic: Manual record-keeping or physical logs
        System.out.println("AOP LEDGER: Entry created in the physical registry. 📖");
    }
}