package com.xworkz.eduportal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;

@Data // Generates Getters, Setters, and toString for student data
@NoArgsConstructor // Required for Spring's form-backing bean mechanism
@AllArgsConstructor // Useful for manual DTO creation in Service/Controller
public class StudentDTO {

    // Primary key used for identifying specific students during updates/deletes
    private int id;

    @NotBlank(message = "Student name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "USN is mandatory")
    /* Pattern for USN (Example: 1PR21EC045)
       Validates the specific seat number format used in regional universities
    */
    @Pattern(regexp = "^[0-9]{1}[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{3}$",
            message = "Invalid USN format (Example: 1PR21EC045)")
    private String usn;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid institutional email address")
    private String email;

    @NotBlank(message = "Please select a Department") // Tracks the academic branch (e.g., EC, CS)
    private String department;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA cannot be less than 0")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    private Double cgpa;

    @NotBlank(message = "Semester selection is required") // Tracks the current academic term
    private String semester;

    // Custom trace method to verify DTO state before passing to Service layer
    public void printDtoStatus() {
        System.out.println("StudentDTO Trace: [Name=" + name + ", USN=" + usn + ", Dept=" + department + "]");
    }
}