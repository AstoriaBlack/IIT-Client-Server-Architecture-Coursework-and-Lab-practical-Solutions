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

//This is the processor 
//This responsible for recieving and reading the message
//like the person recieving a letter
public class Reciever {
    
    private static final Logger logger = Logger.getLogger(Reciever.class.getName());
    
    public void recieveMessage(Message msg) {
//        System.out.println("Reciever: Message recieved!");
        //System.out.println("Content: "+ msg.getContent());
        logger.info("Message received.");
        logger.info("Content: " + msg.getContent());
    }
}
