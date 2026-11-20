package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeRequestDTO;
import com.bridgelabz.employeepayrollapp.dto.EmployeeResponseDTO;
import com.bridgelabz.employeepayrollapp.entity.Employee;
import com.bridgelabz.employeepayrollapp.mapper.EmployeeMapper;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    public final EmployeeService employeeService;
    @Autowired
    public EmployeeMapper mapper;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 error
        }

        List<EmployeeResponseDTO> employeeDTOs = employees.stream()
                .map(employee -> mapper.toEmployeeResponseDTO(employee))
                .collect(toList());

        return ResponseEntity.ok(employeeDTOs); // 200 status code
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployee(id)
                .map(employee -> mapper.toEmployeeResponseDTO(employee))
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = mapper.toEmployee(employeeRequestDTO);
        EmployeeResponseDTO employeeResponseDTO = mapper.toEmployeeResponseDTO(employeeService.addEmployee(employee));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeResponseDTO); // 201
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable int id,
            @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = mapper.toEmployee(employeeRequestDTO);
        EmployeeResponseDTO employeeResponseDTO = mapper.toEmployeeResponseDTO(employeeService.updateEmployee(id, employee));

        return ResponseEntity.ok(employeeResponseDTO); // 200
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build(); // 204
    }
}