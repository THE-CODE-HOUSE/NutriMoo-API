package com.thecodehouse.nutimoo.controller;

import com.thecodehouse.nutimoo.infra.security.TokenService;
import com.thecodehouse.nutimoo.model.employees.AuthenticationDTO;
import com.thecodehouse.nutimoo.model.employees.LoginResponseDTO;
import com.thecodehouse.nutimoo.model.employees.RegisterDto;
import com.thecodehouse.nutimoo.persistence.entity.Employees;
import com.thecodehouse.nutimoo.persistence.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeesRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Employees) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto data){
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Employees newEmployee = new Employees(data.name(),data.email(), encryptedPassword,data.department(), data.role());

        this.repository.save(newEmployee);

        return  ResponseEntity.ok().build();
    }
}
