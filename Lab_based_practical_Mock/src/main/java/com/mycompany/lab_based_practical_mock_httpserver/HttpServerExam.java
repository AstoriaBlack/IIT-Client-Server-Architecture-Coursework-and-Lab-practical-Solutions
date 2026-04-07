package com.mycompany.lab_based_practical_mock_httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;

/**
 * Lab Exam Part 2: HTTP Server (50 Marks)
 * * Scenario: Create a simple HTTP server using the built-in Java library.
 * It should handle two contexts:
 * 1. "/" -> Returns a simple HTML message.
 * 2. "/data" -> Accepts a POST request and returns a mock JSON response.
 * * Lecture References: Week 03 (Slides 21-39)
 */
public class HttpServerExam {

    public static void main(String[] args) throws IOException {
        
        /* TODO 1: Create an HttpServer instance using the factory method.
         * Bind it to port 8000 and set the backlog to 0. 
         * (Lecture Slide 21: HttpServer.create). [3 Marks] */
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);

        /* TODO 2: Create a context for the path "/" and associate it with the RootHandler. 
         * (Lecture Slide 24: Routing Logic). [2 Marks] */
        server.createContext("/", new RootHandler());


        /* TODO 3: Create a context for the path "/data" and associate it with the DataHandler. [2 Marks] */
        server.createContext("/data", new DataHandler());

        /* TODO 4: Set the executor to null. 
         * This creates a default execution model (single-lane bottleneck). 
         * (Lecture Slide 33: Default Dispatcher). [2 Marks] */
        server.setExecutor(null);

        /* TODO 5: Start the server. [2 Marks] */
        server.start();


        System.out.println("HTTP Server started on port 8000...");
    }

    // Handler for the root path "/"
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            /* TODO 6: Retrieve the Request Method from the exchange object. [2 Marks] */
            String method = exchange.getRequestMethod();

            /* TODO 7: Write an if-statement to check if the method is "GET". [2 Marks] */
            if(method.equalsIgnoreCase("GET")){
                
                           
                String response = "<html><body><h1>Welcome to the Lab Exam</h1></body></html>";
                
                /* TODO 8: Send the Response Headers. 
                 * Set the status code to 200 (OK) and the length to the length of the response bytes. [4 Marks] */
               exchange.sendResponseHeaders(200, response.length());

                /* TODO 9: Get the Response Body as an OutputStream. [2 Marks] */
               OutputStream os = exchange.getResponseBody();

                /* TODO 10: Write the response string (converted to bytes) to the output stream. [2 Marks] */
               os.write(response.getBytes());

                /* TODO 11: Close the output stream to finish the transaction. [2 Marks] */
                os.close();

            } else {                
                exchange.sendResponseHeaders(405, -1);
            /* TODO 12: Add an 'else' block. If the method is NOT "GET", send a 405 (Method Not Allowed) response.
             * Set content length to -1 (no body). [3 Marks] */
            }
        }
    }

    // Handler for the "/data" path
    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            /* TODO 13: Check if the request method is "POST". 
             * If it is NOT POST, send a 405 response and return immediately. [3 Marks] */
           String method = exchange.getRequestMethod();
           if(!method.equalsIgnoreCase("POST")){
              exchange.sendResponseHeaders(405, -1);
              return;
           }

            /* TODO 14: Retrieve the Request Headers. [2 Marks] */
           Headers header = exchange.getRequestHeaders();
            
            /* TODO 15: Retrieve the "User-Agent" header from the headers object and print it to the console. 
             * (This is useful for debugging/logging). [3 Marks] */
           String userAgent = header.getFirst(method);
           System.out.println("user-Agent: "+ userAgent);

            /* TODO 16: Get the Request Body as an InputStream. [2 Marks] */
           InputStream is = exchange.getRequestBody();

            /* TODO 17: Read all bytes from the input stream and store them in a byte array. 
             * (This simulates reading the payload sent by the client). [2 Marks] */
           byte[] inputBytes = is.readAllBytes();

            // Mock processing - we just ignore the input for this exam and send a JSON response.
           String jsonResponse = "{ \"status\": \"success\", \"message\": \"Data received\" }";

            /* TODO 18: Set a specific response header "Content-Type" to "application/json".
             * Use exchange.getResponseHeaders().set(...). [3 Marks] */
           exchange.getResponseHeaders().set("Content-Type", "application/json");

            /* TODO 19: Send response headers: Status 200 and the length of the jsonResponse bytes. [3 Marks] */
           exchange.sendResponseHeaders(200,jsonResponse.getBytes().length);

            /* TODO 20: Write the jsonResponse bytes to the response body and close the stream.
             * Ensure you use try-with-resources or explicitly close the stream. [4 Marks] */
            try(OutputStream os = exchange.getResponseBody();){
                os.write(jsonResponse.getBytes());
            }

        }
    }
}