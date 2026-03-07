package com.xworkz.library.repository;

import com.xworkz.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LibraryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // --- TOPIC: JDBC Batch Processing ---
    public void insertBatch(List<Book> books) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";

        // Efficiently maps the Java List to a single database transaction
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // Binding data from the Entity to the SQL parameters (?, ?)
                ps.setString(1, books.get(i).getTitle());
                ps.setString(2, books.get(i).getAuthor());
            }

            @Override
            public int getBatchSize() {
                // Tells the JDBC driver how many records to prepare
                return books.size();
            }
        });
    }
}