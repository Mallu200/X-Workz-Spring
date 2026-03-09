package com.xworkz.fittrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data // Generates Getters, Setters, toString, and Equals/HashCode
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a default no-arg constructor
public class MemberDTO {

    // Unique identifier for the member, auto-generated in DB
    private int id;

    @NotBlank(message = "Name cannot be empty") // Ensures name isn't null or whitespace
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @Email(message = "Please provide a valid email address") // Validates standard email format
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits") // Regex for 10-digit mobile
    private String phoneNumber;

    @NotEmpty(message = "Please select a membership plan") // Ensures a selection is made from the UI
    private String planType;

    @NotBlank(message = "Trainer name is required") // Links the member to a specific trainer
    private String trainerName;

    @Min(value = 30, message = "Weight must be at least 30kg") // Lower bound validation for weight
    @Max(value = 250, message = "Weight must be less than 250kg") // Upper bound validation for weight
    private double weight;

    // Static block or constructor trace to confirm DTO instantiation during data binding
    public void printInfo() {
        System.out.println("MemberDTO Data: [Name=" + name + ", Email=" + email + ", Plan=" + planType + "]");
    }
}