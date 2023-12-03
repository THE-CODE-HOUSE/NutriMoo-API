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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service //Mostra pro spring que esta classe é uma service
public class DietServiceImpl implements DietService{
    @Autowired
    private DietRepository dietRepository; //definir a variavel para utilizar o repositório
    @Autowired
    private IngredientsRepository ingredientsRepository;//definir a variavel para utilizar o repositório
    @Autowired
    private CattleRepository cattleRepository; //definir a variavel para utilizar o repositório

    @Autowired
    private Client client; // utilizar o client do servidor

    @Override
    public DietResponse create(DietRequest dietRequest){
        List<Cattle> cattle = cattleRepository.findAllByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal()); //pega a lista das vacas que possuem esses estagios e metas

        if(cattle.isEmpty()){
            throw new EmptyResultDataAccessException("Nenhum resultado encontrado",1); // caso n encontre nenhuma vaca com esses requisitos
        }

        Diet dietVerification =  dietRepository.findByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal());//pega a lista das dietas que possuem esses estagios e metas
        if (dietVerification == null){// se for null quer dizer q a dieta não existe
            Diet diet = new Diet();
            diet = createDiet(dietRequest);// função que cria a dieta

            dietRepository.save(diet);// salva no mongo
            DietResponse responses = createResponse(diet); //transforma a Diet em DietResponse


            return responses;
        }else{

            DietResponse responses = createResponse(dietVerification); // caso ja exista so retorna
            return responses;
        }




        
    }

    
    private List<DietResponse> getAll(){ // um possivel meto que retornava todas as dietas do bd, porém naoo usamos mais

        List<DietResponse> responses = new ArrayList<>() ;
        List<Diet> diets = dietRepository.findAll();

        if(!diets.isEmpty()){
            diets.forEach(diet -> responses.add(createResponse(diet)));
        }

        return responses;

     }

    private DietResponse createResponse(Diet diet) {// cria uma DietResponse
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
    
    private Diet createDiet(DietRequest dietRequest){ // cria a dieta
        List<Cattle> cattle = cattleRepository.findAllByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal()); // pega a lista de vacas do mongo
        List<Ingredients> ingredients = ingredientsRepository.findAll();// pega a lista de ingredientes do mongo

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
        weight = weight/cattle.size(); // media dos pesos

        try{
           em  = client.emCalculation(weight);// chama o metodo do cliente que solicita para o servidor o EM passando o peso

        }catch(Exception error){}
        switch (dietRequest.getGoal()){ // mudamos seu valor de acordo com a meta
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

        for(int j = 0; j<ingredients.size();j++){// 1 silagem e 1 ração de acordo com a meta
            if(k==0){ //silagem
                int numeroAleatorio = (int) Math.round(Math.random());
                cmsIngredients[k] = emSilagem/ingredients.get(numeroAleatorio).getEnergy();
                foods[k] = new Foods(ingredients.get(numeroAleatorio).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(numeroAleatorio).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(numeroAleatorio).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(numeroAleatorio).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                k++;
            }
            if(dietRequest.getGoal().equals("Perder Peso") || dietRequest.getGoal().equals("Manter Peso")){
                if(ingredients.get(j).getNome().equals("Ração Normal")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(j).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                    k++;
                }
            }else{
                if(ingredients.get(j).getNome().equals("Ração Engorda")){
                    cmsIngredients[k] = emRacao/ingredients.get(j).getEnergy();
                    foods[k] = new Foods(ingredients.get(j).getNome(),formatDouble(cmsIngredients[k]*(ingredients.get(j).getProtein()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getFat()/100)),formatDouble(cmsIngredients[k]*(ingredients.get(j).getCarbohydrates()/100)), formatDouble(cmsIngredients[k]));
                    k++;
                }
            }
        }
        for(int l = 0; l<cmsIngredients.length; l++){
            cms += cmsIngredients[l]; //CMS da dieta
            carbohydrates += foods[l].getCarbohydrates();// carbo da dieta
            fat += foods[l].getFat();// gordura da dieta
            protein += foods[l].getProtein(); // proteina da dieta
        }
        Diet diet = new Diet();
        diet.setStage(dietRequest.getStage());
        diet.setGoal(dietRequest.getGoal());
        diet.setEm(formatDouble(em));
        diet.setCms(formatDouble(cms));
        diet.setCarbohydrates(formatDouble(carbohydrates));
        diet.setFat(formatDouble(fat));
        diet.setProtein(formatDouble(protein));
        diet.setFoods(foods);

        return diet;
    }
    
    @Override
    public DietResponse updateDiet(DietRequest dietRequest){ // atualizar a dieta

        Optional<Diet> optionalDiet = Optional.ofNullable(dietRepository.findByStageAndGoal(dietRequest.getStage(), dietRequest.getGoal())); // utiliza o optional para comparar
        
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
            dietRepository.save(dietUpdate); // salva no mongo
            DietResponse responses = createResponse(dietUpdate) ; // cria uma DietResponse


            return responses;
        }
        throw new EmptyResultDataAccessException("Nenhum resultado encontrado",1); //caso n encontre o tipo de vaca da erro
    }

    private double formatDouble(double value) {
        DecimalFormat model = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(model.format(value));

    } //função que forma os  numeros para 3 casas dps da virgula



}
