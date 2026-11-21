## Employee Payroll App

#### Description

The Employee Payroll App is a Spring Boot REST API application that performs CRUD operations to manage employee records, including names and salaries.
It uses Spring Data JPA and MySQL for database connectivity and persistent storage, following a layered architecture with Controller, Service, and Repository.

### Section 1: Employee Payroll App Setup

#### Use Case 1: Create Employee Payroll Spring Project
Create a Spring Boot project to handle REST requests and test APIs

#### Use Case 2: Create REST Controller
Create a REST Controller to demonstrate HTTP methods and establish REST API connectivity with MySQL.

### Section 2: Handling Employee Payroll DTO and Model in Employee Payroll Service Layer

#### Use Case 1: Introduce DTO and Model
Introduce DTO and Model classes with employee name and salary fields to transfer and manage employee data.

#### Use Case 2: Introduce Services Layer
Introduce a Service layer to manage employee data and implement dependency injection between the Controller and Service.

#### Use Case 3: Store Employee Payroll Data
Enable the Service layer to store, retrieve, update, and delete employee payroll data using an in-memory List.

### Section 3: Application Settings

#### Use Case 1: Use Lombok Library in DTO
Integrate Lombok to automatically generate getters and setters for DTO classes.

#### Use Case 2: Use Lombok for Logging
Integrate Lombok's @Slf4j annotation to enable logging within application classes.