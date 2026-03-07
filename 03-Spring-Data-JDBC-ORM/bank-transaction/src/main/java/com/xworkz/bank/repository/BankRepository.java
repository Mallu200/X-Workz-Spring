package com.xworkz.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Marks this class as a Data Access Object (DAO) that handles all database operations for the Bank module
@Repository
public class BankRepository {

    // Automatically injects the JdbcTemplate bean for simplified SQL execution and exception handling
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieves a single balance value from the accounts table based on the unique account ID
    public double getBalance(int accId) {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        // queryForObject automatically handles ResultSet extraction and maps the result to a Double
        return jdbcTemplate.queryForObject(sql, Double.class, accId);
    }

    // Executes an UPDATE statement to modify the account balance for the specified account ID
    public void updateBalance(int accId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        // jdbcTemplate.update returns the number of rows affected and manages the PreparedStatement internally
        jdbcTemplate.update(sql, newBalance, accId);
    }
}