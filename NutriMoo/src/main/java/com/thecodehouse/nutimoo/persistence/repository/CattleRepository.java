package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CattleRepository extends MongoRepository<Cattle, String> {
    Optional<Cattle> findByTag(String tag);
    void deleteByTag(String tag);
    List<Cattle>findAllByStageAndGoal(String stage, String goal);
}

