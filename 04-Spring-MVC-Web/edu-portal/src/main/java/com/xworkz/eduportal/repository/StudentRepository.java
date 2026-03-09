package com.xworkz.eduportal.repository;

import com.xworkz.eduportal.entity.StudentEntity;
import java.util.List;

// Interface defining the Data Access Object (DAO) contract for Student entities
public interface StudentRepository {

    // Persists a new StudentEntity into the 'student_table'
    boolean save(StudentEntity entity);

    // Retrieves a specific StudentEntity from the database using its Primary Key (ID)
    StudentEntity findById(int id);

    // Fetches a student record specifically using their unique University Seat Number
    StudentEntity findByUsn(String usn);

    // Retrieves the complete list of all student records from the database
    List<StudentEntity> findAll();

    // Updates an existing student's information by merging state into the persistence context
    boolean update(StudentEntity entity);

    // Removes a student record from the database based on their unique ID
    boolean delete(int id);
}