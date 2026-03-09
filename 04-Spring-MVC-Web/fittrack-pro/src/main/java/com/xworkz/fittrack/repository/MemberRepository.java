package com.xworkz.fittrack.repository;

import com.xworkz.fittrack.entity.MemberEntity;
import java.util.List;

// Interface for Database operations related to MemberEntity
public interface MemberRepository {

    // Persists a new member entity into the MySQL database
    boolean save(MemberEntity entity);

    // Retrieves a specific member record from the table using the Primary Key
    MemberEntity findById(int id);

    // Returns a complete list of all records currently stored in the member_table
    List<MemberEntity> findAll();

    // Merges changes from a detached entity back into the database session
    boolean update(MemberEntity entity);

    // Removes a specific member record from the database based on the unique ID
    boolean delete(int id);
}