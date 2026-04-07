/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.tutorial01;
//We will configure logging here
import java.util.logging.*;

/**
 * This is a simple demonstration of software designing concept of client and server
 * This conncets everything like a system manager
 * Client (Sender) → Server (Receiver) → Data (Message)
 * this is how real systems works
 */
public class Tutorial01 {
    
    private static final Logger logger = Logger.getLogger(Tutorial01.class.getName());

    public static void main(String[] args) {
        
        configureLogger();
        //very important since this is where the logging behave
        Sender sender = new Sender();
        Reciever reciever = new Reciever();
        
        //create message
        Message msg = sender.createMessage("Hello from sender with logging!");
        
        //send message
        sender.sendMessage(msg, reciever);
    }
    
    private static void configureLogger() {
        try {
            //gets the root logger (controlls everything)
            Logger globalLogger = Logger.getLogger("");
            //get existing handlers
            Handler[] handlers = globalLogger.getHandlers();
            
            //remove default settings(clean start)
            for(Handler handler : handlers) {
                globalLogger.removeHandler(handler);
            }
            
            //create console output + allow all logs
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            
            //Apply settings
            globalLogger.addHandler(consoleHandler);
            globalLogger.setLevel(Level.ALL);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error configuring logger: " + e.getMessage(), e);
        }
    }
}
