package com.xworkz.fittrack.repository;

import com.xworkz.fittrack.entity.MemberEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository // Marks this as a Data Access Object (DAO) in the Spring context
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext // Injects a thread-safe EntityManager proxy for database interaction
    private EntityManager entityManager;

    @Override
    public boolean save(MemberEntity entity) {
        System.out.println("MemberRepositoryImpl: Saving new entity to DB: " + entity.getName()); // Trace for INSERT operation

        // JPA uses persist to transition a transient entity to managed state
        entityManager.persist(entity);

        System.out.println("MemberRepositoryImpl: Entity persisted successfully.");
        return true;
    }

    @Override
    public MemberEntity findById(int id) {
        System.out.println("MemberRepositoryImpl: Finding member by ID: " + id); // Trace for SELECT by ID

        // Retrieves the entity from the DB based on the Primary Key
        return entityManager.find(MemberEntity.class, id);
    }

    @Override
    public List<MemberEntity> findAll() {
        System.out.println("MemberRepositoryImpl: Fetching all records using JPQL..."); // Trace for SELECT ALL

        // Executes a Java Persistence Query Language statement to fetch all rows
        List<MemberEntity> list = entityManager.createQuery("SELECT m FROM MemberEntity m", MemberEntity.class)
                .getResultList();

        System.out.println("MemberRepositoryImpl: Total records fetched: " + list.size());
        return list;
    }

    @Override
    public boolean update(MemberEntity entity) {
        System.out.println("MemberRepositoryImpl: Merging changes for ID: " + entity.getId()); // Trace for UPDATE

        // JPA uses merge to update a detached entity back into the current persistence context
        entityManager.merge(entity);

        System.out.println("MemberRepositoryImpl: Entity updated successfully.");
        return true;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("MemberRepositoryImpl: Attempting to remove member ID: " + id); // Trace for DELETE

        MemberEntity entity = findById(id); // Must fetch the managed entity first before removal
        if (entity != null) {
            // Removes the record from the database
            entityManager.remove(entity);
            System.out.println("MemberRepositoryImpl: Member deleted successfully.");
            return true;
        }

        System.out.println("MemberRepositoryImpl: Delete failed. Member not found.");
        return false;
    }
}