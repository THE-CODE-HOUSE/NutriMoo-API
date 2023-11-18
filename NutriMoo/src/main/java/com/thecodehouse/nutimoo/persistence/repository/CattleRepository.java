package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CattleRepository extends MongoRepository<Cattle, String> {

}

