package com.xworkz.freshharvest.repository.impl;

import com.xworkz.freshharvest.entity.ProduceEntity;
import com.xworkz.freshharvest.repository.ProduceRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // Stereotype marking this as a Data Access Object for FreshHarvest
public class ProduceRepositoryImpl implements ProduceRepository {

    @PersistenceContext // Injects the container-managed EntityManager for DB operations
    private EntityManager entityManager;

    @Override
    public boolean save(ProduceEntity entity) {
        // Trace to confirm the produce name being persisted in organic_inventory
        System.out.println("FreshHarvestRepo: Persisting new produce: " + entity.getName());

        // Transitions the entity from transient to managed state
        entityManager.persist(entity);
        return true;
    }

    @Override
    public ProduceEntity findById(int id) {
        // Trace for primary key lookup of a specific batch
        System.out.println("FreshHarvestRepo: Searching for record ID: " + id);

        // Direct retrieval from the database using the primary key
        return entityManager.find(ProduceEntity.class, id);
    }

    @Override
    public List<ProduceEntity> findAll() {
        System.out.println("FreshHarvestRepo: Fetching all organic inventory via JPQL...");

        // JPQL query selecting all instances from the ProduceEntity mapping
        String jpql = "SELECT p FROM ProduceEntity p";
        return entityManager.createQuery(jpql, ProduceEntity.class).getResultList();
    }

    @Override
    public boolean update(ProduceEntity entity) {
        // Trace to track status or quantity updates (e.g., Fresh to Near-Expiry)
        System.out.println("FreshHarvestRepo: Merging updates for: " + entity.getName());

        // Merges the state of a detached entity back into the persistence context
        entityManager.merge(entity);
        return true;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("FreshHarvestRepo: Requesting removal of record ID: " + id);

        // Fetches the managed entity first to ensure Hibernate can track the deletion
        ProduceEntity entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
            System.out.println("FreshHarvestRepo: Record successfully removed from database.");
            return true;
        }

        System.err.println("FreshHarvestRepo: Deletion failed - ID " + id + " not found.");
        return false;
    }
}