package com.xworkz.autocare.entity;

import lombok.Data;
import javax.persistence.*;

@Data // Generates Getters, Setters, and toString for the Entity
@Entity // Specifies that this class is a JPA entity mapped to a database table
@Table(name = "vehicle_table") // Sets the specific table name in the 'spring_db' schema
public class VehicleEntity {

    public VehicleEntity() {
        // Trace to confirm when Hibernate instantiates the entity (e.g., during a find or fetch)
        System.out.println("VehicleEntity: Default constructor invoked by Hibernate/JPA.");
    }

    @Id // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Enables MySQL Auto-Increment logic
    private int id;

    @Column(name = "v_owner") // Maps the field to 'v_owner' column in MySQL
    private String ownerName;

    @Column(name = "v_plate") // Maps to 'v_plate' column for unique vehicle identification
    private String licensePlate;

    @Column(name = "v_model") // Maps to 'v_model' column storing car make/model
    private String modelName;

    @Column(name = "v_mileage") // Maps to 'v_mileage' for tracking service intervals
    private int currentMileage;

    @Column(name = "v_service") // Maps to 'v_service' column for maintenance type
    private String serviceType;

    // Helper method to log entity data during Repository operations
    public void printEntityInfo() {
        System.out.println("VehicleEntity [ID=" + id + ", Plate=" + licensePlate + ", Model=" + modelName + "]");
    }
}