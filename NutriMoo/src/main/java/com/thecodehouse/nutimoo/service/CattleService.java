package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;

import java.util.Date;
import java.util.List;

public interface CattleService {
    void create(CattleRequest cattleRequest);
    List<CattleResponse> getAll();
    void updateCattleByTag(String tag, String stage, Boolean fertile, Boolean pregnant, Double weight, String goalS);
    void deleteCattle(String tag);
}
