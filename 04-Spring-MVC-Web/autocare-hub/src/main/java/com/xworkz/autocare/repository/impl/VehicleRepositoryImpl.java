package com.xworkz.autocare.repository.impl;

import com.xworkz.autocare.entity.VehicleEntity;
import com.xworkz.autocare.repository.VehicleRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // Stereotype marking this as a Data Access Object (DAO)
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext // Injects the container-managed EntityManager for thread-safe DB operations
    private EntityManager entityManager;

    @Override
    public boolean save(VehicleEntity entity) {
        System.out.println("VehicleRepositoryImpl: Executing persist for owner: " + entity.getOwnerName()); // SOP for INSERT

        // JPA 'persist' makes the transient instance managed and persistent
        entityManager.persist(entity);

        System.out.println("VehicleRepositoryImpl: Vehicle successfully persisted in DB.");
        return true;
    }

    @Override
    public VehicleEntity findById(int id) {
        System.out.println("VehicleRepositoryImpl: Searching for Vehicle ID: " + id); // SOP for fetch

        // JPA 'find' retrieves the entity by its primary key
        return entityManager.find(VehicleEntity.class, id);
    }

    @Override
    public List<VehicleEntity> findAll() {
        System.out.println("VehicleRepositoryImpl: Running JPQL query to fetch all vehicles..."); // SOP for query

        // Using JPQL (Java Persistence Query Language) to fetch all records from the entity class
        String jpql = "SELECT v FROM VehicleEntity v";
        List<VehicleEntity> list = entityManager.createQuery(jpql, VehicleEntity.class).getResultList();

        System.out.println("VehicleRepositoryImpl: Retrieved " + list.size() + " records from database.");
        return list;
    }

    @Override
    public boolean update(VehicleEntity entity) {
        System.out.println("VehicleRepositoryImpl: Merging vehicle changes for ID: " + entity.getId()); // SOP for UPDATE

        // JPA 'merge' copies the state of the given entity into the current persistence context
        entityManager.merge(entity);

        System.out.println("VehicleRepositoryImpl: Vehicle record updated successfully.");
        return true;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("VehicleRepositoryImpl: Checking record existence for deletion, ID: " + id); // SOP for search before delete

        VehicleEntity entity = findById(id);
        if (entity != null) {
            System.out.println("VehicleRepositoryImpl: Record found. Removing entity...");

            // Entity must be in managed state to be removed via the persistence context
            entityManager.remove(entity);

            System.out.println("VehicleRepositoryImpl: Vehicle removed from DB.");
            return true;
        }

        System.err.println("VehicleRepositoryImpl: Deletion failed - ID " + id + " not found."); // SOP for error
        return false;
    }
}