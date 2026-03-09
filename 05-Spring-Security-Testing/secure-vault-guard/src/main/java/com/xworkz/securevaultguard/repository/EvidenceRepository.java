package com.xworkz.securevaultguard.repository;

import com.xworkz.securevaultguard.entity.EvidenceEntity;
import java.util.List;

// Data Access Object (DAO) contract for the SecureVaultGuard persistence layer
public interface EvidenceRepository {

    /**
     * Persists a new EvidenceEntity (Chain of Custody record) into the MySQL database.
     */
    void save(EvidenceEntity entity);

    /**
     * Retrieves the complete collection of all evidence currently stored in the vault.
     */
    List<EvidenceEntity> findAll();

    /**
     * Removes a specific record from the database using its primary key ID.
     */
    void deleteById(int id);
}