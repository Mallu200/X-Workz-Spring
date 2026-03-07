package com.xworkz.hr.runner;

import com.xworkz.hr.config.HRConfig;
import com.xworkz.hr.entity.Employee;
import com.xworkz.hr.service.HRService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HRRunner {
    public static void main(String[] args) {

        // 1. Initialize the Spring context specifically for the HR module
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HRConfig.class);

        // 2. Access the Service layer (never the repository directly)
        HRService service = context.getBean(HRService.class);

        // 3. Data Entry Phase: Saving Employee objects to MySQL
        // Notice we use the custom constructor: Employee(name, dept, salary)
        service.addEmployee(new Employee("Mallikarjun", "Development", 85000));
        service.addEmployee(new Employee("Arjun", "Development", 70000));
        service.addEmployee(new Employee("Krishna", "HR", 60000));

        // 4. Reporting Phase: Testing the HQL "WHERE" clause
        // This will only print names belonging to the 'Development' department
        service.displayDeptReport("Development");

        // 5. Shutdown the container and release database connections
        context.close();
    }
}