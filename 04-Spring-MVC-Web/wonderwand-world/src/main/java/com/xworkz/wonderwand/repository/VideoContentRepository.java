package com.xworkz.wonderwand.repository;

import com.xworkz.wonderwand.entity.VideoContentEntity;
import java.util.List;

// Interface defining the Data Access Object (DAO) contract for WonderWand video metadata
public interface VideoContentRepository {

    // Persists a new VideoContentEntity (Draft or Final) into the database
    boolean save(VideoContentEntity entity);

    // Retrieves a specific video's persistent data using its unique Primary Key (ID)
    VideoContentEntity findById(int id);

    // Returns a complete list of all video records stored in the content management system
    List<VideoContentEntity> findAll();

    // Synchronizes changes to a video's status or description back to the database
    boolean update(VideoContentEntity entity);

    // Permanently removes a video's metadata from the system based on its ID
    boolean delete(int id);
}