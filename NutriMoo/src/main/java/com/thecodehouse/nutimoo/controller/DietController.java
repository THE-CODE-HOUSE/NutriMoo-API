//package com.thecodehouse.nutimoo.controller;
//
//import com.thecodehouse.nutimoo.model.diet.DietResponse;
//import com.thecodehouse.nutimoo.model.diet.EMDTO;
//import com.thecodehouse.nutimoo.service.DietService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/diet")
//public class DietController {
//    @Autowired
//    private DietService dietService;
//
//    @PostMapping("/conta")
//    public ResponseEntity<Double> testeConta(@RequestBody EMDTO weight){
//        try{
//            double em = dietService.emTeste(weight.x());
//            return ResponseEntity.ok(em);
//        }catch (EmptyResultDataAccessException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//}
