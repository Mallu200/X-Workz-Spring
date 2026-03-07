package com.xworkz.library.service;

import com.xworkz.library.entity.Book;
import com.xworkz.library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository repository;

    /**
     * Business Logic Layer:
     * We validate the incoming list before hitting the database.
     * This prevents empty batch calls and improves system performance.
     */
    public void uploadNewArrivals(List<Book> books) {
        // Validation check (Business Rule)
        if (books != null && !books.isEmpty()) {
            System.out.println("Service: Sending " + books.size() + " books for batch upload.");

            // Calling the high-performance JDBC Batch method
            repository.insertBatch(books);
        } else {
            System.err.println("Service: No books provided for upload.");
        }
    }
}