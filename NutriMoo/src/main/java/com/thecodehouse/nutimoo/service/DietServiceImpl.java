package com.thecodehouse.nutimoo.service;


import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.entity.Diet;
import com.thecodehouse.nutimoo.persistence.entity.Ingredients;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import com.thecodehouse.nutimoo.persistence.repository.DietRepository;
import com.thecodehouse.nutimoo.persistence.repository.IngredientsRepository;
import com.thecodehouse.nutimoo.service.Client.Client;

import ch.qos.logback.core.net.server.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietServiceImpl implements DietService{
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    private CattleRepository cattleRepository;

    @Autowired
    private Client client;

    @Override
    public DietResponse create(DietRequest dietRequest){

        List<Cattle> cattle = cattleRepository.findAllByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal());
        List<Ingredients> ingredients = ingredientsRepository.findAll();

        double em = 0;
        //double emIngredient = em/cattle.size();
        double cms = 0;
        double weight = 0;

        for(int i = 0; i<cattle.size(); i++){
            weight += cattle.get(i).getWeight();
        }
        weight = weight/cattle.size();
        try{
            em  = client.emCalculation(weight);

        }catch(Exception error){
            
        }




        Diet diet = new Diet();
        diet.setStage(dietRequest.getStage());
        diet.setGoal(dietRequest.getGoal());
    }


}
