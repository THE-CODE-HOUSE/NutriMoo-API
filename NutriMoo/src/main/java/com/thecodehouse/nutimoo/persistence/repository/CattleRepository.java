package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CattleRepository extends MongoRepository<Cattle, String> {
    List<Cattle> findAllByStageAndGoal(String stage, String goal);
}

