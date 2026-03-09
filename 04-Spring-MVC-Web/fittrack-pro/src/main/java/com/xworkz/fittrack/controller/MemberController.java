package com.xworkz.fittrack.controller;

import com.xworkz.fittrack.dto.MemberDTO;
import com.xworkz.fittrack.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype annotation identifying this as a web request handler
@RequestMapping("/member") // Base URL mapping for all methods in this controller
public class MemberController {

    @Autowired // Injecting Service layer dependency
    private MemberService service;

    @GetMapping("/") // Maps to the landing page of the application
    public String showIndex() {
        System.out.println("MemberController: Navigating to index page."); // SOP for page navigation
        return "index";
    }

    @GetMapping("/register") // Displays the registration form
    public String showRegisterPage(Model model) {
        System.out.println("MemberController: Opening Registration page..."); // Trace for form loading
        try {
            if (!model.containsAttribute("dto")) {
                MemberDTO dto = new MemberDTO();
                dto.setId(0); // Sets ID to 0 to signify a new record
                model.addAttribute("dto", dto); // Binding empty DTO to the form
                System.out.println("MemberController: Initialized new MemberDTO for registration.");
            }
        } catch (Exception e) {
            System.err.println("Exception in showRegisterPage: " + e.getMessage()); // Error trace
            model.addAttribute("error", "Error loading registration page.");
        }
        return "RegisterMember";
    }

    @PostMapping("/save") // Handles both creation and updates via POST
    public String saveMember(@Valid @ModelAttribute("dto") MemberDTO dto,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        // Log the incoming data for debugging
        System.out.println("MemberController: Processing save/update for: " + dto.getName() + " (ID: " + dto.getId() + ")");

        try {
            if (result.hasErrors()) { // Triggers if JSR-303 validation fails
                System.out.println("Validation failed for MemberDTO! Error count: " + result.getErrorCount());
                return (dto.getId() > 0) ? "UpdateMember" : "RegisterMember";
            }

            boolean success;
            String message;

            if (dto.getId() > 0) { // If ID exists, perform update
                System.out.println("MemberController: Calling service.update() for ID: " + dto.getId());
                success = service.update(dto);
                message = "Member " + dto.getName() + " updated successfully!";
            } else { // If ID is 0, perform new registration
                System.out.println("MemberController: Calling service.validateAndSave() for new member.");
                success = service.validateAndSave(dto);
                message = "Member " + dto.getName() + " registered successfully!";
            }

            if (success) {
                System.out.println("MemberController: Success! Redirecting to viewAll.");
                redirectAttributes.addFlashAttribute("msg", message); // Flash message persists after redirect
                return "redirect:/member/viewAll";
            } else {
                System.out.println("MemberController: Service layer returned failure.");
                model.addAttribute("dbError", "System could not process the request.");
                return (dto.getId() > 0) ? "UpdateMember" : "RegisterMember";
            }

        } catch (Exception e) {
            System.err.println("Database Error in saveMember: " + e.getMessage());
            model.addAttribute("dbError", "Database Error: " + e.getMessage());
            return (dto.getId() > 0) ? "UpdateMember" : "RegisterMember";
        }
    }

    @GetMapping("/viewAll") // Fetches and displays the member list
    public String viewAllMembers(Model model) {
        System.out.println("MemberController: Fetching all members from service...");
        try {
            List<MemberDTO> list = service.fetchAll();
            System.out.println("MemberController: Total members retrieved: " + (list != null ? list.size() : 0));
            model.addAttribute("memberList", list);
            model.addAttribute("dto", new MemberDTO());
        } catch (Exception e) {
            System.err.println("Error in viewAllMembers: " + e.getMessage());
            model.addAttribute("error", "Could not fetch member list.");
        }
        return "ViewMembers";
    }

    @GetMapping("/edit") // Loads existing member data into the update form
    public String showEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("MemberController: Fetching member with ID: " + id + " for editing.");
        try {
            MemberDTO dto = service.fetchById(id);
            if (dto != null) {
                model.addAttribute("dto", dto); // Populates the form with existing data
                return "UpdateMember";
            } else {
                System.out.println("MemberController: Edit failed, member not found.");
                model.addAttribute("error", "Member not found.");
                return "redirect:/member/viewAll";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error retrieving member data.");
            return "redirect:/member/viewAll";
        }
    }

    @GetMapping("/delete") // Handles member removal
    public String deleteMember(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("MemberController: Request to delete member with ID: " + id);
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                System.out.println("MemberController: Successfully deleted member " + id);
                redirectAttributes.addFlashAttribute("deleteMsg", "Member removed successfully.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to delete the member.");
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteMember: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/member/viewAll";
    }
}