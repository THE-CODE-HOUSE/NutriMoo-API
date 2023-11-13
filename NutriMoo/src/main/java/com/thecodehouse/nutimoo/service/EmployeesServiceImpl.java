package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.employees.EmployeesRequest;
import com.thecodehouse.nutimoo.model.employees.EmployeesResponse;
import com.thecodehouse.nutimoo.persistence.entity.Employees;
import com.thecodehouse.nutimoo.persistence.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService{

    @Autowired
    private EmployeesRepository repository;

    @Override
    public EmployeesResponse create(EmployeesRequest employeesRequest) {
        Employees employee = new Employees();
        employee.setName(employeesRequest.getName());
        employee.setEmail(employeesRequest.getEmail());
        employee.setDepartment(employeesRequest.getDepartment());
        employee.setRole(employeesRequest.getRole());
        employee.setPassword(employeesRequest.getPassword());

        repository.save(employee);



        return createResponse(employee);
    }

    @Override
    public List<EmployeesResponse> getAll(){
        List<EmployeesResponse> responses = new ArrayList<>();

        List<Employees> employees = repository.findAll();

        if(!employees.isEmpty()){
            employees.forEach(employee -> responses.add(createResponse(employee)));
        }

        return responses;
    }

    private EmployeesResponse createResponse(Employees employees) {
        EmployeesResponse response = new EmployeesResponse();
        response.setName(employees.getName());
        response.setEmail(employees.getEmail());
        response.setDepartment(employees.getDepartment());
        response.setRole(employees.getRole());

        return response;
    }
}
