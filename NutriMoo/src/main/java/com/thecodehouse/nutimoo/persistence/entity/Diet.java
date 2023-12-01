package com.thecodehouse.nutimoo.persistence.entity;


import com.thecodehouse.nutimoo.model.diet.Foods;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "Diet" )
public class Diet  {


    @Id
    private String id;
    private String stage;
    private String goal;
    private double em;
    private double cms;
    private double protein;
    private double fat;
    private double carbohydrates;
    private Foods[] foods;

    public Diet() {
    }
    public Diet(String id, String stage, String goal, double em, double cms, double protein, double fat, double carbohydrates, Foods[] foods) {
        this.id = id;
        this.stage = stage;
        this.goal = goal;
        this.em = em;
        this.cms = cms;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.foods = foods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Foods[] getFoods() {
        return foods;
    }

    public void setFoods(Foods[] foods) {
        this.foods = foods;
    }
}