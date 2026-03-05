package com.xworkz.db.bean;

// Defines the contract for various database implementations (MySQL, MongoDB, Oracle)
public interface DatabaseDriver {

    // Abstract method to handle the specific handshake and connection logic for a DB
    void connect();
}