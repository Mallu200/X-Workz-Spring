package com.xworkz.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity // 1. Tells Hibernate this class maps to a MySQL table
@Table(name = "employee_details") // 2. Explicitly names the table in jpa_db
@Data @AllArgsConstructor @NoArgsConstructor // 3. Lombok magic for getters, setters, and constructors
public class Employee {

    @Id // 4. Marks this as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 5. Enables MySQL AUTO_INCREMENT
    private int id;

    private String name;
    private String department;
    private double salary;

    // 6. Custom constructor for easier object creation in your Runner/Service
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}