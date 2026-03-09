package com.xworkz.freshharvest.controller;

import com.xworkz.freshharvest.dto.ProduceDTO;
import com.xworkz.freshharvest.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype identifying this class as the web layer handler for FreshHarvest
@RequestMapping("/produce") // Base URL mapping for all inventory-related operations
public class ProduceController {

    @Autowired // Injecting the service layer for supply chain business logic
    private ProduceService service;

    @GetMapping("/") // Landing page for the Organic Ledger
    public String showIndex() {
        System.out.println("ProduceController: Navigating to FreshHarvest index page."); // Navigation trace
        return "index";
    }

    @GetMapping("/register") // Displays the harvest entry form
    public String showRegisterPage(Model model) {
        System.out.println("ProduceController: Opening Produce Registration page..."); // Form initialization trace
        try {
            if (!model.containsAttribute("produceDto")) {
                ProduceDTO dto = new ProduceDTO();
                dto.setId(0); // ID 0 indicates a fresh harvest entry that hasn't been saved yet
                model.addAttribute("produceDto", dto); // Binds empty DTO to the form:form tags
                System.out.println("ProduceController: Initialized new ProduceDTO.");
            }
        } catch (Exception e) {
            System.err.println("Exception in showRegisterPage: " + e.getMessage()); // Error trace
            model.addAttribute("error", "System error while loading the form.");
        }
        return "AddProduce";
    }

    @PostMapping("/save") // Handles persistence for both registration and status updates
    public String saveProduce(@Valid @ModelAttribute("produceDto") ProduceDTO dto,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        System.out.println("ProduceController: save/update request for Item: " + dto.getName());

        try {
            if (result.hasErrors()) { // Checks JSR-303 constraints like quantity and shelf life
                System.out.println("ProduceController: Validation failed! Errors: " + result.getErrorCount());
                return (dto.getId() > 0) ? "UpdateProduce" : "AddProduce"; // Returns to the respective form if invalid
            }

            boolean success;
            String message;

            if (dto.getId() > 0) { // Condition to check if updating an existing record
                System.out.println("ProduceController: Updating inventory for ID: " + dto.getId());
                success = service.update(dto);
                message = "Produce record for " + dto.getName() + " updated successfully!";
            } else { // Logic for new harvest registration
                System.out.println("ProduceController: Saving new harvest registration.");
                success = service.validateAndSave(dto);
                message = "Organic Produce " + dto.getName() + " registered in warehouse!";
            }

            if (success) {
                System.out.println("ProduceController: Transaction successful. Redirecting to inventory list.");
                redirectAttributes.addFlashAttribute("msg", message); // Persists success message across redirect
                return "redirect:/produce/viewAll";
            } else {
                System.out.println("ProduceController: Service layer failed to finalize record.");
                model.addAttribute("dbError", "System could not process the inventory record.");
                return (dto.getId() > 0) ? "UpdateProduce" : "AddProduce";
            }

        } catch (Exception e) {
            System.err.println("Database Error in saveProduce: " + e.getMessage()); // Critical error trace
            model.addAttribute("dbError", "Critical System Error: " + e.getMessage());
            return (dto.getId() > 0) ? "UpdateProduce" : "AddProduce";
        }
    }

    @GetMapping("/viewAll") // Fetches and displays the full organic inventory
    public String viewAllProduce(Model model) {
        System.out.println("ProduceController: Fetching organic inventory history..."); // Data fetch trace
        try {
            List<ProduceDTO> list = service.fetchAll();
            System.out.println("ProduceController: Records retrieved: " + (list != null ? list.size() : 0));
            model.addAttribute("produceList", list); // Passes inventory list to ViewInventory.jsp
        } catch (Exception e) {
            System.err.println("Error in viewAllProduce: " + e.getMessage());
            model.addAttribute("error", "Could not retrieve organic inventory history.");
        }
        return "ViewInventory";
    }

    @GetMapping("/edit") // Loads a specific batch's metadata for modification
    public String showEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("ProduceController: Fetching record ID: " + id + " for update."); // Edit trace
        try {
            ProduceDTO dto = service.fetchById(id);
            if (dto != null) {
                model.addAttribute("produceDto", dto); // Populates the update form
                return "UpdateProduce";
            } else {
                System.out.println("ProduceController: Record not found for ID: " + id);
                return "redirect:/produce/viewAll";
            }
        } catch (Exception e) {
            System.err.println("Exception in showEditPage: " + e.getMessage());
            return "redirect:/produce/viewAll";
        }
    }

    @GetMapping("/delete") // Removes a produce record from the management ledger
    public String deleteProduce(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("ProduceController: Requesting removal of record ID: " + id); // Delete trace
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                System.out.println("ProduceController: Successfully removed record ID: " + id);
                redirectAttributes.addFlashAttribute("deleteMsg", "Produce record removed from inventory.");
            } else {
                System.out.println("ProduceController: Removal failed. Target ID " + id + " not found.");
                redirectAttributes.addFlashAttribute("error", "Unable to remove inventory record.");
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteProduce: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Deletion error: " + e.getMessage());
        }
        return "redirect:/produce/viewAll";
    }
}