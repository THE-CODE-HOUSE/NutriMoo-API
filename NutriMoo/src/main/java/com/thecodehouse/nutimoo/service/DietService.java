package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;


import java.util.List;

public interface DietService {// interface
    DietResponse create(DietRequest dietRequest);

    DietResponse updateDiet(DietRequest dietRequest);



}
