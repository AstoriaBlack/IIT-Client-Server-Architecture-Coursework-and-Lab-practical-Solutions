//package com.mycompany.tutorial.week03.socket2;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class SimpleChatServer {
//
//    public static void main(String[] args) {
//        try {
//            // TODO 22: Create a ServerSocket on port 5000
//            ServerSocket serverSocket = new ServerSocket(5000);
//                    /* code here */
//
//            System.out.println("Server is running and waiting for a client to connect...");
//
//            // TODO 23: Accept a client connection
//            Socket clientSocket = serverSocket.accept();
//                    /* code here */
//
//            System.out.println("Client connected: " + clientSocket.getInetAddress());
//
//            // TODO 24: Get the raw InputStream from the client socket
//            InputStream inputStream = clientSocket.getInputStream();
//                    /* code here */
//
//            // TODO 25: Get the raw OutputStream from the client socket
//            OutputStream outputStream = clientSocket.getOutputStream();
//                    /* code here */
//
//            // TODO 26: Create a byte array buffer of size 1024
//            byte[] buffer = new byte[1024];
//                    /* code here */;
//
//            int bytesRead;
//
//            // TODO 27: Write a while loop: read from inputStream into buffer until != -1
//            while ((bytesRead = inputStream.read(buffer)) != -1){
//
//                // TODO 28: Convert the buffer (0 to bytesRead) into a String
//                String message = new String(buffer,0,bytesRead);
//
//                System.out.println("Client: " + message);
//
//                // TODO 29: Prepare response "Server received your message: " + message
//                String responseMessage = "Server received your message: " + message;
//
//                // TODO 30: Write the response bytes to the outputStream
//                outputStream.write(responseMessage.getBytes());
//            }
//
//            // TODO 31: Close the client socket
//            clientSocket.close();
//
//            // TODO 32: Close the server socket
//            serverSocket.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
package com.mycompany.tutorial.week03.socket2;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class SimpleChatServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server is running and waiting for clients...");

            // IMPORTANT CHANGE
            while (true) {

                Socket clientSocket = serverSocket.accept();

                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create handler
                ClientHandler handler = new ClientHandler(clientSocket);

                // Create thread
                Thread thread = new Thread(handler);

                // Start thread
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}