package com.xworkz.library.runner;

import com.xworkz.library.service.BookService;
import com.xworkz.library.config.LibraryConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// The entry point to test the decoupled Business Logic and AOP Layers
public class LibraryRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Initialize the Spring IoC Container
        // This triggers @ComponentScan and @EnableAspectJAutoProxy from LibraryConfig
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LibraryConfig.class);

        // 2. RETRIEVE: Get the PROXY version of BookService
        // Spring provides a proxy that wraps the real BookService with your @Aspects
        BookService library = context.getBean(BookService.class);

        // 3. EXECUTE: The "Magic" happens here.
        // Triggers: Membership Verify -> Audit Start -> Issue Book -> Audit End -> Ledger Update
        library.issueBook("Spring in Action", "Mallu200");

        // 4. CLEANUP: Close the container to release resources
        context.close();
    }
}