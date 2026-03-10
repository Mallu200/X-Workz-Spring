package com.xworkz.stock.service.impl;

import com.xworkz.stock.dto.StockDTO;
import com.xworkz.stock.entity.StockEntity;
import com.xworkz.stock.repository.StockRepository;
import com.xworkz.stock.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository repository;

    // Validates the DTO data and converts it into an entity for persistence.
    @Override
    public boolean validateAndSave(StockDTO dto) {
        System.out.println("88-SERV: Starting validation for item: " + dto.getItemName());
        try {
            if (dto != null && dto.getItemName().length() >= 3) {
                StockEntity entity = new StockEntity();
                BeanUtils.copyProperties(dto, entity);
                boolean saved = repository.save(entity);
                System.out.println("89-SERV: Validation passed and save status: " + saved);
                return saved;
            }
            System.out.println("90-SERV: Validation failed for item: " + (dto != null ? dto.getItemName() : "NULL"));
            return false;
        } catch (Exception e) {
            System.err.println("91-SERV: Error in validateAndSave: " + e.getMessage());
            return false;
        }
    }

    // Fetches all entities and transforms them into DTOs for the presentation layer.
    @Override
    @Transactional(readOnly = true)
    public List<StockDTO> getAllStock() {
        System.out.println("92-SERV: Fetching all stock for the service layer...");
        try {
            List<StockDTO> list = repository.findAll().stream().map(entity -> {
                StockDTO dto = new StockDTO();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            }).collect(Collectors.toList());
            System.out.println("93-SERV: Successfully converted " + list.size() + " entities to DTOs.");
            return list;
        } catch (Exception e) {
            System.err.println("94-SERV: Error converting entities to DTOs: " + e.getMessage());
            return null;
        }
    }

    // Locates a single stock record and prepares it for viewing or editing.
    @Override
    @Transactional(readOnly = true)
    public StockDTO findById(int id) {
        System.out.println("95-SERV: Searching for stock DTO with ID: " + id);
        try {
            StockEntity entity = repository.findById(id);
            if (entity != null) {
                StockDTO dto = new StockDTO();
                BeanUtils.copyProperties(entity, dto);
                System.out.println("96-SERV: Found item: " + dto.getItemName());
                return dto;
            }
            return null;
        } catch (Exception e) {
            System.err.println("97-SERV: Exception in findById: " + e.getMessage());
            return null;
        }
    }

    // Processes the update logic by mapping the modified DTO back to a database entity.
    @Override
    public boolean updateStock(StockDTO dto) {
        System.out.println("98-SERV: Processing update logic for ID: " + dto.getId());
        try {
            StockEntity entity = new StockEntity();
            BeanUtils.copyProperties(dto, entity);
            boolean updated = repository.update(entity);
            System.out.println("99-SERV: Update operation completed for: " + dto.getItemName());
            return updated;
        } catch (Exception e) {
            System.err.println("100-SERV: Update failed at service level: " + e.getMessage());
            return false;
        }
    }

    // Triggers the deletion process for a specific inventory item by its ID.
    @Override
    public boolean deleteById(int id) {
        System.out.println("101-SERV: Initiating deletion at service level for ID: " + id);
        try {
            boolean deleted = repository.delete(id);
            System.out.println("102-SERV: Deletion status for ID " + id + ": " + deleted);
            return deleted;
        } catch (Exception e) {
            System.err.println("103-SERV: Error during service-level deletion: " + e.getMessage());
            return false;
        }
    }

    // Maps search results from the repository into a list of display-ready DTOs.
    @Override
    @Transactional(readOnly = true)
    public List<StockDTO> searchByItemName(String name) {
        System.out.println("104-SERV: Running service-level search for: " + name);
        try {
            List<StockDTO> results = repository.findByName(name).stream().map(entity -> {
                StockDTO dto = new StockDTO();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            }).collect(Collectors.toList());
            System.out.println("105-SERV: Search completed with " + results.size() + " matches.");
            return results;
        } catch (Exception e) {
            System.err.println("106-SERV: Search failed in service layer: " + e.getMessage());
            return null;
        }
    }

    // Confirms that the Spring container has successfully managed the bean lifecycle.
    @PostConstruct
    public void init() {
        System.out.println("107-SERV: StockService Bean is fully initialized and ready.");
    }

    // Handles resource cleanup before the Spring application context shuts down.
    @PreDestroy
    public void cleanup() {
        System.out.println("108-SERV: StockService Bean cleanup started.");
    }
}