package com.mycompany.mymocktestsocket;

/**
 * ============================================================
 *  SOCKET PROGRAMMING MOCK EXAM  —  Total: 50 Marks
 * ============================================================
 *
 * Scenario:
 *   You are building a multi-threaded Calculator Server.
 *   The server listens on a fixed port and accepts multiple
 *   simultaneous clients.  For each message received, the
 *   server parses a simple arithmetic expression of the form:
 *
 *       <number> <op> <number>      e.g.  "12 + 7"
 *
 *   and replies with "Result: <answer>", or
 *   "Error: invalid expression" if the input cannot be parsed.
 *
 *   The server must stay alive and keep serving clients until
 *   it is forcibly terminated.
 *
 * Compilation:  javac SocketProgrammingMock.java
 * Run server :  java com.mycompany.socket_mock_combined.SocketProgrammingMock
 * Quick test :  telnet localhost 7777   (then type  5 * 3  and press Enter)
 * ============================================================
 */

/* ── TODO 1 (1 mark) ──────────────────────────────────────────
 * Import java.io.BufferedReader
 */
// YOUR IMPORT HERE
import java.io.BufferedReader;

/* ── TODO 2 (1 mark) ──────────────────────────────────────────
 * Import java.io.InputStreamReader
 */
// YOUR IMPORT HERE
import java.io.InputStreamReader;
        
/* ── TODO 3 (1 mark) ──────────────────────────────────────────
 * Import java.io.PrintWriter
 */
// YOUR IMPORT HERE
import java.io.PrintWriter;

/* ── TODO 4 (1 mark) ──────────────────────────────────────────
 * Import java.net.ServerSocket
 */
// YOUR IMPORT HERE
import java.net.ServerSocket;
/* ── TODO 5 (1 mark) ──────────────────────────────────────────
 * Import java.net.Socket
 */
// YOUR IMPORT HERE
import java.net.Socket;

/* ── TODO 6 (1 mark) ──────────────────────────────────────────
 * Import java.io.IOException
 */
// YOUR IMPORT HERE
import java.io.IOException;


        
public class MyMockTestSocket {

    /* ── TODO 7 (1 mark) ──────────────────────────────────────
     * Define a constant for the port number the server will
     * listen on.  Use port 7777.
     */
    // YOUR CODE HERE  e.g.  private static final int PORT = ???;
    final static int PORT = 7777;

    public static void main(String[] args) {

        /* ── TODO 8 (2 marks) ─────────────────────────────────
         * Open a ServerSocket bound to PORT inside a
         * try-with-resources block so it is automatically
         * closed if the JVM exits.
         * Print "Calculator Server started on port <PORT>"
         * once the socket is open.
         */
        // YOUR CODE HERE
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            /* ── TODO 9 (2 marks) ─────────────────────────────
            
             * Start an infinite loop so the server keeps
             * accepting new clients without stopping.
             */
            // YOUR CODE HERE (while loop header only)
            System.out.println("Server started. Waiting for connections...");
            while(true) {

                /* ── TODO 10 (2 marks) ────────────────────────
                 * Block and wait for the next client to connect.
                 * Store the result in a Socket variable named
                 * clientSocket.
                 */
                // YOUR CODE HERE
                Socket clientSocket = serverSocket.accept();

                /* ── TODO 11 (1 mark) ─────────────────────────
                 * Print a message to the console that shows the
                 * IP address of the newly connected client.
                 * Hint: clientSocket.getInetAddress()
                 */
                // YOUR CODE HERE
                System.out.println("Client connected via: " + clientSocket.getInetAddress());

                /* ── TODO 12 (2 marks) ────────────────────────
                 * Create a new ClientHandler instance, passing
                 * clientSocket to its constructor.
                 */
                // YOUR CODE HERE
                ClientHandler handler = new ClientHandler(clientSocket);

                /* ── TODO 13 (2 marks) ────────────────────────
                 * Wrap the ClientHandler in a new Thread and
                 * start it immediately so this client is served
                 * concurrently with any future clients.
                 */
                Thread thread = new Thread(handler);
                thread.start();
            }
                // YOUR CODE HERE

        /* ── TODO 14 (1 mark) ─────────────────────────────────
         * Add a catch block for IOException that prints the
         * stack trace.
         */
        // YOUR CODE HERE
           }catch(IOException e) {
                    e.printStackTrace();
                    }
    }


    // =========================================================
    //  Inner class — handles one client on its own thread
    // =========================================================

    /* ── TODO 15 (2 marks) ────────────────────────────────────
     * Declare ClientHandler as a private static class that
     * implements the Runnable interface.
     */private static class ClientHandler implements Runnable {

        /* ── TODO 16 (1 mark) ─────────────────────────────────
         * Declare a private final Socket field named
         * clientSocket to store the connection passed in via
         * the constructor.
         */
        // YOUR FIELD HERE
         private final Socket clientSocket;

        /* ── TODO 17 (2 marks) ────────────────────────────────
         * Write a constructor that accepts a Socket parameter
         * and assigns it to the field declared above.
         */
        // YOUR CONSTRUCTOR HERE
         public ClientHandler(Socket clientSocket) {
             this.clientSocket = clientSocket;
         }

        /* ── TODO 18 (1 mark) ─────────────────────────────────
         * Override the run() method as required by Runnable.
         */
         @Override
         public void run() {

            /* ── TODO 19 (2 marks) ────────────────────────────
             * Declare BufferedReader and PrintWriter variables,
             * both initialised to null, so they are in scope
             * for the finally block.
             */
            // YOUR CODE HERE
            BufferedReader in = null;
            PrintWriter out = null;

             try {

                /* ── TODO 20 (3 marks) ────────────────────────
                 * Build a BufferedReader that reads text from
                 * clientSocket's input stream.
                 * Chain:  InputStream → InputStreamReader →
                 *         BufferedReader
                 */
                // YOUR CODE HERE
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                /* ── TODO 21 (3 marks) ────────────────────────
                 * Build a PrintWriter that writes to
                 * clientSocket's output stream.
                 * IMPORTANT: enable auto-flush so responses
                 * are sent immediately without an explicit
                 * flush() call.
                 */
                // YOUR CODE HERE
                out = new PrintWriter(clientSocket.getOutputStream(),true);

                /* ── TODO 22 (2 marks) ────────────────────────
                 * Declare a String variable named inputLine.
                 * Write a while-loop that reads one line at a
                 * time from the client using in.readLine() and
                 * continues as long as the result is not null.
                 */
                // YOUR CODE HERE
                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    
               
                    /* ── TODO 23 (1 mark) ─────────────────────
                     * Inside the loop, log the raw message to
                     * the server console so you can see what
                     * arrived (include the client address).
                     */
                    // YOUR CODE HERE
                    System.out.println("Client Recieved from: " + clientSocket.getInetAddress());

                    /* ── TODO 24 (4 marks) ────────────────────
                     * Call the helper method calculate(inputLine)
                     * (defined below) and store its return value
                     * in a String called response.
                     * Send response back to the client via the
                     * PrintWriter.
                     */
                    // YOUR CODE HERE
                    String response = calculate(inputLine);
                    out.println(response);
                } 

             } catch (IOException e) {
                /* ── TODO 25 (1 mark) ─────────────────────────
                 * In the catch block, print a short message to
                 * System.err indicating the client disconnected
                 * or an I/O error occurred.
                 */
                // YOUR CODE HERE
                System.err.println("Client disconnected or I/O error" + e.getMessage());

             } finally {
                 try {
                    /* ── TODO 26 (2 marks) ────────────────────
                     * In the finally block, close clientSocket
                     * only if it is not null and not already
                     * closed.
                     */
                    // YOUR CODE HERE
                    if(clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                    }

                    /* ── TODO 27 (1 mark) ─────────────────────
                     * Close the BufferedReader if it is not null.
                     */
                    // YOUR CODE HERE
                    if(in!= null) in.close();
                    

                    /* ── TODO 28 (1 mark) ─────────────────────
                     * Close the PrintWriter if it is not null.
                     */
                    // YOUR CODE HERE
                    if(out!= null) out.close();

                 } catch (IOException e) { e.printStackTrace(); }
             }
         }
     }   


    // =========================================================
    //  Helper — parses and evaluates a simple expression
    // =========================================================

    /**
     * TODO 29 (3 marks)
     *
     * Complete the method below so that it:
     *   1. Splits inputLine on whitespace into exactly 3 tokens
     *      (operand, operator, operand).  If the split does not
     *      produce exactly 3 tokens, return "Error: invalid expression".
     *   2. Parses tokens[0] and tokens[2] as doubles.
     *      Wrap this in a try-catch for NumberFormatException;
     *      on failure return "Error: invalid expression".
     *   3. Performs the operation indicated by tokens[1]:
     *        "+"  →  addition
     *        "-"  →  subtraction
     *        "*"  →  multiplication
     *        "/"  →  division (if divisor is 0, return "Error: division by zero")
     *      If the operator is unrecognised, return "Error: unknown operator".
     *   4. Returns "Result: " followed by the numeric result.
     *      Hint: if the result is a whole number, you may cast to int for
     *      clean output, e.g.  (result % 1 == 0) ? (int) result : result
     *
     * Examples:
     *   "10 + 5"   →  "Result: 15"
     *   "9 / 2"    →  "Result: 4.5"
     *   "7 / 0"    →  "Error: division by zero"
     *   "abc + 1"  →  "Error: invalid expression"
     */
    private static String calculate(String inputLine) {
       if(inputLine == null || inputLine.trim().isEmpty()) {
           return "Error: Invalid expression ";
       }
       String[] tokens = inputLine.trim().split("\\s+");
       //space line, + is for greedier thing more space
       if(tokens.length != 3) {
           return "Error: Invalid expression ";
       }
       double a,b;
       try{
           a = Double.parseDouble(tokens[0]);
           b = Double.parseDouble(tokens[2]);
       }catch(NumberFormatException e) {
            return "Error: Invalid expression ";
       }
       String op = tokens[1];
       double result;
       
       switch (op) {
           case "+": result = a + b; break;
           case "-": result = a - b; break;
           case "*": result = a * b; break;
           case "/": 
               if(b == 0) return "Error: Division by zero ";
               result = a / b; break;
           default: 
               return "Error: Invalid expression ";               
       }
        // Print cleanly — no ".0" for whole numbers
        if(result%1 == 0) {
            return "Result: " + (int)result;
        }else {
             return "Result: " + result;
        }
    }

    // }  ← end ClientHandler
}

/*
 * ============================================================
 *  MARKING SCHEME SUMMARY
 * ============================================================
 *  TODO  1 – 6   Imports                                6 marks
 *  TODO  7       Port constant                          1 mark
 *  TODO  8       ServerSocket + startup message         2 marks
 *  TODO  9       Infinite accept loop                   2 marks
 *  TODO  10      accept() call                          2 marks
 *  TODO  11      Client connected log                   1 mark
 *  TODO  12      ClientHandler instantiation            2 marks
 *  TODO  13      Thread creation + start                2 marks
 *  TODO  14      IOException catch on main              1 mark
 *  TODO  15      ClientHandler class declaration        2 marks
 *  TODO  16      clientSocket field                     1 mark
 *  TODO  17      Constructor                            2 marks
 *  TODO  18      run() override                         1 mark
 *  TODO  19      Reader/Writer null declarations        2 marks
 *  TODO  20      BufferedReader chain                   3 marks
 *  TODO  21      PrintWriter + auto-flush               3 marks
 *  TODO  22      readLine() while loop                  2 marks
 *  TODO  23      Server-side logging                    1 mark
 *  TODO  24      calculate() call + send response       4 marks
 *  TODO  25      IOException catch in run()             1 mark
 *  TODO  26      clientSocket close in finally          2 marks
 *  TODO  27      BufferedReader close                   1 mark
 *  TODO  28      PrintWriter close                      1 mark
 *  TODO  29      calculate() implementation             3 marks
 *  ────────────────────────────────────────────────────────────
 *  TOTAL                                               50 marks
 * ============================================================
 */