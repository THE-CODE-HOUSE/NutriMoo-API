package com.thecodehouse.nutimoo.service.Client;
import org.springframework.stereotype.Component;

@Component
public class EmRequest extends Message{
  private double value;

  public EmRequest( double value){
    this.value = value;
  }
  public double getValue(){
    return this.value;
  }
  public String toString(){
    return (""+this.value);
  }
}
