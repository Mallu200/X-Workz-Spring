package com.xworkz.eduportal.entity;

import lombok.Data;
import javax.persistence.*;

@Data // Generates Getters, Setters, and toString for the Student record
@Entity // Identifies this class as a persistent database entity
@Table(name = "student_table") // Sets the physical table name in your spring_db schema
public class StudentEntity {

    public StudentEntity() {
        // Trace to confirm when Hibernate instantiates the entity during fetch operations
        System.out.println("StudentEntity: Default constructor invoked (Entity hydration).");
    }

    @Id // Defines the primary key for the student record
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Instructs MySQL to handle auto-increment logic
    private int id;

    @Column(name = "s_name", nullable = false) // Maps to 's_name' column; cannot be null in DB
    private String name;

    @Column(name = "s_usn", nullable = false) // Maps to 's_usn' column for unique seat number tracking
    private String usn;

    @Column(name = "s_email", nullable = false) // Maps to 's_email' column for student contact info
    private String email;

    @Column(name = "s_dept") // Maps to 's_dept' column to store the academic department
    private String department;

    @Column(name = "s_cgpa") // Maps to 's_cgpa' column for academic performance tracking
    private Double cgpa;

    @Column(name = "s_semester") // Maps to 's_semester' column to store current academic year/term
    private String semester;

    // Helper trace to log entity state during Repository save or find
    public void logEntity() {
        System.out.println("StudentEntity [ID=" + id + ", USN=" + usn + ", Name=" + name + "]");
    }
}