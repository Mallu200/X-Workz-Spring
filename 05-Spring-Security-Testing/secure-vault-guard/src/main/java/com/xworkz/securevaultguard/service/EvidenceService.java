package com.xworkz.securevaultguard.service;

import com.xworkz.securevaultguard.dto.EvidenceDTO;
import java.util.List;

// Business logic contract for the SecureVaultGuard evidence management system
public interface EvidenceService {

    /**
     * Validates JSR-303 constraints and applies security context (recordedBy)
     * before persisting the evidence batch.
     */
    void validateAndSave(EvidenceDTO dto);

    /**
     * Retrieves the complete audit log of all evidence items in the vault.
     */
    List<EvidenceDTO> getAllEvidence();

    /**
     * Removes an evidence record from the ledger.
     * Restricted to high-privilege MANAGER roles in the implementation.
     */
    void deleteEvidence(int id);
}