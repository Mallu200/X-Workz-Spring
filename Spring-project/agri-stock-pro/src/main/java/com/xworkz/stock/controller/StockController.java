package com.xworkz.stock.controller;

import com.xworkz.stock.constant.StockCategory;
import com.xworkz.stock.dto.StockDTO;
import com.xworkz.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // Fetches all stock items from the database to display on the main dashboard.
    @GetMapping("/view")
    public String viewAllStock(Model model) {
        System.out.println("36-CTRL: Entering viewAllStock method...");
        try {
            List<StockDTO> list = stockService.getAllStock();
            model.addAttribute("stocks", list);
            System.out.println("37-CTRL: Successfully retrieved " + list.size() + " stock items.");
            return "StockDashboard";
        } catch (Exception e) {
            System.err.println("38-CTRL: Error retrieving stock list: " + e.getMessage());
            return "ErrorPage";
        }
    }

    // Prepares the model with a fresh DTO and category list for the 'Add Stock' form.
    @GetMapping("/add")
    public String showAddPage(Model model) {
        System.out.println("39-CTRL: Navigating to Add Stock page...");
        try {
            model.addAttribute("dto", new StockDTO());
            model.addAttribute("categories", Arrays.asList(StockCategory.values()));
            System.out.println("40-CTRL: Dropdown categories loaded successfully.");
            return "AddStock";
        } catch (Exception e) {
            System.err.println("41-CTRL: Error loading Add Stock page: " + e.getMessage());
            return "redirect:/stock/view";
        }
    }

    // Processes the form submission to save a new stock entry into the system.
    @PostMapping("/add")
    public String saveStock(@ModelAttribute StockDTO dto, RedirectAttributes ra) {
        System.out.println("42-CTRL: Attempting to save new stock: " + dto.getItemName());
        try {
            boolean saved = stockService.validateAndSave(dto);
            if (saved) {
                System.out.println("43-CTRL: Stock saved successfully.");
                ra.addFlashAttribute("message", "Stock item added successfully!");
                return "redirect:/stock/view";
            }
            System.out.println("44-CTRL: Validation failed for item: " + dto.getItemName());
            ra.addFlashAttribute("error", "Failed to add stock. Please check details.");
            return "redirect:/stock/add";
        } catch (Exception e) {
            System.err.println("45-CTRL: Exception during save process: " + e.getMessage());
            return "redirect:/stock/add";
        }
    }

    // Retrieves specific stock details by ID to populate the edit form.
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable int id, Model model) {
        System.out.println("46-CTRL: Entering edit mode for ID: " + id);
        try {
            StockDTO dto = stockService.findById(id);
            model.addAttribute("dto", dto);
            model.addAttribute("categories", Arrays.asList(StockCategory.values()));
            System.out.println("47-CTRL: Details for " + dto.getItemName() + " loaded for editing.");
            return "EditStock";
        } catch (Exception e) {
            System.err.println("48-CTRL: Error finding stock for ID " + id + ": " + e.getMessage());
            return "redirect:/stock/view";
        }
    }

    // Updates an existing stock record and returns the user to the dashboard.
    @PostMapping("/update")
    public String updateStock(@ModelAttribute StockDTO dto, RedirectAttributes ra) {
        System.out.println("49-CTRL: Requesting update for item ID: " + dto.getId());
        try {
            boolean updated = stockService.updateStock(dto);
            if (updated) {
                System.out.println("50-CTRL: Update confirmed for " + dto.getItemName());
                ra.addFlashAttribute("message", "Stock updated successfully!");
            } else {
                System.out.println("51-CTRL: Update failed for ID: " + dto.getId());
                ra.addFlashAttribute("error", "Update failed.");
            }
            return "redirect:/stock/view";
        } catch (Exception e) {
            System.err.println("52-CTRL: Exception during update: " + e.getMessage());
            return "redirect:/stock/view";
        }
    }

    // Removes a stock item from the database using its unique identifier.
    @GetMapping("/delete/{id}")
    public String deleteStock(@PathVariable int id, RedirectAttributes ra) {
        System.out.println("53-CTRL: Deletion request received for ID: " + id);
        try {
            boolean deleted = stockService.deleteById(id);
            if (deleted) {
                System.out.println("54-CTRL: Item ID " + id + " deleted successfully.");
                ra.addFlashAttribute("message", "Item deleted successfully.");
            }
            return "redirect:/stock/view";
        } catch (Exception e) {
            System.err.println("55-CTRL: Error deleting item " + id + ": " + e.getMessage());
            return "redirect:/stock/view";
        }
    }

    // Filters the stock list based on the search query provided by the user.
    @GetMapping("/search")
    public String searchStock(@RequestParam String itemName, Model model) {
        System.out.println("56-CTRL: Searching for item name containing: " + itemName);
        try {
            List<StockDTO> results = stockService.searchByItemName(itemName);
            model.addAttribute("stocks", results);
            model.addAttribute("searchQuery", itemName);
            System.out.println("57-CTRL: Search completed. Found " + results.size() + " matches.");
            return "StockDashboard";
        } catch (Exception e) {
            System.err.println("58-CTRL: Search operation failed: " + e.getMessage());
            return "redirect:/stock/view";
        }
    }
}