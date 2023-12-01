package com.thecodehouse.nutimoo.client;
import java.net.*;

import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class Client {






  public static final String DEFAULT_HOST = "localhost";

  public static final int DEFAULT_PORT = 3000;

  public Bro connection() throws Exception{
    Socket connection = null;
    try {
      String host = Client.DEFAULT_HOST;
      int    port = Client.DEFAULT_PORT;

      connection = new Socket(host, port);
    }catch(Exception e){
      throw new Exception("Indique o servidor e a porta corretos!\n");

    }

    ObjectOutputStream transmissor = null;
    try{
      transmissor = new ObjectOutputStream(connection.getOutputStream());
    }catch(Exception e){
      throw new Exception ("Indique o servidor e a porta corretos!\n");

    }

    ObjectInputStream receptor = null;
    try{
      receptor = new ObjectInputStream(connection.getInputStream());
    }catch(Exception e){
     throw new Exception ("Indique o servidor e a porta corretos!\n");
    }
    return new Bro(connection,receptor,transmissor);
  }
  
  
  public double emCalculation(double weight){
    Bro server = null;
    Result result = null;
    try{
      server = connection();
    }catch (Exception e){
      System.err.println ("Indique o servidor e a porta corretos!\n");

    }
    DisconnectionMessageHandler disconnectionMessageHandler = null;
    try{
          disconnectionMessageHandler = new DisconnectionMessageHandler (server);
      }catch(Exception e){}

    disconnectionMessageHandler.start();
    try{
      EmRequest emRequest = new EmRequest(weight);
      server.send(emRequest);
    }catch(Exception e){
      System.err.println ("Erro de comunicacao com o servidor;");
      System.err.println ("Tente novamente!");
      System.err.println ("Caso o erro persista, termine o programa");
      System.err.println ("e volte a tentar mais tarde!\n");
    }
    try{

      server.send(new EmResponse());

      Message message = null;
      do{
        message = (Message)server.peek();
      }while(!(message instanceof Result));

      result = (Result)server.receive();
      System.out.println("Resultado atual: "+result.getResult()+"\n");


    }catch(Exception e){
      System.out.println ("Erro de comunicacao com o servidor;");
      System.out.println ("Tente novamente!");
      System.out.println ("Caso o erro persista, termine o programa");
      System.out.println ("e volte a tentar mais tarde!\n");
    }

    try{
          server.send (new ExitRequest ());
      }catch (Exception e){}
//    System.out.println ("Obrigado por usar este programa!");

    String secsu = ""+result;
    return Double.parseDouble(secsu);


  }

}