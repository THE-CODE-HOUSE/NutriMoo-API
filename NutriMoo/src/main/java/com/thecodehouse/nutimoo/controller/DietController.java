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
    public ResponseEntity<List<DietResponse>> create(@RequestBody DietRequest request){
        try{
            return ResponseEntity.ok(dietService.create(request));
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }

    @PostMapping("/update")
    public ResponseEntity<List<DietResponse>> update(@RequestBody DietRequest request){
        try{
            return ResponseEntity.ok(dietService.updateDiet(request));
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }





}
