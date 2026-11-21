
package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// sec2 - uc2: introduce services layer
// sec2 - uc3: ability for the services layer to store data
@Slf4j // sec3 - uc2: use lombok library for logging
@Service
public class EmployeeService {

    // In-memory storage
    private final List<Employee> employees = new ArrayList<>();

    // Manual ID generation
    private int nextId = 1;

    // Add employee
    public Employee addEmployee(Employee employee) {
        log.info("Creating employee");

        employee.setId(nextId++);
        employees.add(employee);

        log.info("Employee created successfully with ID: " + employee.getId());
        return employee;
    }

    // Get employee by ID
    public Optional<Employee> getEmployee(int id) {
        log.debug("Fetching employee with ID: " + id);
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return new ArrayList<>(employees);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee employee) {
        log.info("Updating employee with ID: {}", id);

        Employee existingEmployee = getEmployee(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());

        return existingEmployee;
    }

    // Delete employee
    public void deleteEmployee(int id) {
        log.info("Deleting employee with ID: {}", id);

        Employee existingEmployee = getEmployee(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + id));

        employees.remove(existingEmployee);
    }
}
