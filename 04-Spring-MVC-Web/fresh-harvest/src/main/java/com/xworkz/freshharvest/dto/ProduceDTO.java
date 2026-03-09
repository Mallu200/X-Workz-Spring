package com.xworkz.freshharvest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;

@Data // Generates Boilerplate code: Getters, Setters, and toString for FreshHarvest inventory
@NoArgsConstructor // Required for Spring's form-to-object binding mechanism
@AllArgsConstructor // Facilitates easy object creation during service-layer testing
public class ProduceDTO {

    // Unique identifier for the produce record in the database
    private int id;

    @NotBlank(message = "Produce name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name; // e.g., Organic Carrots, Alphonso Mango

    @NotBlank(message = "Category is mandatory")
    private String category; // e.g., Vegetables, Fruits, Grains, Dairy

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 1, message = "Quantity must be at least 1 unit")
    private Integer quantity; // Use Integer wrapper to allow @NotNull to catch empty form fields

    @NotBlank(message = "Unit of measurement is required")
    private String unit; // e.g., KG, Bunches, Liters, Boxes

    @NotBlank(message = "Harvest Date is required")
    private String harvestDate; // Format: YYYY-MM-DD for consistency across the portal

    @NotNull(message = "Shelf life is required")
    @Max(value = 365, message = "Shelf life cannot exceed 1 year for fresh produce")
    private Integer shelfLifeInDays;

    @Pattern(regexp = "^(Fresh|Ripening|Near-Expiry|Expired)$",
            message = "Status must be Fresh, Ripening, Near-Expiry, or Expired")
    private String status; // Tracks the biological state of the inventory items

    // Custom trace to verify the produce state before it reaches the service layer
    public void logProduceInfo() {
        System.out.println("ProduceDTO Trace: [Name=" + name + ", Status=" + status + ", HarvestDate=" + harvestDate + "]");
    }
}