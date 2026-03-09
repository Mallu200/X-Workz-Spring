package com.xworkz.securevaultguard.controller;

import com.xworkz.securevaultguard.dto.EvidenceDTO;
import com.xworkz.securevaultguard.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller // Stereotype for the web layer of the SecureVaultGuard system
@RequestMapping("/evidence") // Base path for all evidence-related endpoints
public class EvidenceController {

    @Autowired // Injecting the business logic service
    private EvidenceService service;

    @GetMapping("/viewAll") // Accessible by MANAGER, EDITOR, and VIEWER roles
    public String viewAllEvidence(Model model) {
        System.out.println("EvidenceController: Fetching vault logs for authorized user..."); // Audit trace
        List<EvidenceDTO> evidenceList = service.getAllEvidence();
        model.addAttribute("list", evidenceList);
        return "ViewEvidence"; // Maps to /WEB-INF/views/ViewEvidence.jsp
    }

    @GetMapping("/register") // Restrict access to MANAGER and EDITOR roles in SecurityConfig
    public String showRegistrationPage(Model model) {
        System.out.println("EvidenceController: Loading new evidence registration form.");
        model.addAttribute("dto", new EvidenceDTO());
        return "RegisterEvidence";
    }

    @PostMapping("/save") // Handles persistence for new evidence batches
    public String saveEvidence(@Valid @ModelAttribute("dto") EvidenceDTO dto,
                               BindingResult result, Model model) {

        System.out.println("EvidenceController: Processing save request for: " + dto.getItemRef());

        if (result.hasErrors()) { // Validates against JSR-303 constraints (Case No, Item Name)
            System.out.println("EvidenceController: Validation failed! Errors count: " + result.getErrorCount());
            return "RegisterEvidence"; // Stay on the form and display validation messages
        }

        service.validateAndSave(dto);
        System.out.println("EvidenceController: Evidence saved successfully. Redirecting to vault ledger.");
        return "redirect:/evidence/viewAll"; // PRG Pattern (Post-Redirect-Get) to avoid duplicates
    }

    @PostMapping("/delete") // High-privilege action (MANAGER only)
    public String deleteEvidence(@RequestParam("id") int id) {
        System.out.println("EvidenceController: Initiating removal of evidence record ID: " + id);
        service.deleteEvidence(id);
        return "redirect:/evidence/viewAll";
    }
}