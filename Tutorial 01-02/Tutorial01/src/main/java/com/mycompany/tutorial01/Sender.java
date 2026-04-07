/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial01;
import java.util.logging.Logger;

/**
 *
 * @author astor
 */

//this is the created and sender
//like a person sending a letter
public class Sender {
    
    private static final Logger logger = Logger.getLogger(Sender.class.getName());
    
    public Message createMessage(String text) {
        Message msg = new Message(text);
//        System.out.println("Message created");
//let's replace with logger
          logger.info("Message created.");
        return msg;
    }
    
    public void sendMessage(Message msg, Reciever reciever){
//        System.out.println("Sender: Sending message to receiver...");
          logger.info("Sending message to reciever...");
        reciever.recieveMessage(msg);
    }
}
