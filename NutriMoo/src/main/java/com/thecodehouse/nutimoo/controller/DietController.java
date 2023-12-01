package com.thecodehouse.nutimoo.controller;

import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet")
public class DietController {
    @Autowired
    private DietService dietService;

    @PostMapping("/insert")
    public ResponseEntity<Void> create(@RequestBody DietRequest request){
        try{
            dietService.create(request);
            return ResponseEntity.ok().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }


    @GetMapping("/all")
    public  ResponseEntity<List<DietResponse>>getAll(){
        return ResponseEntity.ok(dietService.getAll());
    }
}
