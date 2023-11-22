package com.thecodehouse.nutimoo.controller;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;
import com.thecodehouse.nutimoo.service.CattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.List;

@RestController
@RequestMapping("/api/cattle")
public class CattleController {

    @Autowired
    private CattleService service;

    @PostMapping("/insert")
    public ResponseEntity<CattleResponse> create(@RequestBody CattleRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CattleResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Void> update(@PathVariable String id, @RequestBody CattleRequest request){
        try {
            service.updateCattle(id, request.getTag(), request.getStage(), request.getBreed(), request.getBirthDate(), request.getWeight());
            return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found sem corpo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error sem corpo
        }
    }

}
