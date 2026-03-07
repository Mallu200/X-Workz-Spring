package com.xworkz.clinic.runner;

import com.xworkz.clinic.config.ClinicConfig;
import com.xworkz.clinic.entity.Patient;
import com.xworkz.clinic.service.ClinicService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ORMRunner {
    public static void main(String[] args) {

        // 1. Start the Spring Container using your Java Configuration
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClinicConfig.class);

        // 2. Retrieve the Transactional Service Bean
        ClinicService service = context.getBean(ClinicService.class);

        // 3. Create a new Patient Object (Lombok setters in action)
        Patient newPatient = new Patient();
        newPatient.setName("Arjun");
        newPatient.setAilment("Fever");

        // 4. Trigger the @Transactional logic
        // Steps: Count (JDBC) -> Save (Hibernate) -> Commit/Rollback
        service.registerPatient(newPatient);

        // 5. Shutdown the context to release DB connections
        context.close();
    }
}