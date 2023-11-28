package com.thecodehouse.nutimoo.service.Client;

import org.springframework.stereotype.Component;


public class Result extends Message{
    private double result;

    public Result (double result){
        this.result=result;
    }

    public double getResult (){
        return this.result;
    }

    public String toString(){
        return(""+this.result);
    }
}
