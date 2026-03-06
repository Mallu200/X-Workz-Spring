package com.xworkz.hospital.service;

import org.springframework.stereotype.Service;

// Marks this as a Service Layer bean, where core medical rules reside
@Service
public class PatientService {

    /**
     * The Target Method: Focuses strictly on retrieving the patient's data.
     * All @Aspects (Security, Logging) will wrap around this method.
     */
    public void viewPatientRecord(String patientId, String doctorName) {

        // Business logic: In a real app, this would query the 'patient_history'
        // table in your MySQL database using JdbcTemplate.
        System.out.println("HOSPITAL-CORE: Displaying medical records for Patient ID: " + patientId);

        // Note: No authorization or logging code is needed here because of AOP!
    }
}