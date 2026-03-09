package com.xworkz.eduportal.controller;

import com.xworkz.eduportal.dto.StudentDTO;
import com.xworkz.eduportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype annotation identifying this class as the web layer handler
@RequestMapping("/student") // Base URL mapping for all student-related operations
public class StudentController {

    @Autowired // Injecting the StudentService dependency for business operations
    private StudentService service;

    @GetMapping("/") // Landing page for the Student module
    public String showIndex() {
        System.out.println("StudentController: Navigating to EduPortal index page."); // Navigation trace
        return "index";
    }

    @GetMapping("/register") // Displays the student admission/registration form
    public String showRegisterPage(Model model) {
        System.out.println("StudentController: Loading Student Admission form..."); // Form initialization trace
        try {
            if (!model.containsAttribute("studentDto")) {
                StudentDTO dto = new StudentDTO();
                dto.setId(0); // 0 indicates a new admission for the form logic
                model.addAttribute("studentDto", dto); // Binds empty DTO to the form:form tag
                System.out.println("StudentController: Initialized new StudentDTO for admission.");
            }
        } catch (Exception e) {
            System.err.println("Exception in showRegisterPage: " + e.getMessage()); // Error trace
            model.addAttribute("error", "Failed to load admission page.");
        }
        return "RegisterStudent";
    }

    @PostMapping("/save") // Handles persistence for both new admissions and academic updates
    public String saveStudent(@Valid @ModelAttribute("studentDto") StudentDTO dto,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        System.out.println("StudentController: save/update request for USN: " + dto.getUsn());

        try {
            if (result.hasErrors()) { // Checks JSR-303 constraints (USN format, CGPA range, etc.)
                System.out.println("StudentController: Validation failed! Error count: " + result.getErrorCount());
                return (dto.getId() > 0) ? "UpdateStudent" : "RegisterStudent"; // Returns to form if invalid
            }

            boolean success;
            String message;

            if (dto.getId() > 0) { // Condition to check if it's an existing record update
                System.out.println("StudentController: Updating academic record for ID: " + dto.getId());
                success = service.update(dto);
                message = "Student profile for " + dto.getUsn() + " updated successfully!";
            } else { // Logic for new student registration
                System.out.println("StudentController: Saving new student admission.");
                success = service.validateAndSave(dto);
                message = "Student " + dto.getName() + " admitted successfully with USN: " + dto.getUsn();
            }

            if (success) {
                System.out.println("StudentController: Operation successful. Redirecting to student list.");
                redirectAttributes.addFlashAttribute("msg", message); // Persists message across redirect
                return "redirect:/student/viewAll";
            } else {
                System.out.println("StudentController: Service layer failed to save/update record.");
                model.addAttribute("dbError", "System error: Could not save student record.");
                return (dto.getId() > 0) ? "UpdateStudent" : "RegisterStudent";
            }

        } catch (Exception e) {
            System.err.println("Database Error in saveStudent: " + e.getMessage()); // Critical error trace
            model.addAttribute("dbError", "Critical Database Error: " + e.getMessage());
            return (dto.getId() > 0) ? "UpdateStudent" : "RegisterStudent";
        }
    }

    @GetMapping("/viewAll") // Fetches and displays the complete student directory
    public String viewAllStudents(Model model) {
        System.out.println("StudentController: Fetching directory from service layer..."); // Data fetch trace
        try {
            List<StudentDTO> list = service.fetchAll();
            System.out.println("StudentController: Total students retrieved: " + (list != null ? list.size() : 0));
            model.addAttribute("studentList", list); // Passes data list to ViewStudents.jsp
        } catch (Exception e) {
            System.err.println("Error in viewAllStudents: " + e.getMessage());
            model.addAttribute("error", "Unable to fetch student list.");
        }
        return "ViewStudents";
    }

    @GetMapping("/edit") // Loads a specific student's profile for editing
    public String showEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("StudentController: Fetching record ID: " + id + " for update."); // Edit trace
        try {
            StudentDTO dto = service.fetchById(id);
            if (dto != null) {
                model.addAttribute("studentDto", dto); // Populates the update form
                return "UpdateStudent";
            } else {
                System.out.println("StudentController: Record not found for ID: " + id);
                return "redirect:/student/viewAll";
            }
        } catch (Exception e) {
            System.err.println("Exception in showEditPage: " + e.getMessage());
            return "redirect:/student/viewAll";
        }
    }

    @GetMapping("/delete") // Removes a student record from the portal
    public String deleteStudent(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("StudentController: Requesting removal of record ID: " + id); // Delete trace
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                System.out.println("StudentController: Successfully removed record ID: " + id);
                redirectAttributes.addFlashAttribute("deleteMsg", "Student record removed successfully.");
            } else {
                System.out.println("StudentController: Deletion target ID " + id + " not found.");
                redirectAttributes.addFlashAttribute("error", "Action failed: Record not found.");
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteStudent: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "System Error: " + e.getMessage());
        }
        return "redirect:/student/viewAll";
    }
}