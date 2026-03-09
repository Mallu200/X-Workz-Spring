package com.xworkz.wonderwand.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class VideoContentDTO {

    private int id;

    @NotBlank(message = "Video Title is required for YouTube")
    @Size(min = 10, max = 100, message = "Title should be between 10-100 characters for SEO")
    private String title;

    @NotBlank(message = "Educational Category is required")
    private String category; // e.g., Numbers, Alphabet, Moral Stories, Colors

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 20, message = "Please provide a detailed description for the kids")
    private String description;

    @NotBlank(message = "Mascot Activity is required")
    private String mascotActivity; // e.g., Bamboo is eating, Bamboo is dancing

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationInMinutes;

    @Pattern(regexp = "^(Draft|In-Production|Uploaded|Private)$",
            message = "Status must be Draft, In-Production, Uploaded, or Private")
    private String status;
}