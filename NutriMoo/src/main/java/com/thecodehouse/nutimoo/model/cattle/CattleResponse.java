package com.thecodehouse.nutimoo.model.cattle;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;

import java.util.Date;

public class CattleResponse {
    // Objeto que é enviado para o front
    // Como essa classe é apenas para receber as requisições do front, não é usado os getters
    private String tag;
    private String breed;
    private String stage;
    private String gender;
    private Double weight;
    private String goal;
    private Cattle.Status status;
    private boolean pregnant;
    private boolean fertile;
    private Date birthDate;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Cattle.Status getStatus() {
        return status;
    }

    public void setStatus(Cattle.Status status) {
        this.status = status;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isFertile() {
        return fertile;
    }

    public void setFertile(boolean fertile) {
        this.fertile = fertile;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
