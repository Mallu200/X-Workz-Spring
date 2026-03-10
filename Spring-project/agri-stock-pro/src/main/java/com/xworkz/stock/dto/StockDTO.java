package com.xworkz.stock.dto;

import com.xworkz.stock.constant.StockCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    private int id; // Unique identifier for stock updates and deletions

    @NotEmpty(message = "Item name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 chars")
    private String itemName;

    @NotNull(message = "Please select a category")
    private StockCategory category;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @NotEmpty(message = "Supplier name is required")
    private String supplier;

    // Initializes a default StockDTO instance for form binding in the UI.
    public void init() {
        System.out.println("62-DTO: Initializing empty StockDTO for model binding.");
        try {
            // Logic for default values if needed
            System.out.println("63-DTO: Default DTO ready for user input.");
        } catch (Exception e) {
            System.err.println("64-DTO: Error during DTO initialization: " + e.getMessage());
        }
    }
}