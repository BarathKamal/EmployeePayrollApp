
package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// sec2 - uc2: introduce services layer
// sec2 - uc3: ability for the services layer to store data
@Service
public class EmployeeService {

    // In-memory storage
    private final List<Employee> employees = new ArrayList<>();

    // Manual ID generation
    private int nextId = 1;

    // Add employee
    public Employee addEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    // Get employee by ID
    public Optional<Employee> getEmployee(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee employee) {

        Employee existingEmployee = getEmployee(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());

        return existingEmployee;
    }

    // Delete employee
    public void deleteEmployee(int id) {

        Employee existingEmployee = getEmployee(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + id));

        employees.remove(existingEmployee);
    }
}
