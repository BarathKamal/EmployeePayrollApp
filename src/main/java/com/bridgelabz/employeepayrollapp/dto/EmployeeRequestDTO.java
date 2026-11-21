package com.bridgelabz.employeepayrollapp.dto;

// sec2 - uc1: introduce dto and model
import lombok.Getter;
import lombok.Setter;

// Employee request dto
@Getter
@Setter
public class EmployeeRequestDTO {
    private String name;

    private double salary;

    public EmployeeRequestDTO() {}

    public EmployeeRequestDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}