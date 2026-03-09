package com.xworkz.autocare.repository;

import com.xworkz.autocare.entity.VehicleEntity;
import java.util.List;

public interface VehicleRepository {

    // Saves a new vehicle or updates an existing one
    boolean save(VehicleEntity entity);

    // Finds a vehicle record by its primary key
    VehicleEntity findById(int id);

    // Retrieves all vehicle service records from the database
    List<VehicleEntity> findAll();

    // Updates specific vehicle details
    boolean update(VehicleEntity entity);

    // Removes a vehicle record based on ID
    boolean delete(int id);
}