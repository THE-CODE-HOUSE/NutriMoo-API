package com.thecodehouse.nutimoo.model.diet;

import com.thecodehouse.nutimoo.persistence.entity.Ingredients;

public class DietResponse {
    private String stage;
    private String goal;
    private double em;
    private double cms;
    private double protein;
    private double fat;
    private double carbohydrates;
    private Ingredients[] foods;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getEm() {
        return em;
    }

    public void setEm(double em) {
        this.em = em;
    }

    public double getCms() {
        return cms;
    }

    public void setCms(double cms) {
        this.cms = cms;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Ingredients[] getFoods() {
        return foods;
    }

//    public void setFoods(Ingredients foods, int indice) {
//
//        this.foods[indice] = foods;
//    }
}
