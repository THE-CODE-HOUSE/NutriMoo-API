package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;

import java.util.Date;
import java.util.List;

public interface CattleService {
    // Aqui o nosso service, que é uma interface, onde declaramos os métodos que serão implementados
    // e depois chamados pelo nosso controller.
    void create(CattleRequest cattleRequest);
    List<CattleResponse> getAll();
    void updateCattleByTag(String tag, String stage, Boolean fertile, Boolean pregnant, Double weight, String goalS);
    void deleteCattle(String tag);
    boolean isTagExists(String tag);
}
