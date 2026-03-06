package com.xworkz.library.service;

import org.springframework.stereotype.Service;

// Marks this as a Service Layer bean, where core library rules are defined
@Service
public class BookService {

    /**
     * The Target Method: Focuses strictly on the book issuance logic.
     * All @Aspects (Membership, Audit) will wrap around this method.
     */
    public void issueBook(String bookName, String memberName) {

        // Business logic: In a real app, this would update the 'books' table
        // and 'borrowers' table in your MySQL database.
        System.out.println("LIBRARY: Issuing '" + bookName + "' to " + memberName + "...");

        // Note: No logging or validation code is needed here because of AOP!
    }
}