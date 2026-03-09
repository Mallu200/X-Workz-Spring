package com.xworkz.eduportal.service;

import com.xworkz.eduportal.dto.StudentDTO;
import java.util.List;

// Interface defining the business operations for the EduPortal student management system
public interface StudentService {

    // Validates student business rules (like USN format) and persists the record to the database
    boolean validateAndSave(StudentDTO dto);

    // Retrieves a single student's record by their unique primary key ID for editing or viewing
    StudentDTO fetchById(int id);

    // Fetches a student specifically by their University Seat Number (USN) for search functionality
    StudentDTO fetchByUsn(String usn);

    // Returns a complete list of all students currently registered in the EduPortal system
    List<StudentDTO> fetchAll();

    // Updates existing student academic information, such as CGPA or semester changes
    boolean update(StudentDTO dto);

    // Removes a student's record from the portal based on their unique primary key ID
    boolean delete(int id);
}