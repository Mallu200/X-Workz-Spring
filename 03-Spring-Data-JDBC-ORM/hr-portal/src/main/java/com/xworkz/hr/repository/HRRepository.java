package com.xworkz.hr.repository;

import com.xworkz.hr.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Marks this as the Data Access Layer in the Spring Context
public class HRRepository {

    @Autowired // Injects the SessionFactory configured in HRConfig
    private SessionFactory sessionFactory;

    /**
     * TOPIC 1: Hibernate Persistence
     * Saves the Employee object directly to the 'employee_details' table.
     */
    public void saveEmployee(Employee emp) {
        // Automatically uses the transaction opened by HRService
        sessionFactory.getCurrentSession().save(emp);
    }

    /**
     * TOPIC 2: HQL (Hibernate Query Language)
     * Queries based on the Java Entity name ("Employee"), not the MySQL table name.
     */
    public List<Employee> findByDept(String deptName) {
        Session session = sessionFactory.getCurrentSession();

        // HQL is case-sensitive regarding the class name: "Employee"
        String hql = "FROM Employee e WHERE e.department = :dName";

        // Uses Named Parameters (:dName) to prevent SQL Injection
        return session.createQuery(hql, Employee.class)
                .setParameter("dName", deptName)
                .list();
    }
}