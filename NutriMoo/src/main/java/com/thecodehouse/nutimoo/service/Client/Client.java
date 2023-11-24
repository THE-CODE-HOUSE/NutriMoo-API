package com.thecodehouse.nutimoo.service.Client;
import java.net.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
 

@Service
public class Client{


  // private final
  // private final
  // private final
  // private final
  // private final
  // private final
  // private final





  public static final String DEFAULT_HOST = "localhost";

  public static final int DEFAULT_PORT = 3000;

  public void connection(){
    
  }
  
  
  public double emCalculation(double weight){

    Socket connection = null;
    try {
      String host = Client.DEFAULT_HOST;
      int    port = Client.DEFAULT_PORT;

      connection = new Socket(host, port);
  }catch(Exception e){
    System.err.println("Indique o servidor e a porta corretos!\n");
    return;
  }

  ObjectOutputStream transmissor = null;
  try{
    transmissor = new ObjectOutputStream(connection.getOutputStream());
  }catch(Exception e){
    System.err.println ("Indique o servidor e a porta corretos!\n");
    return;
  }
  
  ObjectInputStream receptor = null;
  try{
    receptor = new ObjectInputStream(connection.getInputStream());
  }catch(Exception e){
    System.err.println ("Indique o servidor e a porta corretos!\n");
    return;
  }

  Bro server = null;
  try{
    server = new Bro (connection, receptor, transmissor);
  }catch (Exception e){
    System.err.println ("Indique o servidor e a porta corretos!\n");
    return;
  }

  DisconnectionMessageHandler disconnectionMessageHandler = null;
  try{
		disconnectionMessageHandler = new DisconnectionMessageHandler (server);
	}catch(Exception e){}

  disconnectionMessageHandler.start();
  try{
    server.send(new EmRequest(weight));
  }catch(Exception e){
    System.err.println ("Erro de comunicacao com o servidor;");
    System.err.println ("Tente novamente!");
    System.err.println ("Caso o erro persista, termine o programa");
    System.err.println ("e volte a tentar mais tarde!\n");    
  }
  try{
		server.send (new ExitRequest ());
	}catch (Exception e){}
  System.out.println ("Obrigado por usar este programa!");
	System.exit(0);
  }
}