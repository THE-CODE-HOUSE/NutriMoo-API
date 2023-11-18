package com.thecodehouse.nutimoo.model.cattle;

import com.thecodehouse.nutimoo.persistence.entity.Cattle;

import java.util.Date;

public class CattleRequest {
    private String tag;
    private String stage;
    private String breed;
    private String gender;
    private Double weight;
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
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
}