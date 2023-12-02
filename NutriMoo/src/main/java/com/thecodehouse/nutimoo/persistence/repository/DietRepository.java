package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.model.diet.DietResponse;
import com.thecodehouse.nutimoo.persistence.entity.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends MongoRepository<Diet,String> {
    Diet findByStageAndGoal(String stage, String goal);

}
