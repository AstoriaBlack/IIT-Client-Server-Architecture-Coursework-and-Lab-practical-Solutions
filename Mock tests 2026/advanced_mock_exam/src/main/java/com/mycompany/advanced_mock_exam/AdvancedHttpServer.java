package com.mycompany.advanced_mock_exam;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

/**
 * ============================================================
 *  ADVANCED MOCK LAB EXAM — Part 2: Multi-Threaded HTTP Server
 *  Total Marks: 50
 * ============================================================
 *
 *  SCENARIO:
 *  You are building a REST-like HTTP API backend for a cloud
 *  data platform. The server must expose two endpoints and
 *  handle concurrent requests via a thread pool executor.
 *
 *  Endpoints:
 *    GET  /api/health  → returns plain text "OK" with status 200
 *    POST /api/data    → reads request body, returns JSON response
 *
 *  INSTRUCTIONS:
 *  - Fill in ALL sections marked with TODO.
 *  - Do NOT modify method signatures or class structure.
 *  - Read each comment carefully before writing code.
 * ============================================================
 */
public class AdvancedHttpServer {

    private static final int PORT = 8000;
    private static final int POOL_SIZE = 16;

    public static void main(String[] args) {

        /* TODO 1: Create a fixed thread pool of size POOL_SIZE using
         * Executors.newFixedThreadPool(). Assign it to an ExecutorService. [3 Marks] */
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);


        try {
            /* TODO 2: Create an HttpServer instance using the factory method
             * HttpServer.create(). Bind it to PORT with a backlog of 0. [3 Marks] */
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0 );


            /* TODO 3: Register the path "/api/health" on the server and associate
             * it with a new instance of HealthHandler (defined below). [2 Marks] */
            server.createContext("/api/health", new HealthHandler());


            /* TODO 4: Register the path "/api/data" on the server and associate
             * it with a new instance of DataHandler (defined below). [2 Marks] */
            server.createContext("/api/data", new DataHandler());


            /* TODO 5: Bind the ExecutorService to the server using setExecutor().
             * This enables concurrent request processing across threads. [3 Marks] */
            server.setExecutor(threadPool);


            /* TODO 6: Start the server and print a confirmation message to the
             * console showing the port number. [2 Marks] */
            server.start();
            System.out.println("Server is actively listening on port " + PORT);


        } catch (IOException e) {

            /* TODO 7: Print the stack trace of the IOException. [1 Mark] */
            e.printStackTrace();

        }
    }


    /* -------------------------------------------------------
     * Handler 1: HealthHandler  —  GET /api/health
     * Used by load balancers to verify the server is alive.
     * ------------------------------------------------------- */

    /* TODO 8: Declare HealthHandler as a static inner class implementing
     * the correct HTTP handler interface from com.sun.net.httpserver. [2 Marks] */
    static class HealthHandler implements HttpHandler {

        /* TODO 9: Override the single abstract method required by the interface.
         * It receives an HttpExchange and throws IOException. [2 Marks] */
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            /* TODO 10: Retrieve the HTTP request method from the exchange object. [2 Marks] */
            String method = exchange.getRequestMethod();

            /* TODO 11: Check if the method is NOT "GET" (case-insensitive).
             * If so, send a 405 status with -1 body length and return. [3 Marks] */
            if(!method.equalsIgnoreCase("GET")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }
            


            String response = "OK";

            /* TODO 12: Send response headers. Status 200, length = response.length(). [3 Marks] */
            exchange.sendResponseHeaders(200,response.length() );

            /* TODO 13: Get the response body OutputStream from the exchange,
             * write the response bytes to it, then close the stream. [3 Marks] */
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }


    /* -------------------------------------------------------
     * Handler 2: DataHandler  —  POST /api/data
     * Reads a JSON payload from the client and returns a result.
     * ------------------------------------------------------- */

    /* TODO 14: Declare DataHandler as a static inner class implementing
     * the same HTTP handler interface as HealthHandler. [2 Marks] */
    static class DataHandler implements HttpHandler {

        /* TODO 15: Override the handle method that takes an HttpExchange
         * and throws IOException. [2 Marks] */
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            /* TODO 16: Check if the request method is NOT "POST".
             * If so, send 405 with body length -1 and return immediately. [3 Marks] */
            if(!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }


            /* TODO 17: Retrieve the request headers using exchange.getRequestHeaders().
             * Read the "Content-Type" header and print it to the console. [3 Marks] */
            Headers header = exchange.getRequestHeaders();
            String contentType = header.getFirst("Content-Type");
            System.out.println("Content-Type: " + contentType);


            /* TODO 18: Get the request body as an InputStream from the exchange,
             * then read all its bytes into a byte array using readAllBytes(). [3 Marks] */
            InputStream is = exchange.getRequestBody();
            
            byte[] byteStream = is.readAllBytes();


            /* TODO 19: Set the "Content-Type" response header to "application/json"
             * using exchange.getResponseHeaders().set(). [2 Marks] */
            exchange.getResponseHeaders().set("Content-Type", "application/json");


            String jsonResponse = "{\"status\": \"received\", \"bytes\": " + byteStream.length + "}";

            /* TODO 20: Send response headers with status 200 and the correct
             * byte length of jsonResponse, then write and close the response
             * body using try-with-resources on the OutputStream. [4 Marks] */
             exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
             try(OutputStream os = exchange.getResponseBody()){
                 os.write(jsonResponse.getBytes());
             }

        }
    }
}