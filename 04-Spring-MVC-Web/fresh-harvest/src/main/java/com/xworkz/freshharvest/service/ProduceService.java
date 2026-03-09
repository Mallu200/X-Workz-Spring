package com.xworkz.freshharvest.service;

import com.xworkz.freshharvest.dto.ProduceDTO;
import java.util.List;

// Interface defining the core business logic for the FreshHarvest inventory system
public interface ProduceService {

    // Validates produce data (Name, Harvest Date, Shelf Life) before saving to the database
    boolean validateAndSave(ProduceDTO dto);

    // Retrieves specific inventory details using the unique primary key ID
    ProduceDTO fetchById(int id);

    // Returns the complete list of organic produce for the dashboard view
    List<ProduceDTO> fetchAll();

    // Updates existing record details, such as changing status from 'Fresh' to 'Near-Expiry'
    boolean update(ProduceDTO dto);

    // Permanently removes a produce entry from the management ledger
    boolean delete(int id);
}