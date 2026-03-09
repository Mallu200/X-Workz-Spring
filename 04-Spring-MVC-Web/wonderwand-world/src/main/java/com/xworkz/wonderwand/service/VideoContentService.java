package com.xworkz.wonderwand.service;

import com.xworkz.wonderwand.dto.VideoContentDTO;
import java.util.List;

// Interface defining the core business logic for managing WonderWand YouTube content
public interface VideoContentService {

    // Validates content rules (SEO length, Mascot consistency) and saves the metadata to the database
    boolean validateAndSave(VideoContentDTO dto);

    // Retrieves a specific video's production details using its unique primary key ID
    VideoContentDTO fetchById(int id);

    // Returns a complete list of all videos in the WonderWand library for the dashboard view
    List<VideoContentDTO> fetchAll();

    // Updates production details, such as moving a video status from 'Draft' to 'Uploaded'
    boolean update(VideoContentDTO dto);

    // Removes a video entry from the management system based on its unique ID
    boolean delete(int id);
}