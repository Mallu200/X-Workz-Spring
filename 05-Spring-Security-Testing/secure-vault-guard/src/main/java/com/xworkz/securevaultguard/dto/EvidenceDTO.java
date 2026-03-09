package com.xworkz.securevaultguard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data // Generates Boilerplate: Getters, Setters, and toString for the vault records
@NoArgsConstructor // Required for Jackson and Spring form-binding
@AllArgsConstructor // Useful for quick instantiation during unit testing
public class EvidenceDTO implements Serializable {

    private static final long serialVersionUID = 1L; // Ensures version compatibility during serialization

    private int id; // Primary key for database reference

    @NotBlank(message = "Case Number cannot be blank")
    @Size(min = 5, max = 20, message = "Case Number must be between 5 and 20 characters")
    private String caseNumber; // e.g., CASE-2026-001

    @NotBlank(message = "Item Reference is required")
    private String itemRef; // Specific name or ID of the evidence item

    private String status; // Lifecycle: SEIZED, UNDER_ANALYSIS, DISPOSED

    // POPULATED VIA SECURITY CONTEXT:
    // Tracks the username of the 'Lead' or 'Analyst' who created this entry
    private String recordedBy;

    // Trace to verify DTO state before service layer validation
    public void logDtoState() {
        System.out.println("EvidenceDTO Trace: [Case=" + caseNumber + ", Status=" + status + ", By=" + recordedBy + "]");
    }
}