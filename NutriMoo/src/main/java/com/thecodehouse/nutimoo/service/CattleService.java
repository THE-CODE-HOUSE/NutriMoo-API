package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;

import java.util.List;

public interface CattleService {
    CattleResponse create(CattleRequest cattleRequest);

    List<CattleResponse> getAll();
}
