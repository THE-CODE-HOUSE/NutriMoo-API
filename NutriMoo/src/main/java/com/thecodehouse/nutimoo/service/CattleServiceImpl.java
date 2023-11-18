package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CattleServiceImpl implements CattleService{

    @Autowired
    private CattleRepository repository;
    @Override
    public CattleResponse create(CattleRequest cattleRequest) {
        Cattle cattle = new Cattle();
        cattle.setTag(cattleRequest.getTag());
        cattle.setStage(cattleRequest.getStage());
        cattle.setBreed(cattleRequest.getBreed());
        cattle.setGender(cattleRequest.getGender());
        cattle.setWeight(cattleRequest.getWeight());
        cattle.setBirthDate(cattleRequest.getBirthDate());

        Cattle.Status status = new Cattle.Status();
        status.setHeartRate(Math.random() * (80 - 40) + 40);
        status.setTemperature(Math.random() * (39.2 - 38.3) + 38.3);
        status.setActivityLevel("Ativo");
        status.setFeedConsumptionRate(Math.random() * (15 - 5) + 5);

        cattle.setStatus(status);
        repository.save(cattle);
        return createResponse(cattle);
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
}
