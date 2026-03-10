package com.xworkz.stock.service;

import com.xworkz.stock.dto.StockDTO;
import java.util.List;

public interface StockService {

    // Validates the StockDTO and maps it to a StockEntity for database persistence.
    boolean validateAndSave(StockDTO dto);

    // Retrieves all stock records from the repository and converts them into a list of DTOs.
    List<StockDTO> getAllStock();

    // Fetches a specific stock record by ID and converts it to a DTO for the edit form.
    StockDTO findById(int id);

    // Updates existing stock information after validating the provided DTO data.
    boolean updateStock(StockDTO dto);

    // Coordinates with the repository to remove a stock item based on its unique ID.
    boolean deleteById(int id);

    // Executes business logic for filtering stock items by name and returns matching DTOs.
    List<StockDTO> searchByItemName(String name);
}