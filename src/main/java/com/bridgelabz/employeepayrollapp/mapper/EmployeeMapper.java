package com.bridgelabz.employeepayrollapp.mapper;

import com.bridgelabz.employeepayrollapp.dto.EmployeeRequestDTO;
import com.bridgelabz.employeepayrollapp.dto.EmployeeResponseDTO;
import com.bridgelabz.employeepayrollapp.entity.Employee;
import org.springframework.stereotype.Component;

// sec2 - uc1: introduce dto and model

// Employee mapper for conversion logic between dto and employee
@Component
public class EmployeeMapper {

    public Employee toEmployee(EmployeeRequestDTO employeeRequestDTO) {
        return new Employee(employeeRequestDTO.getName(), employeeRequestDTO.getSalary());
    }

    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getName(), employee.getSalary());
    }
}
