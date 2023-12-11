package com.thecodehouse.nutimoo.persistence.repository;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CattleRepository extends MongoRepository<Cattle, String> {
    // Aqui é uma interface que herda da MongoRepository passando Cattle como tipo e também o tipo do id, que é String
    // essa Classe que herdamos é capaz de fazer as "Querys" de um CRUD no mongo apenas pelos nomes dos métodos.

    Optional<Cattle> findByTag(String tag);
    void deleteByTag(String tag);
    List<Cattle> findAllByStageAndGoal(String stage, String goal);
    boolean existsByTag(String tag);
}

