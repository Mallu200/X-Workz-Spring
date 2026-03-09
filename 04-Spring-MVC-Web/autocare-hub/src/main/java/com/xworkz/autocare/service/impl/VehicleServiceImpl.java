package com.xworkz.autocare.service.impl;

import com.xworkz.autocare.dto.VehicleDTO;
import com.xworkz.autocare.entity.VehicleEntity;
import com.xworkz.autocare.repository.VehicleRepository;
import com.xworkz.autocare.service.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Stereotype annotation marking this as the Service layer component
public class VehicleServiceImpl implements VehicleService {

    @Autowired // Injecting the Repository dependency for DB operations
    private VehicleRepository repository;

    @Override
    @Transactional // Ensures the entire save process is treated as a single transaction
    public boolean validateAndSave(VehicleDTO dto) {
        System.out.println("VehicleServiceImpl: Validating and saving vehicle for owner: " + dto.getOwnerName()); // Entry trace

        VehicleEntity entity = new VehicleEntity();
        // Copies field values from DTO to Entity (names must match exactly)
        BeanUtils.copyProperties(dto, entity);

        System.out.println("VehicleServiceImpl: Mapping successful for Plate: " + entity.getLicensePlate()); // Mapping trace
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true) // Performance optimization for read-only database access
    public List<VehicleDTO> fetchAll() {
        System.out.println("VehicleServiceImpl: Fetching all vehicle records from database..."); // SOP for retrieval

        List<VehicleEntity> entities = repository.findAll();
        List<VehicleDTO> dtos = new ArrayList<>();

        // Iterates through entities to map them back to DTOs for the UI layer
        for (VehicleEntity entity : entities) {
            VehicleDTO dto = new VehicleDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        System.out.println("VehicleServiceImpl: Total DTOs prepared for view: " + dtos.size()); // Count trace
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDTO fetchById(int id) {
        System.out.println("VehicleServiceImpl: Searching for vehicle with ID: " + id); // Search trace

        VehicleEntity entity = repository.findById(id);
        if (entity != null) {
            VehicleDTO dto = new VehicleDTO();
            BeanUtils.copyProperties(entity, dto); // Transfers DB data to UI-friendly DTO
            System.out.println("VehicleServiceImpl: Found record for " + dto.getOwnerName());
            return dto;
        }

        System.out.println("VehicleServiceImpl: No record found for ID: " + id); // Failure trace
        return null;
    }

    @Override
    @Transactional // Required to update an existing record in the database
    public boolean update(VehicleDTO dto) {
        System.out.println("VehicleServiceImpl: Initiating update for Vehicle ID: " + dto.getId()); // Update trace

        VehicleEntity entity = new VehicleEntity();
        BeanUtils.copyProperties(dto, entity); // Maps updated DTO values to entity

        return repository.update(entity);
    }

    @Override
    @Transactional // Required for the remove operation to be committed
    public boolean delete(int id) {
        System.out.println("VehicleServiceImpl: Requesting deletion of record ID: " + id); // Delete trace
        return repository.delete(id);
    }
}