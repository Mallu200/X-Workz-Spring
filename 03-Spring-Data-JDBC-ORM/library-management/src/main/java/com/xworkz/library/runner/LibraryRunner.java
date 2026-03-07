package com.xworkz.library.runner;

import com.xworkz.library.entity.Book;
import com.xworkz.library.config.LibraryConfig;
import com.xworkz.library.service.LibraryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.ArrayList;
import java.util.List;

public class LibraryRunner {
    public static void main(String[] args) {

        // 1. BOOTSTRAP: Load the Spring JDBC Configuration
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LibraryConfig.class);

        // 2. RETRIEVE: Get the Service Bean (Business Layer)
        LibraryService service = context.getBean(LibraryService.class);

        // 3. PREPARE: Creating our collection of Book entities
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Effective Java", "Joshua Bloch"));
        bookList.add(new Book("Head First Java", "Kathy Sierra"));
        bookList.add(new Book("Spring Microservices", "John Carnell"));

        // 4. EXECUTE: The Service validates the data and triggers the Batch Update
        service.uploadNewArrivals(bookList);

        System.out.println("Library System: Update Complete.");

        // 5. CLEANUP: Properly close the context to release DB connections
        context.close();
    }
}