package com.thecodehouse.nutimoo.model.employees;

public enum EmployeesRoles {
    PRODUCER("produtor"),
    MANAGER("gerente"),
    WORKER("trabalhador"),
    VET("veterinario"),
    NUTRITIONIST("nutricionista"),
    ADMIN("admin");

    private String role;

     EmployeesRoles(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }
}
