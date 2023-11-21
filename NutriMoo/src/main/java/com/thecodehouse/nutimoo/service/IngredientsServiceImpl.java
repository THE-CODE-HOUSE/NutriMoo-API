package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.ingredients.IngredientsResponse;
import com.thecodehouse.nutimoo.persistence.entity.Ingredients;
import com.thecodehouse.nutimoo.persistence.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class IngredientsServiceImpl implements IngredientsService{

    @Autowired
    private IngredientsRepository repository;


    @Override
    public List<IngredientsResponse> getAll(){
        List<IngredientsResponse> responses = new ArrayList<>();

        List<Ingredients> ingredients = repository.findAll();

        if(!ingredients.isEmpty()){
            ingredients.forEach(ingredient -> responses.add(createResponse(ingredient)));
        }
        return responses;

    }

    private IngredientsResponse createResponse(Ingredients ingredients){
        IngredientsResponse responses = new IngredientsResponse();
        responses.setName(ingredients.getNome());
        responses.setCarbohydrates(ingredients.getCarbohydrates());
        responses.setEnergy(ingredients.getEnergy());
        responses.setFat(ingredients.getFat());
        responses.setProtein(ingredients.getProtein());
        return responses;
    }

}
