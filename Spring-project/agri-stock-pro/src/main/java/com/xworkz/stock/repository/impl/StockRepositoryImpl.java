package com.xworkz.stock.repository.impl;

import com.xworkz.stock.entity.StockEntity;
import com.xworkz.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StockRepositoryImpl implements StockRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    // Persists a new StockEntity into the database using a manual transaction.
    @Override
    public boolean save(StockEntity entity) {
        System.out.println("69-REPO: Attempting to save entity: " + entity.getItemName());
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
            System.out.println("70-REPO: Entity saved successfully with ID: " + entity.getId());
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("71-REPO: Error saving entity: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // Executes a NamedQuery to fetch the complete list of stock items.
    @Override
    public List<StockEntity> findAll() {
        System.out.println("72-REPO: Fetching all stock entries from DB...");
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Query query = em.createNamedQuery("fetchAllStock");
            List<StockEntity> list = query.getResultList();
            System.out.println("73-REPO: Total records retrieved: " + list.size());
            return list;
        } catch (Exception e) {
            System.err.println("74-REPO: Failed to fetch stock list: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // Retrieves a specific stock record by its primary key ID.
    @Override
    public StockEntity findById(int id) {
        System.out.println("75-REPO: Searching for entity with ID: " + id);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            StockEntity entity = em.find(StockEntity.class, id);
            System.out.println("76-REPO: Found entity: " + (entity != null ? entity.getItemName() : "None"));
            return entity;
        } catch (Exception e) {
            System.err.println("77-REPO: Error during findById for " + id + ": " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // Updates an existing record in the database using the merge operation.
    @Override
    public boolean update(StockEntity entity) {
        System.out.println("78-REPO: Requesting update for ID: " + entity.getId());
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
            tx.commit();
            System.out.println("79-REPO: Update successful for " + entity.getItemName());
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("80-REPO: Update failed for ID " + entity.getId() + ": " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // Locates and removes a stock record from the table based on ID.
    @Override
    public boolean delete(int id) {
        System.out.println("81-REPO: Attempting to delete record ID: " + id);
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            StockEntity entity = em.find(StockEntity.class, id);
            if (entity != null) {
                em.remove(entity);
                tx.commit();
                System.out.println("82-REPO: Record ID " + id + " deleted.");
                return true;
            }
            System.out.println("83-REPO: Delete failed - Record ID " + id + " not found.");
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("84-REPO: Exception during deletion: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // Performs a keyword search using the JPQL LIKE operator for flexible matching.
    @Override
    public List<StockEntity> findByName(String name) {
        System.out.println("85-REPO: Searching database for item name like: " + name);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM StockEntity s WHERE s.itemName LIKE :name";
            Query query = em.createQuery(jpql);
            query.setParameter("name", "%" + name + "%");
            List<StockEntity> results = query.getResultList();
            System.out.println("86-REPO: Search returned " + results.size() + " matches.");
            return results;
        } catch (Exception e) {
            System.err.println("87-REPO: Search operation failed: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}