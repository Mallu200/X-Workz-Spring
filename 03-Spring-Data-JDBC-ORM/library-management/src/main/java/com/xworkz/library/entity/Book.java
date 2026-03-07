package com.xworkz.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotations to keep the catalog code clean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    // These fields match the column names in your manual MySQL 'books' table
    private String title;
    private String author;
}