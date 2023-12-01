package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@Service
public class CattleServiceImpl implements CattleService{

    @Autowired
    private CattleRepository repository;
    @Override
    public void create(CattleRequest cattleRequest) {
        Cattle cattle = new Cattle();
        cattle.setTag(cattleRequest.getTag());
        cattle.setStage(cattleRequest.getStage());
        cattle.setBreed(cattleRequest.getBreed());
        cattle.setGender(cattleRequest.getGender());
        cattle.setWeight(cattleRequest.getWeight());
        cattle.setBirthDate(cattleRequest.getBirthDate());
        cattle.setPregnant(false);
        cattle.setFertile(!cattle.getStage().equals("BEZERRA/NOVILHA")&&!cattle.getStage().equals("BOI"));
        if(!cattle.getStage().equals("VACA")){
            cattle.setGoal("GANHAR PESO");
        } else {
            cattle.setGoal("MANTER PESO");
        }

        Cattle.Status status = new Cattle.Status();
        status.setHeartRate(formatDouble(Math.random() * (80 - 40) + 40));
        status.setTemperature(formatDouble(Math.random() * (39.2 - 38.3) + 38.3));
        status.setActivityLevel("ATIVO");
        status.setFeedConsumptionRate(formatDouble(Math.random() * (15 - 5) + 5));

        cattle.setStatus(status);
        repository.save(cattle);
    }
    private double formatDouble(double value) {
        DecimalFormat model = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(model.format(value));
    }

    @Override
    public List<CattleResponse> getAll() {
        List<CattleResponse> responses = new ArrayList<>();
        List<Cattle> cattles = repository.findAll();

        if(!cattles.isEmpty()){
            cattles.forEach(cattle -> responses.add(createResponse(cattle)));
        }

        return responses;
    }
    private CattleResponse createResponse(Cattle cattle) {
        CattleResponse response = new CattleResponse();
        response.setTag(cattle.getTag());
        response.setStage(cattle.getStage());
        response.setBreed(cattle.getBreed());
        response.setGender(cattle.getGender());
        response.setWeight(cattle.getWeight());
        response.setGoal(cattle.getGoal());
        response.setStatus(cattle.getStatus());
        response.setPregnant(cattle.isPregnant());
        response.setFertile(cattle.isFertile());
        response.setBirthDate(cattle.getBirthDate());

        return response;
    }

    public void updateCattleByTag(String tag, String stage, Boolean fertile, Boolean pregnant, Double weight, String goal) {
        Optional<Cattle> optionalCattle = repository.findByTag(tag);

        if (optionalCattle.isPresent()) {
            Cattle cattle = optionalCattle.get();
            cattle.setStage(stage);
            cattle.setFertile(fertile);
            cattle.setPregnant(pregnant);
            cattle.setWeight(weight);
            cattle.setGoal(goal);
            repository.save(cattle);
        }
    }
    public void deleteCattle(String tag) {
        repository.deleteByTag(tag);
    }
}
