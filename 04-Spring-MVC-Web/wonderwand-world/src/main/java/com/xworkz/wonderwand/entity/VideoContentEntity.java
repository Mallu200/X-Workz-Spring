package com.xworkz.wonderwand.entity;

import lombok.Data;
import javax.persistence.*;

@Data // Generates Boilerplate code: Getters, Setters, and toString for the Video record
@Entity // Identifies this class as a persistent database entity for Hibernate
@Table(name = "video_content_table") // Sets the physical table name in your spring_db schema
public class VideoContentEntity {

    public VideoContentEntity() {
        // Trace to confirm when Hibernate instantiates the entity during fetch operations
        System.out.println("VideoContentEntity: Default constructor invoked (Entity hydration).");
    }

    @Id // Defines the primary key for the video record
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Instructs MySQL to handle auto-increment logic
    private int id;

    @Column(name = "v_title", nullable = false) // Maps to 'v_title' column; essential for SEO tracking
    private String title;

    @Column(name = "v_category") // Maps to 'v_category' column for educational grouping
    private String category;

    @Column(name = "v_description", length = 500) // Extended length for detailed educational scripts
    private String description;

    @Column(name = "v_mascot_activity") // Tracks specific character movements (e.g., Bamboo eating/dancing)
    private String mascotActivity;

    @Column(name = "v_duration") // Maps to 'v_duration' column for production planning
    private int durationInMinutes;

    @Column(name = "v_status") // Maps to 'v_status' column to track the YouTube upload pipeline
    private String status;

    // Helper trace to log entity state during Repository save or find operations
    public void logVideoEntity() {
        System.out.println("VideoContentEntity [ID=" + id + ", Title=" + title + ", Status=" + status + "]");
    }
}