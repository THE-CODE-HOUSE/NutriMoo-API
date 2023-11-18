package com.thecodehouse.nutimoo.controller;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;
import com.thecodehouse.nutimoo.service.CattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
