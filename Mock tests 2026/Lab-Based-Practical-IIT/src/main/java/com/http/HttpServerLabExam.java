/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 5COSC022W Client Server Architectures Practical Exam: HTTP Server Programming
 * Total Marks: 50
 *
 * INSTRUCTIONS:
 * 1. This code compiles but does not run correctly yet.
 * 2. Complete the TODO tasks with the correct implementation.
 */
public class HttpServerLabExam {

    /* TODO 1: Define the Server Port.
       Set a constant integer named PORT with value 9090.
       [1 Mark]
     */
    
    
    private static final int PORT = 9090;
//    private static final int THREAD_POOL = 10; //I will place a constant here for more sophisticated way but ultimately 
    //decided to leave it near the question
    

    public static void main(String[] args) {
        try {
            System.out.println("Initialising Edge Computing Node Server...");

            /* TODO 2: Create the HttpServer Instance.
               Use the HttpServer factory method to create a server.
               Bind it to the PORT defined above.
               Set the backlog (queue depth) to 0 (default).
               [4 Marks]
             */
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0 );
            
            
            

            /* TODO 3: Create "Ping" Context.
               Map the path "/ping" to a new instance of PingHandler (defined below).
               [2 Marks]
             */
            server.createContext("/ping", new PingHandler());
            
            
            
            

            /* TODO 4: Create "Node Config" Context.
               Map the path "/api/node/config" to a new instance of ConfigHandler.
               [3 Marks]
             */
            server.createContext("/api/node/config", new ConfigHandler());
           
            
            

            /* TODO 5: Enable Multithreading (Executor).
               By default, the server is single-threaded.
               Set the executor to use a Fixed Thread Pool with 10 threads to handle concurrent requests.
               [3 Marks]
             */
            
            final int THREAD_POOL = 10;
            ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL);
            
            
            

            /* TODO 6: Start the Server.
               Call the method to begin listening for connections.
               [1 Mark]
             */
            server.start();
            
            
            
            
            System.out.println("Edge Server started on port " + PORT);

        } catch (Exception e) { // Changed to Exception to catch potential null pointers during skeleton run
            e.printStackTrace();
        }
    }

    // ==========================================
    // HANDLER 1: PING STATUS
    // ==========================================
    static class PingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Logic: Always return a JSON active state
            String response = "{\"status\": \"Node Active\"}";

            /* TODO 7: Set Response Headers (Content-Type).
               Before sending the response, set the "Content-Type" header to "application/json".
               [3 Marks]
             */
            
            Headers header = t.getResponseHeaders();
            header.set("Content-Type", "application/json");
            
            
            

            /* TODO 8: Send Response Headers (Status & Length).
               Send the HTTP Status Code 200 (OK) and the length of the response string.
               CRITICAL: You must convert the string length to bytes length if using special chars,
               but for simple text, response.length() is usually fine.
               [4 Marks]
             */
            t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);            
            
            

            /* TODO 9: Write Response Body.
               1. Get the Response Body OutputStream.
               2. Write the response string bytes to it.
               3. Close the stream (Try-with-resources is recommended but not strict).
               [4 Marks]
             */
            // Replace null wih appropriate implementation
            try (OutputStream os = t.getResponseBody()) { 
                
                    /*Implement the code below to Write the response string bytes to it*/
                    os.write(response.getBytes());
                    
                    
                
            }
        }
    }

    // ==========================================
    // HANDLER 2: EDGE CONFIGURATION
    // ==========================================
    static class ConfigHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            /* TODO 10: Identify Request Method.
               Get the HTTP Method (GET, POST, etc.) from the exchange object.
               [3 Marks]
             */
            String method = t.getRequestMethod(); // TODO: REPLACE "" to invoke a method to get request method.

            if (method.equalsIgnoreCase("GET")) {
                handleGet(t);
            } else if (method.equalsIgnoreCase("POST")) {
                handlePost(t);
            } else {
                /* TODO 11: Handle Unsupported Methods.
                   If the method is not GET or POST, send a 405 (Method Not Allowed) response.
                   The response body length can be -1 (no body) or 0.
                   [3 Marks]
                   
                 */
                t.sendResponseHeaders(405, -1);
                
                
                
            }
        }

        private void handleGet(HttpExchange t) throws IOException {
            /* TODO 12: create a response 
               string "{\"mode\": \"AI-Processing\"}".
               [2 Marks]
             */
            String response = "{\"mode\": \"AI-Processing\"}"; // TODO: REPLACE "" WITH "{\"mode\": \"AI-Processing\"}"

            /* TODO 13: Send 200 OK Response.
               Send headers (200) and write the response body.
               [4 Marks]
             */
            t.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            try (OutputStream os = t.getResponseBody()) { 
                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
            
            
            
        }

        private void handlePost(HttpExchange t) throws IOException {
            /* TODO 14: Read Request Body.
               1. Get the Request Body InputStream.
               2. Read all bytes from the stream.
               3. Convert the bytes to a String (assume UTF-8).
               [5 Marks]
             */
            InputStream is = t.getRequestBody(); // TODO: REPLACE null to invoke a method to get request body
            String body = is.readAllBytes().toString();      // TODO: REPLACE "" WITH Logic to read stream
            
            System.out.println("Received edge configuration payload: " + body);

            /* TODO 15: Mock Update Logic.
               Create a response string: "Configuration updated to: " + body
               [2 Marks]
             */
            String response = "Configuration updated to: " + body; // TODO: REPLACE "" WITH "Configuration updated to: " + body

            /* TODO 16: Send 200 OK Response.
               Send the response headers (200) and the response body.
               [4 Marks]
             */
            t.sendResponseHeaders(200, response.getBytes().length);
           
            try (OutputStream os = t.getResponseBody()) {
                                os.write(response.getBytes(StandardCharsets.UTF_8));
            }
            
            is.close();
            
            

             /* TODO 17: Resource Management.
               Ensure the InputStream 'is' is closed if you opened it manually 
               (or rely on try-with-resources if you implemented it that way).
               [2 Marks]
             */
             
                 is.close();
             
             
             
             
        }
    }
}