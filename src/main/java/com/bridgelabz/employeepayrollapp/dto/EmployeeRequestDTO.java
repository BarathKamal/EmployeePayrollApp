package com.bridgelabz.employeepayrollapp.dto;

// sec2 - uc1: introduce dto and model

// Employee request dto
public class EmployeeRequestDTO {
    private String name;

    private double salary;

    public EmployeeRequestDTO() {}

    public EmployeeRequestDTO(String name, double salary) {
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