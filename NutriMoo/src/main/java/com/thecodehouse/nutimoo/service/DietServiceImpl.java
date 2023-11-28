package com.thecodehouse.nutimoo.service;


import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.model.diet.EMDTO;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.entity.Diet;
import com.thecodehouse.nutimoo.persistence.entity.Ingredients;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import com.thecodehouse.nutimoo.persistence.repository.DietRepository;
import com.thecodehouse.nutimoo.persistence.repository.IngredientsRepository;
import com.thecodehouse.nutimoo.service.Client.Client;

//import ch.qos.logback.core.net.server.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//    @Override
//    public DietResponse create(DietRequest dietRequest){
//
//        List<Cattle> cattle = cattleRepository.findAllByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal());
//        List<Ingredients> ingredients = ingredientsRepository.findAll();
//        Double[] cmsIngredients = new Double[2];
//        double em = 0;
//        double cms = 0;
//        double weight = 0;
//
//        for(int i = 0; i<cattle.size(); i++){
//            weight += cattle.get(i).getWeight();
//        }
//        weight = weight/cattle.size();
//        try{
//            em  = client.emCalculation(weight);
//
//        }catch(Exception error){}
//        switch (dietRequest.getGoal()){
//            case "PERDER PESO":
//                em *= 0.8  ;
//                break;
//            case "GANHAR PESO":
//                em *= 1.2 ;
//                break;
//
//            default:
//                break;
//        }
//
//
//        double emIngredients = em/2;
//
//        for(int j =0; j<ingredients.size();j++){
//            if(dietRequest.getGoal() == "PERDER PESO" || dietRequest.getGoal() == "MANTER PESO"){
//                cmsIngredients[j] = emIngredients/;
//            }
//        }
//
//
//
//
//        cms = em/ingredients.get(0).getEnergy();
//
//        Diet diet = new Diet();
//        diet.setStage(dietRequest.getStage());
//        diet.setGoal(dietRequest.getGoal());
//        diet.setEm(em);
//        diet.setCms(cms);
//        return;
//    }

//    @Override
//    public double emTeste(double x){
//        double em = client.emCalculation(x);
//        return em;
//    }

}
