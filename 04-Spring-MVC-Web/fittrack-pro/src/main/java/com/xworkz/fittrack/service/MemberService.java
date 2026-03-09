package com.xworkz.fittrack.service;

import com.xworkz.fittrack.dto.MemberDTO;
import java.util.List;

// Defines the business logic contract for managing gym members
public interface MemberService {

    // Validates business rules (e.g., unique email) and saves the member to the database
    boolean validateAndSave(MemberDTO dto);

    // Retrieves a specific member's data based on their unique Primary Key
    MemberDTO fetchById(int id);

    // Returns a list of all registered members to be displayed in the UI
    List<MemberDTO> fetchAll();

    // Processes modifications to an existing member's profile
    boolean update(MemberDTO dto);

    // Removes a member record from the system based on their ID
    boolean delete(int id);
}