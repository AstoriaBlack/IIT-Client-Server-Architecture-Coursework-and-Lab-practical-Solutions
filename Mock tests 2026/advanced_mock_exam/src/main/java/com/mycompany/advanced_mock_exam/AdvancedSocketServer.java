package com.mycompany.advanced_mock_exam;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

/**
 * ============================================================
 *  ADVANCED MOCK LAB EXAM — Part 1: Multi-Threaded Socket Server
 *  Total Marks: 50
 * ============================================================
 *
 *  SCENARIO:
 *  You are building a concurrent echo server for a messaging
 *  platform. The server must handle multiple clients at once
 *  using a thread pool, and gracefully shut down on "SHUTDOWN".
 *
 *  INSTRUCTIONS:
 *  - Fill in ALL sections marked with TODO.
 *  - Do NOT modify method signatures or class structure.
 *  - Read each comment carefully before writing code.
 * ============================================================
 */
public class AdvancedSocketServer {

    private static final int PORT = 6666;
    private static final int POOL_SIZE = 10;

    public static void main(String[] args) {

        /* TODO 1: Declare and initialise an ExecutorService using a fixed
         * thread pool of size POOL_SIZE. This replaces raw Thread creation
         * and allows concurrent client handling.
         * (Hint: Executors.newFixedThreadPool). [3 Marks] */
       ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

        /* TODO 2: Declare a ServerSocket bound to PORT inside a try-with-resources
         * block so it is automatically closed on exit. [3 Marks] */
        try ( ServerSocket serverSocket = new ServerSocket(PORT))  {

            System.out.println("Server started on port " + PORT + ". Awaiting connections...");

            /* TODO 3: Write an infinite loop to keep accepting client connections. [2 Marks] */
            while(true) {

                /* TODO 4: Inside the loop, call the blocking accept() method on
                 * the ServerSocket to obtain a connected client Socket. [3 Marks] */
               Socket clientSocket = serverSocket.accept();

                /* TODO 5: Print the remote address of the newly connected client
                 * to the console for logging purposes. [2 Marks] */
              System.out.println("Client connected: " + clientSocket.getInetAddress());

                /* TODO 6: Submit a new ClientHandler (defined below), passing the
                 * client socket, to the ExecutorService using execute(). [3 Marks] */
//                ClientHandler handler = new ClientHandler(clientSocket);
//                threadPool.execute(handler);
                threadPool.execute(new ClientHandler(clientSocket));
               
            }

        } catch (IOException e) {

            /* TODO 7: Print the stack trace of the caught IOException. [1 Mark] */
            e.printStackTrace();

        } finally {

            /* TODO 8: Call shutdown() on the ExecutorService to gracefully stop
             * accepting new tasks once the server loop exits. [2 Marks] */
            threadPool.shutdown();

       
       }
    }


    /* -------------------------------------------------------
     * Inner class: ClientHandler
     * Each instance handles one connected client on its own thread.
     * ------------------------------------------------------- */

    /* TODO 9: Declare ClientHandler as a private static inner class that
     * implements the correct interface for use with an ExecutorService.
     * (Hint: Runnable). [2 Marks] */
    private static class ClientHandler implements Runnable {

        /* TODO 10: Declare a private final Socket field to hold the client connection. [1 Mark] */
       private final Socket clientSocket;

        /* TODO 11: Write a constructor that accepts a Socket parameter and
         * assigns it to the field declared above. [2 Marks] */
       public ClientHandler(Socket socket) {
           this.clientSocket = socket;
       }


        /* TODO 12: Override the correct method required by the Runnable interface.
         * This is the entry point that the thread pool will invoke. [2 Marks] */
       @Override
        public void run() {

            try (
                /* TODO 13: Inside try-with-resources, wrap the socket's InputStream
                 * in an InputStreamReader, then in a BufferedReader for line reading. [3 Marks] */
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                /* TODO 14: Wrap the socket's OutputStream in a PrintWriter.
                 * Pass 'true' as the second argument to enable auto-flush. [3 Marks] */
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            ) {
                String inputLine;

                /* TODO 15: Write a while loop that reads lines from the BufferedReader
                 * into inputLine as long as the result is not null. [3 Marks] */
               while((inputLine = in.readLine()) != null){
                    /* TODO 16: Print the received message to the server console
                     * prefixed with "Received: ". [1 Mark] */
                    System.out.println("Received " + inputLine);
                    

                    /* TODO 17: Check if inputLine equals "SHUTDOWN" (case-insensitive).
                     * If it does, send "Server shutting down." back to the client
                     * using the PrintWriter, then break out of the loop. [4 Marks] */
                   if(inputLine.equalsIgnoreCase("SHUTDOWN")){
                       out.println("Server shutting down");
                       break;
                   }

                    /* TODO 18: Otherwise, echo the message back to the client in
                     * the format "Echo: " + inputLine using the PrintWriter. [2 Marks] */
                    out.println("Echo: " + inputLine);
               }

            } catch (IOException e) {
                System.err.println("Client error: " + e.getMessage());
            } finally {

                /* TODO 19: In the finally block, check if the socket is not null
                 * and not already closed, then close it to free resources. [3 Marks] */
                try {
                     if(clientSocket != null && !clientSocket.isClosed()) {
                           clientSocket.close();
                     }                    
                     
            }catch (IOException e) {
                e.printStackTrace();
            }

           }
        }
    }
}