package com.mycompany.lab_based_practical_mock_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Lab Exam Part 1: Socket Programming (50 Marks)
 * * Scenario: You are building a multi-threaded Echo Server.
 * The server must accept multiple clients. For every message sent by a client,
 * the server must reply with "Echo: " followed by the message.
 */
public class SocketServerExam {

    public static void main(String[] args) {
        
        /* TODO 1: Define a port number (e.g., 6666) to listen on. [1 Mark] */
        final int PORT = 6666;

        
        /* TODO 2: Initialize a ServerSocket object bound to the defined port. 
         * Use a try-catch block or declare throws to handle IOException. [2 Marks] */
        


        System.out.println("Server started. Waiting for connections...");

        try(ServerSocket serverSocket = new ServerSocket(PORT);)
        {
            /* TODO 3: Create an infinite loop to keep the server running and accepting clients. [2 Marks] */
            while(true) {

            
                /* TODO 4: Inside the loop, call the method to block and wait for an incoming client connection.
                 * Assign the result to a Socket object. [3 Marks] */
                Socket clientSocket = serverSocket.accept();

                
                /* TODO 5: Print a message to the console indicating a new client has connected. [1 Mark] */
                System.out.println("A new client connected: " + clientSocket.getInetAddress());


                /* TODO 6: Create a new instance of the ClientHandler class (defined below).
                 * Pass the connected socket to its constructor. [2 Marks] */
                ClientHandler handler = new ClientHandler(clientSocket);


                /* TODO 7: Create a new Thread object, passing the ClientHandler instance to it. [3 Marks] */
                Thread thread = new Thread (handler);


                /* TODO 8: Start the thread to handle this client concurrently. [2 Marks] */
                thread.start();
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inner class to handle individual client connections on a separate thread.
     * Implements Runnable to decouple logic from the main thread.
     */
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            /* TODO 9: Initialize BufferedReader and PrintWriter variables to null here
             * so they can be closed in the finally block (or use try-with-resources). [1 Mark] */
            BufferedReader in = null;
            PrintWriter out = null;


            try {
                /* TODO 10: Obtain the InputStream from the clientSocket. [2 Marks] */
                InputStream inputStream = clientSocket.getInputStream();


                /* TODO 11: Wrap the InputStream in an InputStreamReader to handle character decoding. [2 Marks] */
                InputStreamReader reader = new InputStreamReader(inputStream);


                /* TODO 12: Wrap the InputStreamReader in a BufferedReader to enable efficient line-by-line reading. [3 Marks] */
                in = new BufferedReader(reader);


                /* TODO 13: Obtain the OutputStream from the clientSocket. [2 Marks] */
                OutputStream outputStream = clientSocket.getOutputStream();
                


                /* TODO 14: Wrap the OutputStream in a PrintWriter.
                 * IMPORTANT: Enable 'auto-flush' in the constructor to ensure data is sent immediately. [3 Marks] */
                out = new PrintWriter(outputStream,true);


                String inputLine;

                /* TODO 15: Create a while loop to read data from the client.
                 * The loop should continue as long as (inputLine = in.readLine()) is NOT null. [4 Marks] */
                while((inputLine = in.readLine()) != null) {

                
                    /* TODO 16: Inside the loop, print the received message to the server console for debugging. [1 Mark] */
                    System.out.println("Received: " + inputLine);


                    /* TODO 17: Send a response back to the client using the PrintWriter.
                     * The format should be "Echo: " + inputLine. [3 Marks] */
                    out.println("Echo: " + inputLine);
                }

                

            } catch (IOException e) {
                System.err.println("Client disconnected or error occurred.");
            } finally {
                try {
                    /* TODO 18: Close the clientSocket to release resources. [2 Marks] */
                    if(clientSocket != null && clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                    
                    
                    /* TODO 19: Close the BufferedReader. [2 Marks] */
                    if(in != null) {
                        in.close();
                    }
                    
                    
                    /* TODO 20: Close the PrintWriter. [2 Marks] */
                    if(out != null) {
                        out.close();
                    }
                    
                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}