package com.xworkz.securevaultguard.service;

import com.xworkz.securevaultguard.config.DatabaseConfig;
import com.xworkz.securevaultguard.config.SecurityConfig;
import com.xworkz.securevaultguard.dto.EvidenceDTO;
import com.xworkz.securevaultguard.repository.impl.EvidenceRepositoryImpl;
import com.xworkz.securevaultguard.service.impl.EvidenceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // Integrates JUnit 5 with the Spring TestContext Framework
@ContextConfiguration(classes = {DatabaseConfig.class, SecurityConfig.class, EvidenceServiceImpl.class, EvidenceRepositoryImpl.class})
public class EvidenceSecurityTest {

    @Autowired // Injecting the secured service layer for testing
    private EvidenceService service;

    @Test // Test case for high-privilege access
    @WithMockUser(username = "lead", roles = {"MANAGER"}) // Mocking a Lead (Manager) session
    public void testManagerCanDelete() {
        System.out.println("EvidenceSecurityTest: Testing Manager access for deletion...");

        // Verifies that no AccessDeniedException is thrown for a MANAGER role
        Assertions.assertDoesNotThrow(() -> {
            service.deleteEvidence(1);
        });
        System.out.println("✅ Security Check Passed: Manager successfully accessed delete logic.");
    }

    @Test // Test case for restricted access
    @WithMockUser(username = "auditor", roles = {"VIEWER"}) // Mocking an Auditor (Viewer) session
    public void testAuditorCannotDelete() {
        System.out.println("EvidenceSecurityTest: Testing Auditor restriction for deletion...");

        // Verifies that the @PreAuthorize firewall correctly throws an AccessDeniedException
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            service.deleteEvidence(1);
        });
        System.out.println("✅ Security Check Passed: Auditor was blocked from deleting.");
    }

    @Test // Test case for editor-level registration
    @WithMockUser(username = "analyst", roles = {"EDITOR"}) // Mocking an Analyst (Editor) session
    public void testEditorCanSave() {
        System.out.println("EvidenceSecurityTest: Testing Analyst access for registration...");

        EvidenceDTO dto = new EvidenceDTO(0, "CASE-999", "Hard Drive", "SEIZED", null);

        // Verifies that an EDITOR can successfully invoke the save logic
        Assertions.assertDoesNotThrow(() -> {
            service.validateAndSave(dto);
        });
        System.out.println("✅ Security Check Passed: Editor successfully logged evidence.");
    }
}