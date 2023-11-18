package com.thecodehouse.nutimoo.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Cattle")
public class Cattle {

    @Id
    private String id;
    private String tag;
    private String breed;
    private String stage;
    private String gender;
    private Double weight;
    private String goal;
    private Status status;
    private boolean pregnant;
    private boolean fertile;
    private Date birthDate;


    /*public Cattle(String tag, String breed, String stage, String gender, double weight, String goal, Status status, boolean pregnant, Date date) {
        this.tag = tag;
        this.breed = breed;
        this.stage = stage;
        this.gender = gender;
        this.weight = weight;
        this.goal = goal;
        this.status = status;
        this.pregnant = pregnant;
        this.birthDate = date;
    }

    public Cattle(String tag, String breed, String stage, String gender, double weight, String goal, Status status, Date date,boolean fertilityStatus) {
        this.tag = tag;
        this.breed = breed;
        this.stage = stage;
        this.gender = gender;
        this.weight = weight;
        this.goal = goal;
        this.status = status;
        this.fertile = fertilityStatus;
        this.birthDate = date;
    }*/

    public Cattle() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean isPregnant() {
        return pregnant;
    }
    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }
    public boolean isFertile() {
        return this.fertile;
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
    public static class Status {
        private double heartRate;
        private double temperature;
        private String activityLevel;
        private double feedConsumptionRate;
        public Status(){}
        public double getHeartRate() {
            return heartRate;
        }
        public void setHeartRate(double heartRate) {
            this.heartRate = heartRate;
        }
        public double getTemperature() {
            return temperature;
        }
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
        public String getActivityLevel() {
            return activityLevel;
        }
        public void setActivityLevel(String activityLevel) {
            this.activityLevel = activityLevel;
        }
        public double getFeedConsumptionRate() {
            return feedConsumptionRate;
        }
        public void setFeedConsumptionRate(double feedConsumptionRate) {
            this.feedConsumptionRate = feedConsumptionRate;
        }
    }

}
