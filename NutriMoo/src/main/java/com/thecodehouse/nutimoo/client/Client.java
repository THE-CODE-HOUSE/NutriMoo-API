package com.thecodehouse.nutimoo.client;
import java.net.*;

import message.*;
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

      connection = new Socket(host, port); //instanciando um novo socket a partir do host e port definidos a cima.
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
      server = connection(); // chama o meto para estabelecer a conexao
    }catch (Exception e){
      System.err.println ("Indique o servidor e a porta corretos!\n");

    }
    DisconnectionMessageHandler disconnectionMessageHandler = null;
    try{
          disconnectionMessageHandler = new DisconnectionMessageHandler (server);
      }catch(Exception e){}

    disconnectionMessageHandler.start();// starta a thread que verifica se o cliente esta conectado
    try{
      EmRequest emRequest = new EmRequest(weight);  //envia uma Mensagem do
      server.send(emRequest);                       //tipo EMrequest para o servidor passando o peso
    }catch(Exception e){
      System.err.println ("Erro de comunicacao com o servidor;");
      System.err.println ("Tente novamente!");
      System.err.println ("Caso o erro persista, termine o programa");
      System.err.println ("e volte a tentar mais tarde!\n");
    }
    try{

      server.send(new EmResponse()); //envia uma mensagem solicitando uma resposta

      Message message = null;
      do{
        message = (Message)server.peek();     //espia o tipo da
      }while(!(message instanceof Result));   //resposta e so sai do while se for a do tipo resultado

      result = (Result)server.receive();//recebe a resposta

    }catch(Exception e){
      System.out.println ("Erro de comunicacao com o servidor;");
      System.out.println ("Tente novamente!");
      System.out.println ("Caso o erro persista, termine o programa");
      System.out.println ("e volte a tentar mais tarde!\n");
    }

    try{
          server.send (new ExitRequest ()); //envia uma mensagem do tipo ExitRequest falando que ira se desconectar
      }catch (Exception e){}
//    System.out.println ("Obrigado por usar este programa!");

    String secsu = ""+result;
    return Double.parseDouble(secsu);


  }

}