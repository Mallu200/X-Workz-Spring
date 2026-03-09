package com.xworkz.autocare.service;

import com.xworkz.autocare.dto.VehicleDTO;
import java.util.List;

// Interface defining the business operations for the AutoCare management system
public interface VehicleService {

    // Validates vehicle business rules (like plate format) and persists the record
    boolean validateAndSave(VehicleDTO dto);

    // Retrieves a single vehicle's service history by ID for editing or viewing
    VehicleDTO fetchById(int id);

    // Returns the complete service log for all vehicles registered in the system
    List<VehicleDTO> fetchAll();

    // Updates existing vehicle information such as mileage or service type
    boolean update(VehicleDTO dto);

    // Removes a vehicle record from the system based on its unique ID
    boolean delete(int id);
}