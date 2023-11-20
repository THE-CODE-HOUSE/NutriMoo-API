package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.persistence.entity.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends MongoRepository<Diet,String> {

}
