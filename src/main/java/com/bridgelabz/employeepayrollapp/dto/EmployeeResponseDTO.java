package com.bridgelabz.employeepayrollapp.dto;

// sec2 - uc1: introduce dto and model

import lombok.Getter;
import lombok.Setter;

// Employee response dto
@Getter
@Setter
public class EmployeeResponseDTO {
    private String name;
    private double salary;

    public EmployeeResponseDTO() {}

    public EmployeeResponseDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}