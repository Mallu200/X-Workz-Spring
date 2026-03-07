package com.xworkz.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 1. Marks this class as a JPA Entity to be managed by Hibernate
@Entity
// 2. Specifies the table name in your MySQL 'jpa_db' schema
@Table(name = "patient_table")
// 3. Lombok Annotations to reduce boilerplate (Getters, Setters, ToString, etc.)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    // 4. Primary Key with Auto-Increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 5. These fields will automatically become columns in 'patient_table'
    private String name;
    private String ailment; // The specific health issue (e.g., "Fever", "Back Pain")
}