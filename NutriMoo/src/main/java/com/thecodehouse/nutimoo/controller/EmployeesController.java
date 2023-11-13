package com.thecodehouse.nutimoo.controller;

import com.thecodehouse.nutimoo.model.employees.EmployeesRequest;
import com.thecodehouse.nutimoo.model.employees.EmployeesResponse;
import com.thecodehouse.nutimoo.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    @PostMapping("/insert")
    public ResponseEntity<EmployeesResponse> create(@RequestBody EmployeesRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
