package com.xworkz.eduportal.repository.impl;

import com.xworkz.eduportal.entity.StudentEntity;
import com.xworkz.eduportal.repository.StudentRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import java.util.List;

@Repository // Stereotype marking this as a Data Access Object for the EduPortal
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext // Injects the container-managed EntityManager for database interaction
    private EntityManager entityManager;

    @Override
    public boolean save(StudentEntity entity) {
        System.out.println("StudentRepositoryImpl: Persisting new student: " + entity.getName()); // Trace for INSERT

        // Transitions the entity from transient to managed state
        entityManager.persist(entity);

        System.out.println("StudentRepositoryImpl: Student record persisted successfully.");
        return true;
    }

    @Override
    public StudentEntity findById(int id) {
        System.out.println("StudentRepositoryImpl: Finding student record by ID: " + id); // Trace for Primary Key search

        // Uses the built-in find method for direct Primary Key retrieval
        return entityManager.find(StudentEntity.class, id);
    }

    @Override
    public StudentEntity findByUsn(String usn) {
        System.out.println("StudentRepositoryImpl: Executing JPQL search for USN: " + usn); // Trace for custom search
        try {
            // JPQL query using a named parameter to prevent SQL injection
            String jpql = "SELECT s FROM StudentEntity s WHERE s.usn = :usn";
            return entityManager.createQuery(jpql, StudentEntity.class)
                    .setParameter("usn", usn)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Standard catch block for when the specific USN doesn't exist in the database
            System.err.println("StudentRepositoryImpl: No student found with USN: " + usn);
            return null;
        }
    }

    @Override
    public List<StudentEntity> findAll() {
        System.out.println("StudentRepositoryImpl: Fetching all students from student_table..."); // Trace for SELECT ALL

        String jpql = "SELECT s FROM StudentEntity s";
        List<StudentEntity> list = entityManager.createQuery(jpql, StudentEntity.class).getResultList();

        System.out.println("StudentRepositoryImpl: Total students retrieved: " + list.size());
        return list;
    }

    @Override
    public boolean update(StudentEntity entity) {
        System.out.println("StudentRepositoryImpl: Merging updates for Student ID: " + entity.getId()); // Trace for UPDATE

        // Synchronizes the state of a detached entity back to the database
        entityManager.merge(entity);

        System.out.println("StudentRepositoryImpl: Entity state merged successfully.");
        return true;
    }

    @Override
    public boolean delete(int id) {
        System.out.println("StudentRepositoryImpl: Initiating removal of student ID: " + id); // Trace for DELETE

        // Fetching the entity first to ensure it is in a managed state before removal
        StudentEntity entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
            System.out.println("StudentRepositoryImpl: Record removed from database.");
            return true;
        }

        System.err.println("StudentRepositoryImpl: Deletion failed. ID " + id + " not found.");
        return false;
    }
}