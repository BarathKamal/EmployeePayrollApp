package com.bridgelabz.employeepayrollapp.dto;

// sec2 - uc1: introduce dto and model

// Employee response dto
public class EmployeeResponseDTO {
    private String name;
    private double salary;

    public EmployeeResponseDTO() {}

    public EmployeeResponseDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}