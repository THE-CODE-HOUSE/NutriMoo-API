package com.thecodehouse.nutimoo.model.diet;

public class DietRequest {// objeto que o "front" mandara para a api
    private String stage;
    private String goal;

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
}
