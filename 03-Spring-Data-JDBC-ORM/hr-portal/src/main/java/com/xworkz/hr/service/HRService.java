package com.xworkz.hr.service;

import com.xworkz.hr.entity.Employee;
import com.xworkz.hr.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service // Marks this as a business logic bean in the Spring Context
public class HRService {

    @Autowired // Injects the Repository for data access
    private HRRepository repository;

    @Transactional // 1. Opens a database transaction before the method starts
    public void addEmployee(Employee emp) {
        // Business logic: You could add validation here before saving
        repository.saveEmployee(emp);
        System.out.println("Service: Employee " + emp.getName() + " added.");
        // Transaction is committed here if no exception occurs
    }

    @Transactional(readOnly = true) // 2. Optimized for performance since no data is modified
    public void displayDeptReport(String dept) {
        List<Employee> list = repository.findByDept(dept);
        System.out.println("--- Department Report: " + dept + " ---");
        // Using Java 8 Method Reference for clean iteration
        list.forEach(e -> System.out.println(e.getName()));
    }
}