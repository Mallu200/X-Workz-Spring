# 🍃 Learning Spring Framework | Enterprise Java Development
### Developer: Mallu200 | Program: X-Workz Full-Stack Training

This repository is a comprehensive technical archive of my transition from Java Standard Edition to the **Spring Enterprise Ecosystem**. It tracks the evolution of my skills from core dependency management to building secure, multi-role B2B platforms.

---

## 🏗️ Core Modules & Learning Path
I have organized my training into five specialized modules, each representing a critical pillar of the Spring Framework:

### 📁 01-Spring-Core-IOC
**Focus:** Inversion of Control and the management of object lifecycles.
* **IoC Container:** Mastering `ApplicationContext` for centralized bean management.
* **Dependency Injection:** Practical implementation of Constructor vs. Setter Injection.
* **Scopes & Lifecycle:** Understanding `Singleton` vs. `Prototype` and `@PostConstruct` hooks.
* **SpEL:** Using Spring Expression Language for dynamic configuration.

### 📁 02-Spring-AOP (Aspect Oriented Programming)
**Focus:** Decoupling cross-cutting concerns from business logic.
* **Modularity:** Removing logging and security "clutter" from core services.
* **Logic:** Implementation of `@Before`, `@After`, and the powerful `@Around` advice.
* **Terminology:** Practical application of JoinPoints and Pointcuts.

### 📁 03-Spring-Data-JDBC-ORM
**Focus:** Modernizing data persistence and eliminating boilerplate code.
* **JdbcTemplate:** Internal handling of Connections, Statements, and ResultSets.
* **Hibernate Integration:** Configuring `LocalSessionFactoryBean` for ORM mapping.
* **Transactions:** Automated commit/rollback management using `@Transactional`.

### 📁 04-Spring-MVC-Web
**Focus:** Architecting scalable Web Applications.
* **Front Controller Pattern:** Mastering the `DispatcherServlet` workflow.
* **Annotations:** Using `@Controller` and `@RequestMapping` for clean URL routing.
* **Data Flow:** Transferring data between Java and JSP/UI layers using `Model`.
* **REST APIs:** Transitioning to JSON-based data exchange with `@RestController`.

### 📁 05-Spring-Security-Testing
**Focus:** Application hardening and quality assurance.
* **RBAC:** Implementation of Authentication vs. Authorization.
* **Filter Chains:** How Spring Security intercepts and protects incoming requests.
* **Unit Testing:** Using `@ContextConfiguration` with JUnit to validate Bean logic.

---

## 🛡️ Capstone Project: Krishi-Mandi B2B Ecosystem
The **"krishi-mandi-app"** folder represents the culmination of all the modules above into a production-grade B2B Agricultural Exchange.



### 🛰️ Project Deep-Dive:
**Krishi-Mandi** is designed to digitize the agricultural supply chain, providing a direct link between farmers and the market.

* **Multi-Role Ecosystem:** * **Farmers:** Can securely register and list crop inventory with specific quintal volumes and base prices.
    * **Traders:** Access a real-time marketplace grid to view verified assets and initiate purchase requests.
    * **Inspectors:** Utilize a dedicated panel to audit and approve listings, ensuring quality control.
* **Security Architecture:** Implements a sophisticated **Spring Security Filter Chain** with **BCrypt** password hashing. Access is strictly partitioned using **Role-Based Access Control (RBAC)**.
* **Data Integrity:** Employs a tiered **DTO → Service → Entity** architecture. Data is sanitized in the DTO layer, processed via `@Service` logic, and persisted into **MySQL 8.0** using **Hibernate ORM**.
* **Enterprise UI:** A professional, distraction-free **Flat Design** interface using a **Deep Navy (#1B2631)** and **Slate Gray (#708090)** palette, optimized for high-efficiency business operations.

---

## 🛠️ Technical Competencies
* **Frameworks:** Spring Core, AOP, MVC, Security, Hibernate ORM.
* **Database:** MySQL 8.0 (Workbench), JDBC Template.
* **Web Tech:** JSP, JSTL, EL, CSS3 (Professional Flat Design), Tomcat 9.0.
* **Tools:** Maven, Lombok, Git/GitHub, JUnit.

---
© 2026 Mallu200 | Developed at X-Workz Training Centre
