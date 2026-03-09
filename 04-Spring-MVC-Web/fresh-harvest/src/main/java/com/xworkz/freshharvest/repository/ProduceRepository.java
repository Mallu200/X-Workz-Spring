package com.xworkz.freshharvest.repository;

import com.xworkz.freshharvest.entity.ProduceEntity;
import java.util.List;

// Interface defining the persistence contract for FreshHarvest inventory records
public interface ProduceRepository {

    // Persists a new ProduceEntity (Organic crop batch) into the MySQL database
    boolean save(ProduceEntity entity);

    // Retrieves a specific inventory record using its unique Primary Key (ID)
    ProduceEntity findById(int id);

    // Returns a complete list of all organic produce currently in the database
    List<ProduceEntity> findAll();

    // Synchronizes changes to quantity, shelf life, or status back to the database
    boolean update(ProduceEntity entity);

    // Permanently removes a produce record from the ledger based on its ID
    boolean delete(int id);
}