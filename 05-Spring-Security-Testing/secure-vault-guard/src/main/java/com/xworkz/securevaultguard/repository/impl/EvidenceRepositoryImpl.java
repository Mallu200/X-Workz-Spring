package com.xworkz.securevaultguard.repository.impl;

import com.xworkz.securevaultguard.entity.EvidenceEntity;
import com.xworkz.securevaultguard.repository.EvidenceRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // Stereotype marking this as the Data Access Object for the Vault
public class EvidenceRepositoryImpl implements EvidenceRepository {

    @PersistenceContext // Injects the thread-safe EntityManager managed by the Spring container
    private EntityManager entityManager;

    @Override
    public void save(EvidenceEntity entity) {
        // Trace to confirm the Case Number being written to the spring_db
        System.out.println("EvidenceRepo: Persisting new evidence for Case: " + entity.getCaseNumber());

        // Transitions the entity into the persistence context (Managed state)
        entityManager.persist(entity);
    }

    @Override
    public List<EvidenceEntity> findAll() {
        System.out.println("EvidenceRepo: Fetching complete audit ledger via JPQL...");

        // Uses JPQL to select all records from the EvidenceEntity mapping
        return entityManager.createQuery("from EvidenceEntity", EvidenceEntity.class).getResultList();
    }

    @Override
    public void deleteById(int id) {
        System.out.println("EvidenceRepo: Requesting removal of Record ID: " + id);

        // Step 1: Find the entity to bring it into the current persistence context
        EvidenceEntity entity = entityManager.find(EvidenceEntity.class, id);

        // Step 2: Remove the managed entity from the database
        if (entity != null) {
            entityManager.remove(entity);
            System.out.println("EvidenceRepo: Record successfully purged from vault.");
        } else {
            System.err.println("EvidenceRepo: Deletion failed - ID " + id + " not found.");
        }
    }
}