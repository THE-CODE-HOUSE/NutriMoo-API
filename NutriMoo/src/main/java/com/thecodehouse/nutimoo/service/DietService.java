package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;


import java.util.List;

public interface DietService {
    List<DietResponse> create(DietRequest dietRequest);

    List<DietResponse> updateDiet(DietRequest dietRequest);



}
