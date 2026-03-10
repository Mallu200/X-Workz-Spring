package com.xworkz.stock.constant;

import lombok.Getter;

@Getter
public enum StockCategory {
    SEEDS("Seeds"),
    FERTILIZER("Fertilizer"),
    PESTICIDE("Pesticide"),
    MACHINERY_SPARES("Machinery Spares"),
    TOOLS("Tools");

    private final String displayValue;

    // Initializes enum constants with their respective display names for the UI.
    StockCategory(String displayValue) {
        // We assign the value first to satisfy the 'final' requirement, then log/catch
        this.displayValue = displayValue;

        try {
            System.out.println("33-ENUM: Attempting to load constant: " + displayValue);
            if (this.displayValue == null) {
                throw new Exception("Display value cannot be null");
            }
            System.out.println("34-ENUM: Category '" + this.displayValue + "' successfully loaded.");
        } catch (Exception e) {
            // Logs any issues during the initialization phase
            System.err.println("35-ENUM: Initialization warning for '" + displayValue + "': " + e.getMessage());
        }
    }
}