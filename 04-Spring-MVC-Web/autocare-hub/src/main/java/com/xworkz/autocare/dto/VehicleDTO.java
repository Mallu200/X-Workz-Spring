package com.xworkz.autocare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data // Generates getters, setters, and toString for vehicle data
@AllArgsConstructor // Generates constructor for all fields
@NoArgsConstructor // Default constructor required for Spring form backing
public class VehicleDTO {

    // Unique ID for the vehicle record, used for updates and deletes
    private int id;

    @NotBlank(message = "Owner name is required") // Ensures owner name is not null or empty
    private String ownerName;

    @NotBlank(message = "License plate is required")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", message = "Invalid Format (e.g., KA01AB1234)") // Validates Indian vehicle plate format
    private String licensePlate;

    @NotBlank(message = "Vehicle model is required") // Ensures the car model is specified
    private String modelName;

    @Min(value = 1, message = "Mileage must be positive") // Validates that mileage is a realistic positive number
    private int currentMileage;

    @NotBlank(message = "Service type is required") // Tracks specific maintenance task (e.g., Oil Change)
    private String serviceType;

    // Custom method to trace DTO state during form submission
    public void logDtoDetails() {
        System.out.println("VehicleDTO Trace: [Owner: " + ownerName + ", Plate: " + licensePlate + ", Service: " + serviceType + "]");
    }
}