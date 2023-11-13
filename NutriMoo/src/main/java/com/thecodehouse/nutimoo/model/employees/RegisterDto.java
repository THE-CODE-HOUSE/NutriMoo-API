package com.thecodehouse.nutimoo.model.employees;

public record RegisterDto(String email, String password, EmployeesRoles role,String name,String department) {
}
