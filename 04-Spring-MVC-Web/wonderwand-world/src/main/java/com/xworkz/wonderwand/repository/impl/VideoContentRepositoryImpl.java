package com.xworkz.wonderwand.repository.impl;

import com.xworkz.wonderwand.entity.VideoContentEntity;
import com.xworkz.wonderwand.repository.VideoContentRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // Stereotype marking this as a Data Access Object for the WonderWand database
public class VideoContentRepositoryImpl implements VideoContentRepository {

    @PersistenceContext // Injects the container-managed EntityManager for DB operations
    private EntityManager entityManager;

    @Override
    public boolean save(VideoContentEntity entity) {
        // Trace to confirm title being persisted in the video_content_table
        System.out.println("WonderWandRepo: Saving content: " + entity.getTitle());

        // Transitions the video entity from transient to managed state
        entityManager.persist(entity);
        return true;
    }

    @Override
    public VideoContentEntity findById(int id) {
        // Trace for primary key lookup of specific video metadata
        System.out.println("WonderWandRepo: Searching for Video ID: " + id);

        // Direct retrieval using the primary key
        return entityManager.find(VideoContentEntity.class, id);
    }

    @Override
    public List<VideoContentEntity> findAll() {
        System.out.println("WonderWandRepo: Fetching complete video library via JPQL...");

        // JPQL query selecting all instances of VideoContentEntity
        String jpql = "SELECT v FROM VideoContentEntity v";
        return entityManager.createQuery(jpql, VideoContentEntity.class).getResultList();
    }

    @Override
    public boolean update(VideoContentEntity entity) {
        // Trace to track status updates (e.g., Draft to Uploaded)
        System.out.println("WonderWandRepo: Updating record for: " + entity.getTitle());

        // Merges the state of a detached entity into the current persistence context
        entityManager.merge(entity);
        return true;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("WonderWandRepo: Requesting removal of record ID: " + id);

        // Finds the managed entity first to ensure Hibernate can track the removal
        VideoContentEntity entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
            System.out.println("WonderWandRepo: Record successfully removed from database.");
            return true;
        }

        System.err.println("WonderWandRepo: Deletion failed - ID " + id + " not found.");
        return false;
    }
}