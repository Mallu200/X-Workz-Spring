package com.xworkz.securevaultguard.service.impl;

import com.xworkz.securevaultguard.dto.EvidenceDTO;
import com.xworkz.securevaultguard.entity.EvidenceEntity;
import com.xworkz.securevaultguard.repository.EvidenceRepository;
import com.xworkz.securevaultguard.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Stereotype for the vault's business logic layer
public class EvidenceServiceImpl implements EvidenceService {

    @Autowired // Injecting the JPA repository for vault operations
    private EvidenceRepository repository;

    @Override
    @Transactional // Ensures the evidence batch is saved atomically in the spring_db
    @PreAuthorize("hasAnyRole('MANAGER', 'EDITOR')") // Firewall: Only LEAD and ANALYST can register
    public void validateAndSave(EvidenceDTO dto) {
        // Step 1: Capture the authenticated username directly from the Security Session
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("EvidenceService: User [" + currentUsername + "] is registering Case: " + dto.getCaseNumber());

        // Step 2: Convert DTO to Entity (Manual mapping for precision in security projects)
        EvidenceEntity entity = new EvidenceEntity();
        entity.setCaseNumber(dto.getCaseNumber());
        entity.setItemRef(dto.getItemRef());
        entity.setStatus(dto.getStatus());

        // Mandatory Audit Trail: The user cannot spoof this field
        entity.setRecordedBy(currentUsername);

        repository.save(entity);
        System.out.println("EvidenceService: Record successfully committed to the vault.");
    }

    @Override
    @Transactional(readOnly = true) // Optimizes DB performance for audit log retrieval
    public List<EvidenceDTO> getAllEvidence() {
        System.out.println("EvidenceService: Fetching all vault entries for audit...");
        List<EvidenceEntity> entities = repository.findAll();
        List<EvidenceDTO> dtos = new ArrayList<>();

        // Maps the persistent entities back to DTOs for the JSP view layer
        for (EvidenceEntity entity : entities) {
            dtos.add(new EvidenceDTO(entity.getId(), entity.getCaseNumber(),
                    entity.getItemRef(), entity.getStatus(), entity.getRecordedBy()));
        }
        return dtos;
    }

    @Override
    @Transactional // Required for the 'remove' operation in the persistence context
    @PreAuthorize("hasRole('MANAGER')") // High-Security Firewall: Strictly for LEAD (Manager) roles only
    public void deleteEvidence(int id) {
        String adminUser = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("EvidenceService: MANAGER [" + adminUser + "] is deleting Record ID: " + id);
        repository.deleteById(id);
    }
}