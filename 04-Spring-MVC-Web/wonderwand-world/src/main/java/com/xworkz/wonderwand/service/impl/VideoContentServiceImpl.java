package com.xworkz.wonderwand.service.impl;

import com.xworkz.wonderwand.dto.VideoContentDTO;
import com.xworkz.wonderwand.entity.VideoContentEntity;
import com.xworkz.wonderwand.repository.VideoContentRepository;
import com.xworkz.wonderwand.service.VideoContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // Stereotype annotation identifying this class as the business logic service
public class VideoContentServiceImpl implements VideoContentService {

    @Autowired // Injecting the VideoContentRepository for database interaction
    private VideoContentRepository repository;

    @Override
    @Transactional // Ensures the save operation is atomic and persistent
    public boolean validateAndSave(VideoContentDTO dto) {
        System.out.println("WonderWandService: Validating content for Title: " + dto.getTitle()); // Trace for title

        VideoContentEntity entity = new VideoContentEntity();
        // Copies metadata from DTO to Entity for Hibernate persistence
        BeanUtils.copyProperties(dto, entity);

        System.out.println("WonderWandService: Mascot Activity assigned: " + dto.getMascotActivity()); // Trace for Bamboo activity
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true) // Optimizes DB performance for read-only library retrieval
    public List<VideoContentDTO> fetchAll() {
        System.out.println("WonderWandService: Fetching all video content logs from database...");

        List<VideoContentEntity> entities = repository.findAll();
        List<VideoContentDTO> dtos = new ArrayList<>();

        // Iterates through entities to prepare DTOs for the view layer
        for (VideoContentEntity entity : entities) {
            VideoContentDTO dto = new VideoContentDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }

        System.out.println("WonderWandService: Total videos prepared for dashboard: " + dtos.size());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public VideoContentDTO fetchById(int id) {
        System.out.println("WonderWandService: Retrieving production details for Video ID: " + id);

        VideoContentEntity entity = repository.findById(id);
        if (entity != null) {
            VideoContentDTO dto = new VideoContentDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }

        System.err.println("WonderWandService: Record not found for ID: " + id);
        return null;
    }

    @Override
    @Transactional // Required to commit status changes (e.g., Draft to Uploaded)
    public boolean update(VideoContentDTO dto) {
        System.out.println("WonderWandService: Updating production status/metadata for: " + dto.getTitle());

        VideoContentEntity entity = new VideoContentEntity();
        BeanUtils.copyProperties(dto, entity);

        return repository.update(entity);
    }

    @Override
    @Transactional // Required for secure deletion from the content library
    public boolean delete(int id) {
        System.out.println("WonderWandService: Requesting removal of content record ID: " + id);
        return repository.delete(id);
    }
}