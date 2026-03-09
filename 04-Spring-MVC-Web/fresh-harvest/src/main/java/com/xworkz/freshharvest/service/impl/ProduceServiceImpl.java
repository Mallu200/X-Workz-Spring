package com.xworkz.freshharvest.service.impl;

import com.xworkz.freshharvest.dto.ProduceDTO;
import com.xworkz.freshharvest.entity.ProduceEntity;
import com.xworkz.freshharvest.repository.ProduceRepository;
import com.xworkz.freshharvest.service.ProduceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Stereotype identifying this as the business logic service for FreshHarvest
public class ProduceServiceImpl implements ProduceService {

    @Autowired // Injecting the repository for database interactions
    private ProduceRepository repository;

    @Override
    @Transactional // Ensures the save operation is atomic and persistent in the inventory
    public boolean validateAndSave(ProduceDTO dto) {
        System.out.println("FreshHarvestService: Validating produce: " + dto.getName());

        ProduceEntity entity = new ProduceEntity();
        // Maps the DTO data to a persistent entity for Hibernate
        BeanUtils.copyProperties(dto, entity);

        System.out.println("FreshHarvestService: Saving " + dto.getQuantity() + " " + dto.getUnit() + " of " + dto.getName());
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true) // Optimizes DB performance for read-only inventory retrieval
    public List<ProduceDTO> fetchAll() {
        System.out.println("FreshHarvestService: Fetching all organic inventory logs...");

        List<ProduceEntity> entities = repository.findAll();
        List<ProduceDTO> dtos = new ArrayList<>();

        // Iterates through database records to prepare DTOs for the view layer
        for (ProduceEntity entity : entities) {
            ProduceDTO dto = new ProduceDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        System.out.println("FreshHarvestService: Total items prepared for dashboard: " + dtos.size());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public ProduceDTO fetchById(int id) {
        System.out.println("FreshHarvestService: Retrieving details for Produce ID: " + id);

        ProduceEntity entity = repository.findById(id);
        if (entity != null) {
            ProduceDTO dto = new ProduceDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }

        System.err.println("FreshHarvestService: Inventory record not found for ID: " + id);
        return null;
    }

    @Override
    @Transactional // Required to commit status or quantity changes (e.g., Fresh to Expired)
    public boolean update(ProduceDTO dto) {
        System.out.println("FreshHarvestService: Updating inventory for: " + dto.getName());

        ProduceEntity entity = new ProduceEntity();
        BeanUtils.copyProperties(dto, entity);

        return repository.update(entity);
    }

    @Override
    @Transactional // Required for secure removal of items from the inventory ledger
    public boolean delete(int id) {
        System.out.println("FreshHarvestService: Deleting inventory record ID: " + id);
        return repository.delete(id);
    }
}