package com.mycompany.tutorial.week03.socket2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleChatClient {

    public static void main(String[] args) {
        try {
            // TODO 33: Connect to localhost on port 5000
            Socket socket = new Socket("localhost",5000);

            System.out.println("Connected to the server.");

            // TODO 34: Get InputStream
            InputStream inputStream = socket.getInputStream();

            // TODO 35: Get OutputStream
            OutputStream outputStream = socket.getOutputStream();

            // TODO 36: Initialize a Scanner to read from System.in
            Scanner scanner = new Scanner(System.in);

            // TODO 37: Create an infinite loop
            while (true) {
                System.out.print("You: ");

                // TODO 38: Read the next line from the scanner
                String message = scanner.nextLine();

                // TODO 39: Write the message bytes to the outputStream
                outputStream.write(message.getBytes());

                // TODO 40: Create a byte buffer [1024] for the response
                byte[] buffer = new byte[1024];

                // TODO 41: Read from inputStream into the buffer
                int bytesRead = inputStream.read(buffer);

                // TODO 42: Convert buffer to String
                String responseMessage = new String(buffer,0,bytesRead);

                System.out.println("Server: " + responseMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
