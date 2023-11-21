package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.model.employees.EmployeesRequest;
import com.thecodehouse.nutimoo.model.employees.EmployeesResponse;

import java.util.List;

public interface DietService {
    DietResponse create(DietRequest dietRequest);
    List<DietResponse> getAll();
}
