/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymocktestsocket;
import java.io.*;
import java.net.*;
/**
 *
 * @author astor
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",7777);
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        String[] tests = {"10 + 5", "9 / 2", "7 / 0", "abc + 1", "5 / 2", "100 - 37"};
        
        for (String expr : tests) {
            out.println(expr);
            System.out.println(expr + " -> " + in.readLine());
        }
        socket.close();
    }
}
