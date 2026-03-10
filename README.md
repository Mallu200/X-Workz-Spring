# 🍃 Learning Spring Framework | Enterprise Java Development
### Developer: Mallu200 | Program: X-Workz Full-Stack Training

This repository is a comprehensive technical archive of my transition from Java Standard Edition to the **Spring Enterprise Ecosystem**. It tracks the evolution of my skills from core dependency management to building robust, test-driven B2B systems.

---

## 🏗️ Core Modules & Learning Path
I have organized my training into specialized modules, each representing a critical pillar of the Spring Framework (Non-Boot/Standard Spring):

### 📁 01-Spring-Core-IOC
* **IoC Container:** Mastering `ApplicationContext` for centralized bean management.
* **Dependency Injection:** Practical implementation of Constructor vs. Setter Injection.
* **Scopes & Lifecycle:** Understanding `Singleton` vs. `Prototype` and bean lifecycle hooks.

### 📁 02-Spring-AOP (Aspect Oriented Programming)
* **Modularity:** Removing logging and transaction "clutter" from business logic.
* **Logic:** Implementation of `@Before`, `@After`, and the powerful `@Around` advice.
* **Pointcuts:** Precise execution targeting for performance monitoring and execution time tracking.

### 📁 03-Spring-Data-JPA-ORM
* **Hibernate Integration:** Configuring `LocalContainerEntityManagerFactoryBean` for ORM.
* **MySQL Persistence:** Mapping Entities to relational tables and optimizing queries.
* **Transactions:** Automated commit/rollback management using `JpaTransactionManager`.

### 📁 04-Spring-MVC-Web
* **Front Controller:** Mastering the `DispatcherServlet` workflow.
* **Architecture:** Using `@Controller` for routing and `Model` for data transfer.
* **Web Setup:** Configuration of `ViewResolvers` and internal resource handlers.

### 📁 05-Enterprise-Testing
* **JUnit Integration:** Using `SpringJUnit4ClassRunner` to validate bean wiring.
* **Web Testing:** Implementing `@WebAppConfiguration` to mock the Servlet environment during integration tests.

---

## 🛡️ Capstone Project: Agri-Stock Pro B2B
The **"agri-stock-pro"** folder represents the culmination of all modules into a production-grade Agricultural Inventory Management system.



### 🛰️ Project Deep-Dive:
**Agri-Stock Pro** is designed to manage high-volume agricultural inventory (Seeds, Fertilizers, Pesticides, and Machinery) with enterprise-grade stability.

* **Tiered Architecture:** Implements a strict **Controller → Service → Repository → Entity** flow for separation of concerns.
* **Performance Monitoring:** Leverages **Spring AOP** to track the execution time of every service method, ensuring optimal database performance.
* **Data Integrity:** Employs a tiered **DTO → Entity** architecture. Data is sanitized in the service layer before being persisted into **MySQL 8.0** using **Hibernate ORM**.
* **Integration Testing:** High test coverage using **JUnit 4**, ensuring dependency injection and DB connectivity (EntityManager) are fully functional before deployment.
* **Enterprise UI:** A professional, distraction-free **Flat Design** interface utilizing a **Deep Navy (#1B2631)** and **Slate Gray (#2C3E50)** palette, optimized for high-efficiency business operations.



---

## 🛠️ Technical Competencies
* **Frameworks:** Spring Core, Spring AOP, Spring MVC, Hibernate ORM.
* **Database:** MySQL 8.0, JPA (Java Persistence API), JDBC Template.
* **Testing:** JUnit 4, Spring Test Context, @WebAppConfiguration.
* **Web Tech:** JSP, JSTL, CSS3 (Professional Flat Design), Tomcat 9.0.
* **Build Tools:** Maven (Dependency & Lifecycle Management), Lombok.

---
© 2026 Mallu200 | Developed at X-Workz Training Centre
