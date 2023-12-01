package com.thecodehouse.nutimoo.service;


import com.thecodehouse.nutimoo.model.diet.DietRequest;
import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.model.diet.Foods;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.entity.Diet;
import com.thecodehouse.nutimoo.persistence.entity.Ingredients;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import com.thecodehouse.nutimoo.persistence.repository.DietRepository;
import com.thecodehouse.nutimoo.persistence.repository.IngredientsRepository;
import com.thecodehouse.nutimoo.client.Client;

//import ch.qos.logback.core.net.server.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public List<DietResponse> create(DietRequest dietRequest){

        Diet diet = new Diet();
        diet = createDiet(dietRequest);
       
        dietRepository.save(diet);

        List<DietResponse> responses;
        responses = getAll();

        return responses;
        
    }

    
    public List<DietResponse> getAll(){

        List<DietResponse> responses = new ArrayList<>() ;
        List<Diet> diets = dietRepository.findAll();

        if(!diets.isEmpty()){
            diets.forEach(diet -> responses.add(createResponse(diet)));
        }

        return responses;

     }

    private DietResponse createResponse(Diet diet) {
        DietResponse response = new DietResponse();

        response.setProtein(diet.getProtein());
        response.setFat(diet.getFat());
        response.setCarbohydrates(diet.getCarbohydrates());
        response.setGoal(diet.getGoal());
        response.setStage(diet.getStage());
        response.setCms(diet.getCms());
        response.setEm(diet.getEm());
        response.setFoods(diet.getFoods());


        return response;
    }
    
    private Diet createDiet(DietRequest dietRequest){
        List<Cattle> cattle = cattleRepository.findAllByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal());
        List<Ingredients> ingredients = ingredientsRepository.findAll();

        Foods[] foods = new Foods[2];
        Double[] cmsIngredients = new Double[2];

        double em = 0;
        double cms = 0;
        double weight = 0;
        double carbohydrates = 0;
        double fat = 0;
        double protein = 0;


        for(int i = 0; i<cattle.size(); i++){
            weight += cattle.get(i).getWeight();
        }
        weight = weight/cattle.size();

        try{
           em  = client.emCalculation(weight);

        }catch(Exception error){}
        switch (dietRequest.getGoal()){
            case "PERDER PESO":
                em *= 0.8  ;
                break;
            case "GANHAR PESO":
                if (dietRequest.getStage().equals("VACA EM LACTACAO")){
                    em*= 1.40;
                }else{
                    em *= 1.2;
                }
                break;

            default:
                break;
        }
        double emRacao = em*0.75;
        double emSilagem = em*0.25;
        int k = 0;

        for(int j = 0; j<ingredients.size();j++){
            if(k==0){
                cmsIngredients[k] = emSilagem/ingredients.get(j).getEnergy();
                foods[k] = new Foods(ingredients.get(j).getNome(),cmsIngredients[k]*(ingredients.get(j).getProtein()/100),cmsIngredients[k]*(ingredients.get(j).getFat()/100),cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100), cmsIngredients[k]);
                k++;
            }
            if(dietRequest.getGoal().equals("PERDER PESO") || dietRequest.getGoal().equals("MANTER PESO")){
                if(ingredients.get(j).getNome().equals("Ração Comercial")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),cmsIngredients[k]*(ingredients.get(j).getProtein()/100),cmsIngredients[k]*(ingredients.get(j).getFat()/100),cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100),cmsIngredients[k]);
                    k++;
                }
            }else{
                if(ingredients.get(j).getNome().equals("Ração Comercial para Bovinos Lactacao")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),cmsIngredients[k]*(ingredients.get(j).getProtein()/100),cmsIngredients[k]*(ingredients.get(j).getFat()/100),cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100),cmsIngredients[k]);
                    k++;
                }
            }
        }
        for(int l = 0; l<cmsIngredients.length; l++){
            cms += cmsIngredients[l];
            carbohydrates += foods[l].getCarbohydrates();
            fat += foods[l].getFat();
            protein += foods[l].getProtein();
        }
        Diet diet = new Diet();
        diet.setStage(dietRequest.getStage());
        diet.setGoal(dietRequest.getGoal());
        diet.setEm(em);
        diet.setCms(cms);
        diet.setCarbohydrates(carbohydrates);
        diet.setFat(fat);
        diet.setProtein(protein);
        diet.setFoods(foods);

        return diet;
    }
    
    @Override
    public List<DietResponse> updateDiet(DietRequest dietRequest){

        Optional<Diet> optionalDiet = Optional.ofNullable(dietRepository.findByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal()));
        
        Diet diet = new Diet();
        diet = createDiet(dietRequest);
        if(optionalDiet.isPresent()){
            Diet dietUpdate = optionalDiet.get();
            dietUpdate.setCarbohydrates(diet.getCarbohydrates());
            dietUpdate.setFat(diet.getFat());
            dietUpdate.setProtein(diet.getProtein());
            dietUpdate.setGoal(diet.getGoal());
            dietUpdate.setStage(diet.getStage());
            dietUpdate.setCms(diet.getCms());
            dietUpdate.setEm(diet.getEm());
            dietUpdate.setFoods(diet.getFoods());
            
        }




        List<DietResponse> responses = new ArrayList<>() ;
        responses = getAll();

        return responses;

    }

}
