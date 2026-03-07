package com.xworkz.clinic.repository;

import com.xworkz.clinic.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Marks this as a Data Access Object (DAO) managed by Spring
@Repository
public class ClinicRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * TOPIC 1: JdbcTemplate (Direct SQL)
     * Best for simple aggregations and performance-heavy reports.
     * Spring handles Opening/Closing connections and Exception Translation.
     */
    public int countPatients() {
        String sql = "SELECT COUNT(*) FROM patient_table";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * TOPIC 2: Hibernate (ORM)
     * Best for saving/updating Objects without writing INSERT statements.
     * Works seamlessly with @Transactional in the Service layer.
     */
    public void saveWithHibernate(Patient patient) {
        // Gets the session bound to the current transaction
        Session session = sessionFactory.getCurrentSession();
        session.save(patient);
    }
}