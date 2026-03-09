package com.xworkz.eduportal.service.impl;

import com.xworkz.eduportal.dto.StudentDTO;
import com.xworkz.eduportal.entity.StudentEntity;
import com.xworkz.eduportal.repository.StudentRepository;
import com.xworkz.eduportal.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Stereotype annotation marking this as the business logic component
public class StudentServiceImpl implements StudentService {

    @Autowired // Injecting the StudentRepository dependency
    private StudentRepository repository;

    @Override
    @Transactional // Begins a transaction for the save operation
    public boolean validateAndSave(StudentDTO dto) {
        System.out.println("StudentService: Validating and mapping DTO for: " + dto.getName());

        StudentEntity entity = new StudentEntity();
        // Copies field values from DTO to Entity based on matching field names
        BeanUtils.copyProperties(dto, entity);

        System.out.println("StudentService: Mapping complete for USN: " + entity.getUsn());
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true) // Optimizes performance for data retrieval
    public List<StudentDTO> fetchAll() {
        System.out.println("StudentService: Fetching all student records from database...");

        List<StudentEntity> entities = repository.findAll();
        List<StudentDTO> dtos = new ArrayList<>();

        // Iterates and converts each record back into a DTO for the View layer
        for (StudentEntity entity : entities) {
            StudentDTO dto = new StudentDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        System.out.println("StudentService: Total student DTOs prepared: " + dtos.size());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO fetchByUsn(String usn) {
        System.out.println("StudentService: Fetching record for USN: " + usn);

        StudentEntity entity = repository.findByUsn(usn);
        if (entity != null) {
            StudentDTO dto = new StudentDTO();
            BeanUtils.copyProperties(entity, dto);
            System.out.println("StudentService: Found student: " + dto.getName());
            return dto;
        }

        System.out.println("StudentService: No record found for USN: " + usn);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO fetchById(int id) {
        System.out.println("StudentService: Fetching record for ID: " + id);

        StudentEntity entity = repository.findById(id);
        if (entity != null) {
            StudentDTO dto = new StudentDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }

    @Override
    @Transactional // Required for committing updates to the database
    public boolean update(StudentDTO dto) {
        System.out.println("StudentService: Initiating update for ID: " + dto.getId());

        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(dto, entity);

        return repository.update(entity);
    }

    @Override
    @Transactional // Required for the delete operation to be persistent
    public boolean delete(int id) {
        System.out.println("StudentService: Requesting deletion of record ID: " + id);
        return repository.delete(id);
    }
}