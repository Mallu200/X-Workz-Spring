package com.xworkz.clinic.service;

import com.xworkz.clinic.entity.Patient;
import com.xworkz.clinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Marks this as the Service Layer, where business logic is executed
@Service
public class ClinicService {

    @Autowired
    private ClinicRepository repository;

    // --- TOPIC 3: @Transactional (Atomic Operations) ---
    // This annotation creates a proxy that manages the Database Transaction.
    // If an Exception occurs during saveWithHibernate, the count check and save are rolled back.
    @Transactional
    public void registerPatient(Patient patient) {

        // 1. Using JdbcTemplate via Repository (Read operation)
        System.out.println("Total Patients before: " + repository.countPatients());

        // 2. Using Hibernate via Repository (Write operation)
        repository.saveWithHibernate(patient);

        System.out.println("Patient Saved successfully!");
    }
}