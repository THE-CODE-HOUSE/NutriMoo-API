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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        Optional<Diet> dietVerification =  Optional.ofNullable(dietRepository.findByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal()));
        if (!dietVerification.isPresent() ){
            Diet diet = new Diet();
            diet = createDiet(dietRequest);

            dietRepository.save(diet);
        }


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
            case "Perder Peso":
                em *= 0.8  ;
                break;
            case "Ganhar Peso":
                if (dietRequest.getStage().equals("Vaca em Lactação")){
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
                foods[k] = new Foods(ingredients.get(j).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(j).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                k++;
            }
            if(dietRequest.getGoal().equals("Perder Peso") || dietRequest.getGoal().equals("Manter Peso")){
                if(ingredients.get(j).getNome().equals("Ração Comercial")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(j).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                    k++;
                }
            }else{
                if(ingredients.get(j).getNome().equals("Ração Comercial para Bovinos Lactacao")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(j).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                    k++;
                }
            }
        }
        for(int l = 0; l<cmsIngredients.length; l++){
            cms += formatDouble(cmsIngredients[l]);
            carbohydrates += formatDouble(foods[l].getCarbohydrates());
            fat += formatDouble(foods[l].getFat());
            protein += formatDouble(foods[l].getProtein());
        }
        Diet diet = new Diet();
        diet.setStage(dietRequest.getStage());
        diet.setGoal(dietRequest.getGoal());
        diet.setEm(formatDouble(em));
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
            dietRepository.save(dietUpdate);
        }




        List<DietResponse> responses = new ArrayList<>() ;
        responses = getAll();

        return responses;

    }

    private double formatDouble(double value) {
        DecimalFormat model = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(model.format(value));

    }



}
