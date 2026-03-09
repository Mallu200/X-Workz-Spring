package com.xworkz.fittrack.service;

import com.xworkz.fittrack.dto.MemberDTO;
import com.xworkz.fittrack.entity.MemberEntity;
import com.xworkz.fittrack.repository.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Marks this class as the business logic provider in the Spring container
public class MemberServiceImpl implements MemberService {

    @Autowired // Injects the Repository for database interaction
    private MemberRepository repository;

    @Override
    @Transactional // Ensures the save operation is atomic; rolls back on failure
    public boolean validateAndSave(MemberDTO dto) {
        System.out.println("MemberServiceImpl: Validating and Saving DTO for " + dto.getName()); // Trace entry point

        MemberEntity entity = new MemberEntity();
        // Automatically copies matching fields from DTO to Entity object
        BeanUtils.copyProperties(dto, entity);

        System.out.println("MemberServiceImpl: Conversion to Entity complete. Passing to repository.");
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true) // Informs Spring this is a fetch-only operation for better performance
    public List<MemberDTO> fetchAll() {
        System.out.println("MemberServiceImpl: Initiating fetchAll operation..."); // Trace for data retrieval

        List<MemberEntity> entities = repository.findAll();
        List<MemberDTO> dtos = new ArrayList<>();

        // Loops through retrieved entities to convert them back to DTOs for the view
        for (MemberEntity entity : entities) {
            MemberDTO dto = new MemberDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        System.out.println("MemberServiceImpl: Successfully mapped " + dtos.size() + " entities to DTOs.");
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDTO fetchById(int id) {
        System.out.println("MemberServiceImpl: Fetching member with ID: " + id); // Trace for targeted search

        MemberEntity entity = repository.findById(id);
        if (entity != null) {
            MemberDTO dto = new MemberDTO();
            BeanUtils.copyProperties(entity, dto); // Maps data for the edit form
            System.out.println("MemberServiceImpl: Found entity for ID " + id + ", mapping to DTO.");
            return dto;
        }

        System.out.println("MemberServiceImpl: No member found with ID: " + id);
        return null;
    }

    @Override
    @Transactional // Required to persist changes to an existing record
    public boolean update(MemberDTO dto) {
        System.out.println("MemberServiceImpl: Updating record for Member ID: " + dto.getId()); // Trace for update flow

        MemberEntity entity = new MemberEntity();
        BeanUtils.copyProperties(dto, entity); // Transfers updated UI data to persistent entity

        return repository.update(entity);
    }

    @Override
    @Transactional // Required to remove record from DB
    public boolean delete(int id) {
        System.out.println("MemberServiceImpl: Requesting deletion of ID: " + id); // Trace for removal
        return repository.delete(id);
    }
}