package com.xworkz.freshharvest.entity;

import lombok.Data;
import javax.persistence.*;

@Data // Generates Boilerplate code: Getters, Setters, and toString for the Produce record
@Entity // Identifies this class as a persistent database entity for Hibernate ORM
@Table(name = "organic_inventory") // Sets the physical table name in your spring_db schema
public class ProduceEntity {

    public ProduceEntity() {
        // Trace to confirm when Hibernate instantiates the entity during fetch operations
        System.out.println("ProduceEntity: Default constructor invoked (Entity hydration).");
    }

    @Id // Defines the primary key for the inventory record
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Instructs MySQL to handle auto-increment logic
    private int id;

    @Column(name = "p_name", nullable = false, length = 50) // Maps to 'p_name'; essential for inventory tracking
    private String name;

    @Column(name = "p_category") // Maps to 'p_category' column for produce grouping
    private String category;

    @Column(name = "p_quantity") // Tracks the numerical stock level in the warehouse
    private Integer quantity;

    @Column(name = "p_unit") // Maps to 'p_unit' (e.g., KG, Liters)
    private String unit;

    @Column(name = "p_harvest_date") // Stores the date of harvest for freshness tracking
    private String harvestDate;

    @Column(name = "p_shelf_life") // Maps to 'p_shelf_life' to calculate expiration alerts
    private Integer shelfLifeInDays;

    @Column(name = "p_status") // Maps to 'p_status' to track the Fresh/Ripening/Expired state
    private String status;

    // Helper trace to log entity state during Repository save or find operations
    public void logProduceEntity() {
        System.out.println("ProduceEntity [ID=" + id + ", Name=" + name + ", Status=" + status + "]");
    }
}