/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket2;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author astor
 */
public class ClientHandler implements Runnable{
    
    private Socket clientsocket;
    
    //constructor
    public ClientHandler(Socket socket) {
        this.clientsocket = socket;
    }
    
    @Override
    public void run() {
        try {
            InputStream inputStream = clientsocket.getInputStream();
            OutputStream outputStream = clientsocket.getOutputStream();
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while((bytesRead = inputStream.read(buffer)) != -1) {
                
                String message = new String(buffer,0,bytesRead);
                System.out.println("Client "+ clientsocket.getInetAddress() + ": " + message);
                
                String response = "Server received: " + message;
                
                outputStream.write(response.getBytes());
            }
            
            clientsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
