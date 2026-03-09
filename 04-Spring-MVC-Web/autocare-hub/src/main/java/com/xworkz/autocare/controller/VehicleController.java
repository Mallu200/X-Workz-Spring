package com.xworkz.autocare.controller;

import com.xworkz.autocare.dto.VehicleDTO;
import com.xworkz.autocare.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype annotation identifying this class as a web request handler
@RequestMapping("/vehicle") // Base URL mapping for all automotive-related operations
public class VehicleController {

    @Autowired // Dependency injection for the business logic layer
    private VehicleService service;

    @GetMapping("/") // Maps to the initial landing page of the AutoCare module
    public String showIndex() {
        System.out.println("VehicleController: Navigating to AutoCare index page."); // Trace for navigation
        return "index";
    }

    @GetMapping("/register") // Displays the empty vehicle registration form
    public String showRegisterPage(Model model) {
        System.out.println("VehicleController: Loading Registration Form..."); // Trace for form initialization
        try {
            if (!model.containsAttribute("vehicleDto")) {
                VehicleDTO dto = new VehicleDTO();
                dto.setId(0); // ID 0 signals a fresh registration to the UI
                model.addAttribute("vehicleDto", dto); // Binds the DTO to the form:form tag
                System.out.println("VehicleController: Bound a new VehicleDTO to the model.");
            }
        } catch (Exception e) {
            System.err.println("Error in showRegisterPage: " + e.getMessage()); // Error trace
            model.addAttribute("error", "System error while loading the form.");
        }
        return "AddVehicle";
    }

    @PostMapping("/save") // Processes form submission for both adding and updating records
    public String saveVehicle(@Valid @ModelAttribute("vehicleDto") VehicleDTO dto,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        System.out.println("VehicleController: save/update request for License Plate: " + dto.getLicensePlate());

        try {
            if (result.hasErrors()) { // Checks JSR-303 validation (e.g., license plate pattern)
                System.out.println("VehicleController: Validation failed! Error count: " + result.getErrorCount());
                return (dto.getId() > 0) ? "UpdateVehicle" : "AddVehicle"; // Returns to form with errors
            }

            boolean success;
            String message;

            if (dto.getId() > 0) { // Condition to check if record already exists in DB
                System.out.println("VehicleController: Triggering update for ID: " + dto.getId());
                success = service.update(dto);
                message = "Vehicle record for " + dto.getLicensePlate() + " updated successfully!";
            } else { // New registration logic
                System.out.println("VehicleController: Triggering new registration save.");
                success = service.validateAndSave(dto);
                message = "Vehicle " + dto.getLicensePlate() + " registered successfully!";
            }

            if (success) {
                System.out.println("VehicleController: Success! Redirecting to view list.");
                redirectAttributes.addFlashAttribute("msg", message); // Flash attribute survives the redirect
                return "redirect:/vehicle/viewAll";
            } else {
                System.out.println("VehicleController: Service layer returned false for save/update.");
                model.addAttribute("dbError", "System could not process the vehicle record.");
                return (dto.getId() > 0) ? "UpdateVehicle" : "AddVehicle";
            }

        } catch (Exception e) {
            System.err.println("Database Exception in saveVehicle: " + e.getMessage());
            model.addAttribute("dbError", "Critical System Error: " + e.getMessage());
            return (dto.getId() > 0) ? "UpdateVehicle" : "AddVehicle";
        }
    }

    @GetMapping("/viewAll") // Handles the display of the complete vehicle log
    public String viewAllVehicles(Model model) {
        System.out.println("VehicleController: Requesting all vehicle records from Service...");
        try {
            List<VehicleDTO> list = service.fetchAll();
            System.out.println("VehicleController: Records retrieved: " + (list != null ? list.size() : 0));
            model.addAttribute("vehicleList", list); // Passes the list to ViewVehicles.jsp
        } catch (Exception e) {
            System.err.println("Error in viewAllVehicles: " + e.getMessage());
            model.addAttribute("error", "Could not retrieve service history.");
        }
        return "ViewVehicles";
    }

    @GetMapping("/edit") // Pulls existing record data into the update form
    public String showEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("VehicleController: Fetching data for editing ID: " + id);
        try {
            VehicleDTO dto = service.fetchById(id);
            if (dto != null) {
                model.addAttribute("vehicleDto", dto); // Populates form fields for editing
                return "UpdateVehicle";
            } else {
                System.out.println("VehicleController: Edit failed. ID " + id + " not found.");
                return "redirect:/vehicle/viewAll";
            }
        } catch (Exception e) {
            System.err.println("Exception in showEditPage: " + e.getMessage());
            return "redirect:/vehicle/viewAll";
        }
    }

    @GetMapping("/delete") // Handles the removal of vehicle records
    public String deleteVehicle(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("VehicleController: Requesting deletion of record ID: " + id);
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                System.out.println("VehicleController: Record " + id + " deleted successfully.");
                redirectAttributes.addFlashAttribute("deleteMsg", "Vehicle record removed.");
            } else {
                System.out.println("VehicleController: Could not find record " + id + " for deletion.");
                redirectAttributes.addFlashAttribute("error", "Unable to remove record.");
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteVehicle: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Deletion error: " + e.getMessage());
        }
        return "redirect:/vehicle/viewAll";
    }
}