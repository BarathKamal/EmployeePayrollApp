package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeRequestDTO;
import com.bridgelabz.employeepayrollapp.dto.EmployeeResponseDTO;
import com.bridgelabz.employeepayrollapp.entity.Employee;
import com.bridgelabz.employeepayrollapp.mapper.EmployeeMapper;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// sec2 - uc1: introduce dto and model
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    private final EmployeeMapper mapper;

    // In-memory storage
    private final List<Employee> employees = new ArrayList<>();

    // Manual ID generation
    private int nextId = 1;

    public EmployeeController(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    // GET: Retrieve all employees
    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EmployeeResponseDTO> employeeDTOs = employees.stream()
                .map(employee -> mapper.toEmployeeResponseDTO(employee))
                .toList();

        return ResponseEntity.ok(employeeDTOs);
    }

    // GET: Retrieve employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable int id) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();

        return employee
                .map(emp -> ResponseEntity.ok(
                        mapper.toEmployeeResponseDTO(emp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create employee
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = mapper.toEmployee(employeeRequestDTO);

        employee.setId(nextId++);

        employees.add(employee);

        EmployeeResponseDTO responseDTO =
                mapper.toEmployeeResponseDTO(employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    // PUT: Update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable int id,
            @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {

        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();

        if (existingEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = existingEmployee.get();

        // Update employee details
        Employee updatedEmployee = mapper.toEmployee(employeeRequestDTO);

        updatedEmployee.setId(id);

        employees.set(employees.indexOf(employee), updatedEmployee);

        EmployeeResponseDTO responseDTO =
                mapper.toEmployeeResponseDTO(updatedEmployee);

        return ResponseEntity.ok(responseDTO);
    }

    // DELETE: Delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id) {

        boolean removed = employees.removeIf(
                employee -> employee.getId() == id);

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
