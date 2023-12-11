package com.thecodehouse.nutimoo.service;

import com.thecodehouse.nutimoo.model.cattle.CattleRequest;
import com.thecodehouse.nutimoo.model.cattle.CattleResponse;
import com.thecodehouse.nutimoo.persistence.entity.Cattle;
import com.thecodehouse.nutimoo.persistence.repository.CattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@Service
public class CattleServiceImpl implements CattleService{
// Aqui é onde os métodos do nosso servico serão implementados
    @Autowired
    private CattleRepository repository;
    @Override
    public void create(CattleRequest cattleRequest) {
        // Aqui vamos instanciar um novo Cattle, com os dados que recebemos do cattleRequest, que são enviados do front
        // Por padrão alguns dados são inseridos por padrão, como a meta
        // e alguns são aleatorios, como os do Status, que é uma classe static que fica dentro do Cattle,
        // porque esses "Dados" irão vir teoricamente de um chip
        Cattle cattle = new Cattle();
        cattle.setTag(cattleRequest.getTag());
        cattle.setStage(cattleRequest.getStage());
        cattle.setBreed(cattleRequest.getBreed());
        cattle.setGender(cattleRequest.getGender());
        cattle.setWeight(cattleRequest.getWeight());
        cattle.setBirthDate(cattleRequest.getBirthDate());
        cattle.setPregnant(false);
        cattle.setFertile(!cattle.getStage().equals("Bezerra/Novilha")&&!cattle.getStage().equals("Boi"));
        if(!cattle.getStage().equals("Vaca")){
            cattle.setGoal("Ganhar Peso");
        } else {
            cattle.setGoal("Manter Peso");
        }

        Cattle.Status status = new Cattle.Status();
        status.setHeartRate(formatDouble(Math.random() * (80 - 40) + 40));
        status.setTemperature(formatDouble(Math.random() * (39.2 - 38.3) + 38.3));
        status.setActivityLevel("Ativo");
        status.setFeedConsumptionRate(formatDouble(Math.random() * (15 - 5) + 5));

        cattle.setStatus(status);
        repository.save(cattle);  // aqui usamos o método save para salvar no banco de dados
    }
    private double formatDouble(double value) {
        // método para formatar um valor double para 2 casas decimais
        DecimalFormat model = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(model.format(value));
    }
    public boolean isTagExists(String tag) {
        // método que verifica se a tag existe no banco de dados
        return repository.existsByTag(tag);
    }

    @Override
    public List<CattleResponse> getAll() {
        // método que vai pegar todos os documentos que estão no banco e vai guardar dentro de uma List
        // depois ira pegar cada um desses documentos e criar uma Response e colocar em uma ArrayList
        // isso sim será utilizado pelo controller
        List<CattleResponse> responses = new ArrayList<>();
        List<Cattle> cattles = repository.findAll();

        if(!cattles.isEmpty()){
            cattles.forEach(cattle -> responses.add(createResponse(cattle)));
        }

        return responses;
    }
    private CattleResponse createResponse(Cattle cattle) {
        // esse método cria uma Response com os valores do cattle.
        CattleResponse response = new CattleResponse();
        response.setTag(cattle.getTag());
        response.setStage(cattle.getStage());
        response.setBreed(cattle.getBreed());
        response.setGender(cattle.getGender());
        response.setWeight(cattle.getWeight());
        response.setGoal(cattle.getGoal());
        response.setStatus(cattle.getStatus());
        response.setPregnant(cattle.isPregnant());
        response.setFertile(cattle.isFertile());
        response.setBirthDate(cattle.getBirthDate());

        return response;
    }

    public void updateCattleByTag(String tag, String stage, Boolean fertile, Boolean pregnant, Double weight, String goal) {
        // Aqui usamos um Optinal para o resultado do findByTag depois vemos se pegamos o resultado (Sei que vamos pegar)
        // então alteramos os dados que vieram por parametro e salvamos naquele cattle da tag
        Optional<Cattle> optionalCattle = repository.findByTag(tag);

        if (optionalCattle.isPresent()) {
            Cattle cattle = optionalCattle.get();
            cattle.setStage(stage);
            cattle.setFertile(fertile);
            cattle.setPregnant(pregnant);
            cattle.setWeight(weight);
            cattle.setGoal(goal);
            repository.save(cattle);
        }
    }
    public void deleteCattle(String tag) {
        repository.deleteByTag(tag);
    } // esse método deleta um cattle pela tag
}
