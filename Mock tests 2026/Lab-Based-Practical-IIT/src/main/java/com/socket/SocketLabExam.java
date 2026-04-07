/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.socket;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.Scanner;

/**
 * 5COSC022W Client Server Architectures Practical Exam: Socket Programming
 * Total Marks: 50
 *
 * INSTRUCTIONS:
 * 1. This code compiles but does not run correctly yet.
 * 2. Complete the TODO tasks with the correct implementation.
 */
public class SocketLabExam {

    /* TODO 1: Define the communication port. Set a constant integer named PORT with value 6500. [1 Mark]*/
    final static int PORT = 6500;
    
    

    /* TODO 2: Define the server host address. Set a constant String named HOST with value "localhost". [1 Mark]*/
    final static String HOST = "localhost";
    
    
    
    // Global entry point
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Mode: Type 'server' to run Server, 'client' to run Client:");
        String mode = scanner.nextLine();

        if (mode.equalsIgnoreCase("server")) {
            runServer();
        } else if (mode.equalsIgnoreCase("client")) {
            runClient();
        } else {
            System.out.println("Invalid mode selected. Exiting.");
        }
    }

    // ==========================================
    // SERVER SIDE LOGIC
    // ==========================================
    private static void runServer() {
        System.out.println("Starting Cloud Sensor Data Processor...");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        /* TODO 3: Initialise a Thread Pool.
           Instead of creating a new Thread for every client (which is unbounded),
           initialize the "threadPool" variable 
           to create a Fixed Thread Pool with 5 threads using the ExecutorService. 
           [3 Marks]
        */
        final int THREAD_POOL = 5;
        threadPool = Executors.newFixedThreadPool(THREAD_POOL);
        
        
        

        /* TODO 4: Create the ServerSocket.
           Initialise a ServerSocket object. Note that this action might throw an IOException,
           so ensure your code handles or declares it (try-catch is preferred here).
           [3 Marks]
        */
        // TODO: REPLACE null WITH appropriate code
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { 

            /* TODO 5: Bind the ServerSocket (If not done in constructor).
               If you used the default constructor above, bind it to the PORT here. 
               If you used the constructor taking the port, print a message saying 
               "Server listening on port " + PORT.
               [2 Mark]
            */
            System.out.println("Server listening on port " + PORT);
            
            
            
            

            /* TODO 6: Create the Infinite Loop.
               The server must run indefinitely to accept multiple clients.
               Create a standard while-loop that runs as long as the server is valid.
               [3 Marks]
            */
            while(true) {
            


                /* TODO 7: Accept an incoming connection.
                   This is a BLOCKING call. Call the method that waits for a client 
                   to connect and returns a Socket object. Then print that "New data node connected: " plus the IP Address of the Client.
                   [3 Marks]
                   TODO: REPLACE null WITH appropriate code
                */
                Socket clientSocket = serverSocket.accept(); 

                /* TODO 8: Submit the task to the Thread Pool.
                   Do NOT create a 'new Thread()'. Instead, pass a new instance of 
                   ClientHandler (defined below) to your threadPool's execute/submit method.
                   [3 Marks]
                */
                
                threadPool.execute(new ClientHandler(clientSocket)); 
            }  

            
                

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (threadPool != null) threadPool.shutdown();
        }
    }

    // ==========================================
    // CLIENT HANDLER (THREAD WORKER)
    // ==========================================
    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                /* TODO 9: Setup Input Stream (Buffered).
                   Wrap the socket's InputStream in an InputStreamReader, and then 
                   wrap that in a BufferedReader for efficiency.
                   [3 Marks]
                */
                BufferedReader in = new BufferedReader(
                              new InputStreamReader (socket.getInputStream()));
                
                                     
                
                

                /* TODO 10: Setup Output Stream (PrintWriter).
                   Wrap the socket's OutputStream in a PrintWriter.
                   CRITICAL: Enable "AutoFlush" (set the boolean argument to true) to ensure
                   data is sent immediately without filling the buffer first.
                   [3 Marks]
                */
               PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                
                

                /* TODO 11: Log Client Connection.
                   Print a message to the console indicating which IP address is being processed.
                   [2 Marks]
                */
                // TODO: Add System.out.println logic here
                System.out.println("Ip Address is being processed" + socket.getInetAddress());
                
                

                // Protocol Logic
                String request;
                
                /* TODO 12: Read from Client.
                   Create a loop that reads a line from the BufferedReader.
                   The loop should continue as long as the readLine() does not return null.
                   Inside the loop, print "Received sensor payload: " plus the request.
                   [4 Marks]
                */
                String lastRequest = "";
                while((request = in.readLine()) != null) {
                    System.out.println("Received sensor payload: " + request);
                    lastRequest = request;
                }
                
                

                    /* TODO 13: Transform Logic.
                       The server should process the message by converting it to uppercase.
                       Construct a response string: "Processed: " + request.toUpperCase().
                       [2 Marks]
                    */
                    String response = "Processed: " + request.toUpperCase(); // TODO: REPLACE "" WITH TRANSFORM LOGIC

                    /* TODO 14: Send Response to Client.
                       Use the PrintWriter to send the 'response' string back to the client.
                       [2 Marks]
                    */
                    out.println("Response: " + response);
                    
                    

               
                    

            } catch (Exception e) {
                System.out.println("Client disconnected or Timed Out");
            } finally {
                /* TODO 15: Clean up resources.
                   Ensure the socket is closed within this finally block to prevent
                   resource leaks.
                   [2 Marks]
                */
                if(socket != null && !socket.isClosed()) {
                    
                
                try {
                    socket.close();
                
                    
                    
                } catch (Exception e) { e.printStackTrace(); }
            }
        }}
    }

    // ==========================================
    // CLIENT SIDE LOGIC
    // ==========================================
    private static void runClient() {
        System.out.println("Starting Sensor Node Client...");

        
        try {

            /* TODO 16: Connect to Server.
            Initialise a Socket object attempting to connect to HOST and PORT.
            [3 Marks]
            */
            Socket socket = new  Socket(HOST,PORT);
            
            
            

            /* TODO 17: Setup Streams.
               Initialise a BufferedReader (for input) and PrintWriter (for output, with AutoFlush)
               connected to this socket.
               [3 Marks]
            */
            BufferedReader in = new BufferedReader(
                              new InputStreamReader (socket.getInputStream()));
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);           
            
            
            
            

            /* Interaction - Please note that the following BufferedReader is used to interact with input from keyboard
            which is different than the one you will implement in TODO 17
            */
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to Processor. Enter sensor payload (e.g., SENSOR:TEMP:45) or 'exit':");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) break;

                /* TODO 18: Send Request.
                   Send the 'userInput' to the server using the PrintWriter.
                   [3 Marks]
                */
                // TODO: Send message using 'out'
                out.println("userInput: " + userInput);
                
                
                

                /* TODO 19: Receive Response.
                   Read the server's response using the BufferedReader (readLine)
                   and print it to the console.
                   [3 Marks]
                */
                // TODO: Read response using 'in'
                String response = in.readLine();
                System.out.println("Server response: " + response);
                
                
                
                
                 
            }

            /* TODO 20: Close the Socket.
               Explicitly close the client socket when the user types 'exit' and the loop ends.
               [1 Marks]
            */
            // TODO: Close socket here
            socket.close();
            
            
            

        } catch (IOException e) {
            e.printStackTrace();
        }
   }
    
}