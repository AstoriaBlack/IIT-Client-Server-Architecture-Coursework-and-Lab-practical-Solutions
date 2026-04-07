/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial_week04;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lab Solution: Week 03 - Java HttpServer
 * Covers: Setup, Routing, Query Parsing, POST Bodies, and Thread Safety.
 */
public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // 1. Create Server - Binding to port 8000
        // Reference: Lecture Slide 22
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // 2. Routing - Context Creation
        // Reference: Lecture Slide 24
        server.createContext("/", new RootHandler());
        server.createContext("/greet", new GreetHandler());
        server.createContext("/echo", new EchoHandler());
        server.createContext("/stats", new StatsHandler());

        // 3. Scaling - Multithreading
        // Reference: Lecture Slide 34
        // Creating a pool that creates new threads as needed, but reuses previously constructed threads.
        server.setExecutor(Executors.newCachedThreadPool());

        // 4. Lifecycle - Start
        System.out.println("Server started on port " + port);
        server.start();
    }

    /**
     * Handler for the root path "/".
     * Returns a simple status message.
     */
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Server is online and running.";
            
            // CRITICAL ORDERING: Headers MUST be sent before writing the body.
            // Reference: Lecture Slide 31
            exchange.sendResponseHeaders(200, response.length());
            
            // Try-with-resources ensures the stream is closed automatically.
            // Reference: Lecture Slide 38
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    /**
     * Handler for "/greet".
     * Demonstrates manual parsing of Query Parameters.
     */
    static class GreetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Reference: Lecture Slide 36
            String query = exchange.getRequestURI().getQuery();
            String name = "Stranger";

            if (query != null) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] entry = pair.split("=");
                    if (entry.length > 1 && entry[0].equals("name")) {
                        name = entry[1];
                    }
                }
            }

            String response = "Hello, " + name + "!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    /**
     * Handler for "/echo".
     * Demonstrates handling POST requests and reading the Input Body.
     */
    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Check Method - Reference: Lecture Slide 28
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                
                // Read the Input Stream - Reference: Lecture Slide 30
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                
                String response = "Received: " + body;
                
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                // Method Not Allowed
                String response = "Only POST method is allowed.";
                exchange.sendResponseHeaders(405, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        }
    }

    /**
     * Handler for "/stats".
     * Demonstrates Thread Safety using AtomicInteger.
     */
    static class StatsHandler implements HttpHandler {
        // SHARED MUTABLE STATE
        // Using AtomicInteger prevents Race Conditions in a multi-threaded environment.
        // Reference: Lecture Slide 35
        private final AtomicInteger requestCount = new AtomicInteger(0);

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            int currentCount = requestCount.incrementAndGet();
            
            String response = "Total requests to /stats: " + currentCount;
            
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}