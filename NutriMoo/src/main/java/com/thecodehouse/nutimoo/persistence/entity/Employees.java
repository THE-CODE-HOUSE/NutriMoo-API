package com.thecodehouse.nutimoo.persistence.entity;

import com.thecodehouse.nutimoo.model.employees.EmployeesRoles;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "Employees")
public class Employees implements UserDetails {

    @Id
    private String id;
    private String name;
    private String email;
    private String department;
    private EmployeesRoles role;
    private String password;

    public Employees(String name,String email, String password,String department, EmployeesRoles role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.role = role;
    }
    public  Employees(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public EmployeesRoles getRole() {
        return role;
    }

    public void setRole(EmployeesRoles role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == EmployeesRoles.ADMIN || this.role == EmployeesRoles.MANAGER)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_VET"),
                    new SimpleGrantedAuthority("ROLE_WORKER"),
                    new SimpleGrantedAuthority("ROLE_NUTRITIONIST"),
                    new SimpleGrantedAuthority("ROLE_PRODUCER")
            );
        else if (this.role == EmployeesRoles.PRODUCER)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_VET"),
                    new SimpleGrantedAuthority("ROLE_WORKER"),
                    new SimpleGrantedAuthority("ROLE_NUTRITIONIST"),
                    new SimpleGrantedAuthority("ROLE_PRODUCER")
            );
        else if (this.role == EmployeesRoles.VET)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_VET"),
                    new SimpleGrantedAuthority("ROLE_WORKER")
            );
        else if (this.role == EmployeesRoles.NUTRITIONIST)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_WORKER"),
                    new SimpleGrantedAuthority("ROLE_NUTRITIONIST")
            );
        else return List.of(new SimpleGrantedAuthority("ROLE_WORKER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}