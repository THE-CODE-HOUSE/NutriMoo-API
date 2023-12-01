package com.thecodehouse.nutimoo.model.diet;

public class Foods {
    private String nome;
    private double protein;
    private double fat;
    private double carbohydrates;
    private double cms;

    public Foods( String nome, double protein, double fat, double carbohydrates,  double cms) {
        this.nome = nome;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.cms = cms;
    }


    public double getCms() {
        return cms;
    }

    public void setCms(double cms) {
        this.cms = cms;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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



}
