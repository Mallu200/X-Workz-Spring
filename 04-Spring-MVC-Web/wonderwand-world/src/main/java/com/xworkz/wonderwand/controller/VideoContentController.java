package com.xworkz.wonderwand.controller;

import com.xworkz.wonderwand.dto.VideoContentDTO;
import com.xworkz.wonderwand.service.VideoContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype identifying this class as the web layer handler for WonderWand
@RequestMapping("/video") // Base URL mapping for all video-related operations
public class VideoContentController {

    @Autowired // Injecting the service layer for production business logic
    private VideoContentService service;

    @GetMapping("/") // Landing page for the Content module
    public String showIndex() {
        System.out.println("VideoContentController: Navigating to WonderWand dashboard."); // Navigation trace
        return "index";
    }

    @GetMapping("/create") // Displays the new video entry form
    public String showCreatePage(Model model) {
        System.out.println("VideoContentController: Opening Content Creation page..."); // Form initialization trace
        try {
            if (!model.containsAttribute("videoDto")) {
                VideoContentDTO dto = new VideoContentDTO();
                dto.setId(0); // ID 0 indicates new content that hasn't been saved yet
                model.addAttribute("videoDto", dto); // Binds empty DTO to the form:form tags
                System.out.println("VideoContentController: Initialized new VideoContentDTO.");
            }
        } catch (Exception e) {
            System.err.println("Exception in showCreatePage: " + e.getMessage()); // Error trace
            model.addAttribute("error", "System error while loading creation form.");
        }
        return "AddVideoContent";
    }

    @PostMapping("/save") // Handles persistence for both new ideas and status updates
    public String saveContent(@Valid @ModelAttribute("videoDto") VideoContentDTO dto,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        System.out.println("VideoContentController: save/update request for Title: " + dto.getTitle());

        try {
            if (result.hasErrors()) { // Checks JSR-303 constraints like SEO length or Mascot status
                System.out.println("VideoContentController: Validation failed! Errors: " + result.getErrorCount());
                return (dto.getId() > 0) ? "UpdateVideoContent" : "AddVideoContent"; // Returns to form if invalid
            }

            boolean success;
            String message;

            if (dto.getId() > 0) { // Condition to check if updating an existing record
                System.out.println("VideoContentController: Updating content status for ID: " + dto.getId());
                success = service.update(dto);
                message = "Video '" + dto.getTitle() + "' status updated successfully!";
            } else { // Logic for new video entry
                System.out.println("VideoContentController: Saving new video content idea.");
                success = service.validateAndSave(dto);
                message = "New video idea '" + dto.getTitle() + "' added to production!";
            }

            if (success) {
                System.out.println("VideoContentController: Operation successful. Redirecting to library.");
                redirectAttributes.addFlashAttribute("msg", message); // Persists success message across redirect
                return "redirect:/video/viewLibrary";
            } else {
                System.out.println("VideoContentController: Service layer failed to finalize record.");
                model.addAttribute("dbError", "Could not process the video content.");
                return (dto.getId() > 0) ? "UpdateVideoContent" : "AddVideoContent";
            }

        } catch (Exception e) {
            System.err.println("Database Error in saveContent: " + e.getMessage()); // Critical error trace
            model.addAttribute("dbError", "Critical System Error: " + e.getMessage());
            return (dto.getId() > 0) ? "UpdateVideoContent" : "AddVideoContent";
        }
    }

    @GetMapping("/viewLibrary") // Fetches and displays the full production library
    public String viewLibrary(Model model) {
        System.out.println("VideoContentController: Fetching library from service layer..."); // Data fetch trace
        try {
            List<VideoContentDTO> list = service.fetchAll();
            System.out.println("VideoContentController: Records found: " + (list != null ? list.size() : 0));
            model.addAttribute("videoList", list); // Passes video list to ViewLibrary.jsp
        } catch (Exception e) {
            System.err.println("Error in viewLibrary: " + e.getMessage());
            model.addAttribute("error", "Could not retrieve the content library.");
        }
        return "ViewLibrary";
    }

    @GetMapping("/edit") // Loads a specific video's metadata for editing
    public String showEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("VideoContentController: Fetching record ID: " + id + " for update."); // Edit trace
        try {
            VideoContentDTO dto = service.fetchById(id);
            if (dto != null) {
                model.addAttribute("videoDto", dto); // Populates the update form
                return "UpdateVideoContent";
            } else {
                System.out.println("VideoContentController: Record not found for ID: " + id);
                return "redirect:/video/viewLibrary";
            }
        } catch (Exception e) {
            System.err.println("Exception in showEditPage: " + e.getMessage());
            return "redirect:/video/viewLibrary";
        }
    }

    @GetMapping("/delete") // Removes a video record from the management system
    public String deleteContent(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("VideoContentController: Requesting removal of content ID: " + id); // Delete trace
        try {
            boolean deleted = service.delete(id);
            if (deleted) {
                System.out.println("VideoContentController: Successfully removed record ID: " + id);
                redirectAttributes.addFlashAttribute("deleteMsg", "Video record removed from production.");
            } else {
                System.out.println("VideoContentController: Removal failed. Target ID " + id + " not found.");
                redirectAttributes.addFlashAttribute("error", "Unable to remove the content record.");
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteContent: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Deletion error: " + e.getMessage());
        }
        return "redirect:/video/viewLibrary";
    }
}