package com.xworkz.fittrack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // Generates Boilerplate code for getters, setters, and toString
@AllArgsConstructor // Generates a constructor with all fields for easy manual instantiation
@Entity // Marks this class as a JPA Entity to be mapped to a database table
@Table(name = "member_table") // Defines the specific table name in the MySQL database
public class MemberEntity {

    public MemberEntity() {
        // Trace to see when Hibernate is fetching or creating an entity instance
        System.out.println("MemberEntity: Default constructor called (Likely by Hibernate).");
    }

    @Id // Marks this field as the Primary Key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells MySQL to handle auto-increment logic
    @Column(name = "m_id") // Maps the field to the 'm_id' column
    private int id;

    @Column(name = "m_name", nullable = false) // Maps to 'm_name' and prevents null values at DB level
    private String name;

    @Column(name = "m_email", nullable = false) // Ensures email is mandatory in the database
    private String email;

    @Column(name = "m_phone") // Maps to 'm_phone' column for storing contact numbers
    private String phoneNumber;

    @Column(name = "m_plan") // Maps to 'm_plan' column for membership details
    private String planType;

    @Column(name = "m_trainer") // Maps to 'm_trainer' column for assigned gym staff
    private String trainerName;

    @Column(name = "m_weight") // Maps to 'm_weight' column for fitness tracking
    private double weight;

    // Custom SOP to verify data when an entity is loaded or saved
    public void printEntityStatus() {
        System.out.println("Current MemberEntity State: [ID=" + id + ", Name=" + name + "]");
    }
}