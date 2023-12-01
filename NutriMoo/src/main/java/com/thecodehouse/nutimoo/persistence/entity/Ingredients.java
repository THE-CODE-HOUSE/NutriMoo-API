package com.thecodehouse.nutimoo.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;
@Document(collection = "Ingredients")
public class Ingredients{

    @Id
    private String id;

    private String nome;
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrates;

    public Ingredients( String nome, double energy, double protein, double fat, double carbohydrates) {

        this.nome = nome;
        this.energy = energy;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


}