package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;

import java.util.Date;
import java.util.List;

public interface CattleService {
    void create(CattleRequest cattleRequest);
    List<CattleResponse> getAll();
    void updateCattle(String id, String tag, String stage, String breed, Date birthDate, Double weight);
    void deleteCattle(String id);
}
