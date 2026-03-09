package com.xworkz.securevaultguard.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Marks this as a persistent object for the SecureVaultGuard system
@Table(name = "evidence_vault") // Maps to the physical table in your spring_db
@Data // Generates Boilerplate: Getters, Setters, and toString for the vault record
public class EvidenceEntity {

    public EvidenceEntity() {
        // Trace to confirm when Hibernate hydraties the entity from the database
        System.out.println("EvidenceEntity: Instance created for vault record.");
    }

    @Id // Defines the primary key for the evidence ledger
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL handles the Auto-Increment logic
    private int id;

    @Column(name = "case_number", nullable = false) // Ensures every record is linked to a valid case
    private String caseNumber;

    @Column(name = "item_reference") // Maps to 'item_reference' column for specific evidence IDs
    private String itemRef;

    @Column(name = "status") // Tracks lifecycle: SEIZED, UNDER_ANALYSIS, DISPOSED
    private String status;

    @Column(name = "recorded_by") // Field to capture the authenticated user from SecurityContext
    private String recordedBy;

    @Column(name = "created_at", updatable = false) // Immutable timestamp for the audit trail
    private LocalDateTime createdAt;

    // Hibernate Lifecycle Hook: Automatically sets the timestamp before the INSERT query
    @PrePersist
    protected void onCreate() {
        System.out.println("EvidenceEntity: Generating creation timestamp for Case: " + caseNumber);
        this.createdAt = LocalDateTime.now();
    }

    // Helper trace for debugging database merges or updates
    public void logEntityState() {
        System.out.println("EvidenceEntity [ID=" + id + ", Case=" + caseNumber + ", Status=" + status + "]");
    }
}