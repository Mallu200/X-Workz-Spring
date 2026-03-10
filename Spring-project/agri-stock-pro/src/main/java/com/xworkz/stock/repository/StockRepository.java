package com.xworkz.stock.repository;

import com.xworkz.stock.entity.StockEntity;
import java.util.List;

public interface StockRepository {

    // Persists a new stock record into the stock_items table.
    boolean save(StockEntity entity);

    // Retrieves all existing stock records using the 'fetchAllStock' named query.
    List<StockEntity> findAll();

    // Locates a single stock entry based on its unique primary key.
    StockEntity findById(int id);

    // Synchronizes changes from an edited entity back to the database.
    boolean update(StockEntity entity);

    // Removes a stock record from the database using its ID.
    boolean delete(int id);

    // Filters stock items based on partial or full name matches.
    List<StockEntity> findByName(String name);
}