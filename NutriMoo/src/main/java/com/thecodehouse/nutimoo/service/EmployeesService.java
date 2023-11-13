package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.employees.EmployeesRequest;
import com.thecodehouse.nutimoo.model.employees.EmployeesResponse;

import java.util.List;

public interface EmployeesService {

    EmployeesResponse create(EmployeesRequest employeesRequest);

    List<EmployeesResponse> getAll();
}
