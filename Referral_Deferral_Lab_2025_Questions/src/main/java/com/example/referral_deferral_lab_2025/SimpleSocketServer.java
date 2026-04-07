/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/*
TOTAL MARKS INCLUDING SUCESSFUL RUNNING THE PROJECT AND CODE FORMATTING = 54
*/

package com.example.referral_deferral_lab_2025;

/*
----------------------------------------------
(2 marks)
Instruction: Begin by importing the necessary Java classes for this socket server.
You will need classes for reading character streams, handling input/output exceptions,
creating character-output streams, managing server-side sockets, client-side sockets,
and for logging messages.
Locate these within the 'java.io' and 'java.net' packages, and 'java.util.logging' for the logger.
-----------------------------------------------
*/





/*
----------------------------------------------
(1 mark)
Instruction: Define the main public class for your socket server.
Let's name this class 'SimpleSocketServer'. It will contain the server's core logic
and a handler for individual client connections.
-----------------------------------------------
*/




    /*
    ----------------------------------------------
    (1 mark)
    Instruction: Initialize a static final Logger instance for this class.
    This will be used for logging server events and messages.
    Use `Logger.getLogger()` and provide the class name.
    -----------------------------------------------
    */




    /*
    ----------------------------------------------
    (1 mark)
    Instruction: Define a static final integer for the default port number.
    This constant will be used if no other port is specified. A common choice is 8080.
    -----------------------------------------------
    */
    



    /*
    ----------------------------------------------
    (1 mark)
    Instruction: Implement the main method, the entry point of your server application.
    This method will set up the server socket and enter a loop to accept client connections.
    -----------------------------------------------
    */
    


        

        /*
        ----------------------------------------------
        (2 marks)
        Instruction: Set up the ServerSocket within a try-with-resources statement.
        This ensures the ServerSocket is automatically closed when the block is exited.
        Instantiate 'ServerSocket' with the chosen 'port'.
        -----------------------------------------------
        */
        



            /*
            ----------------------------------------------
            (1 mark)
            Instruction: Log a message indicating the server has started and is listening.
            Use your LOGGER instance with an appropriate Level (e.g., INFO) and include the port number.
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (1 mark)
            Instruction: Log another message indicating what the server is waiting for from clients.
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (1 mark)
            Instruction: Implement an infinite loop to continuously listen for new client connections.
            
            -----------------------------------------------
            */
           



                /*
                ----------------------------------------------
                (1 mark)
                Instruction: Inside the loop, prepare to accept a client connection.
                This operation can throw an IOException, so use a try-catch block.
                -----------------------------------------------
                */
               



                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: Wait for a client to connect to the server.
                    The 'ServerSocket' instance has a method for this blocking operation.
                    Hint: `accept`
                    Assign the returned client socket to a Socket instance variable.
                    -----------------------------------------------
                    */
                    


                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: Log that a client has connected.
                    Include the client's IP address (obtainable from the 'clientSocket').
                    Use logging level in info
                    -----------------------------------------------
                    */
                    



                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: Create an instance of your client handler class (ClientHandler) .
                    Pass the 'clientSocket' to its constructor.
                    -----------------------------------------------
                    */
                    



                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: To handle multiple clients concurrently, create a new Thread for the client handler.
                    Pass your 'clientHandler' instance (which must be a Runnable) to the Thread constructor.
                    Then, initiate the execution of this new thread.
                    Hint for starting the thread: `start`
                    -----------------------------------------------
                    */
                    



                /*
                ----------------------------------------------
                (1 mark)
                Instruction: Add a catch block to handle IOExceptions that might occur
                when trying to accept a client connection on the server socket.
                Print an error message to 'System.err' including the exception message.
                -----------------------------------------------
                */
                




        /*
        ----------------------------------------------
        (2 mark)
        Instruction: Add a catch block for IOExceptions that might occur if the server
        cannot be started on the specified port (e.g., port already in use).
        Print an informative error message to 'System.err'.
        Terminate the application if the server cannot start.
        Hint for termination: `System.exit` with a non-zero status (1).
        -----------------------------------------------
        */
        





    /*
    ----------------------------------------------
    (1 mark)
    Instruction: Define a private static inner class named 'ClientHandler'.
    This class will be responsible for handling communication with a single connected client.
    It must implement the 'Runnable' interface to be executed in its own thread.
    -----------------------------------------------
    */
    



        /*
        ----------------------------------------------
        (1 mark)
        Instruction: Declare a private final field to hold the client's Socket instance.
        -----------------------------------------------
        */
       




        /*
        ----------------------------------------------
        (2 marks)
        Instruction: Implement a constructor for 'ClientHandler'.
        It should accept a 'Socket' object (the client's socket) as a parameter
        and assign it to the 'clientSocket' field.
        -----------------------------------------------
        */
        




        /*
        ----------------------------------------------
        (1 mark)
        Instruction: Override the 'run' method from the 'Runnable' interface.
        This method contains the logic for interacting with the client.
        -----------------------------------------------
        */
        




            /*
            ----------------------------------------------
            (4 marks)
            Instruction: Set up input and output streams for communication with the client
            within a try-with-resources statement. This ensures they are closed automatically.
            - Create a 'BufferedReader' to read text from the client's input stream.
              You'll need to wrap 'clientSocket.getInputStream()' with an 'InputStreamReader'.
            - Create a 'PrintWriter' to send text to the client's output stream.
              Wrap 'clientSocket.getOutputStream()'. Enable auto-flushing.
            -----------------------------------------------
            */
            





                /*
                ----------------------------------------------
                (2 marks)
                Instruction: Read a line of text sent by the client.
                The 'BufferedReader' instance has a method for this.
                Hint: `readLine`
                Store this in a String variable.
                -----------------------------------------------
                */
                




                /*
                ----------------------------------------------
                (2 marks)
                Instruction: Check if the input received from the client is not null and not empty.
                -----------------------------------------------
                */
                




                    /*
                    ----------------------------------------------
                    (1 marks)
                    Instruction: Print the received client message to the server's console (System.out).
                    
                    -----------------------------------------------
                    */
                    

                    


                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: Process the client's input.
                    If the client sent "hello server" and it equals to clientInputLine,
                    prepare "Hello Client" as the reply and store it inside a varibale
                    Otherwise, prepare a different reply indicating what was received and what is expected.
                    Hint for case-insensitive comparison: `equalsIgnoreCase`
                    
                    -----------------------------------------------
                    */
                    




                    /*
                    ----------------------------------------------
                    (2 marks)
                    Instruction: Handle the case where an empty or null input line is received.
                    Print a warning message to the console and then define an appropriate replyString variable 
                    and store a message like "I didn't receive a clear messageinclude a message like"
                    -----------------------------------------------
                    */                    




                /*
                ----------------------------------------------
                (2 marks)
                Instruction: Send the prepared 'replyString' back to the client.
                The 'PrintWriter' instance has a method suitable for sending a line of text.
                Hint: `println`
                -----------------------------------------------
                */
                



                /*
                ----------------------------------------------
                (1 mark)
                Instruction: Print a message to the server's console confirming the reply sent.
             
                -----------------------------------------------
                */
                




            /*
            ----------------------------------------------
            (1 marks)
            Instruction: Add a catch block to handle IOExceptions that might occur
            during communication with the client (e.g., client disconnects abruptly).
            Print an error message to 'System.err' and the exception message.
            -----------------------------------------------
            */
           



            /*
            ----------------------------------------------
            (1 mark)
            Instruction: Implement a 'finally' block to ensure resources are cleaned up,
            specifically the client socket.
            -----------------------------------------------
            */
            



                /*
                ----------------------------------------------
                (2 marks)
                Instruction: Inside the 'finally' block, attempt to close the 'clientSocket'.
                This should be in its own try-catch block as closing a socket can also throw an IOException.
                Before closing, check if 'clientSocket' is not null and is not already closed.
                Hint for checking if clientSocket closed, just invoke clientSocket.isClosed()
                Hint for closing: `close()`
                -----------------------------------------------
                */
                




                /*
                ----------------------------------------------
                (1 marks)
                Instruction: Catch any IOException that occurs during the socket closing attempt.
                Print an error message to 'System.err'.
                -----------------------------------------------
                */
                

