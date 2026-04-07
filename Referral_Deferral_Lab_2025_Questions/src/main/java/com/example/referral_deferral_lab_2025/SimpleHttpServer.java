
/*
TOTAL MARKS INCLUDING SUCESSFUL RUNNING THE PROJECT AND CODE FORMATTING = 43
*/


package com.example.referral_deferral_lab_2025;

/*
----------------------------------------------
(3 marks)
Instruction: Your first task is to import all necessary classes.
To build your HTTP server, you will need specific functionalities:
- A class to represent an HTTP request and its corresponding response.
- An interface for objects that will process these requests.
- The core class for building the HTTP server itself.
- A class for handling potential errors during input/output operations.
- A mechanism for sending data, like your response, back to the client.
- A class to define the network address (IP and port) for your server.
- A utility to work with the requested Uniform Resource Identifiers (URIs).
Locate these classes, primarily within 'com.sun.net.httpserver', 'java.io', and 'java.net' packages.
-----------------------------------------------
*/





/*
----------------------------------------------
(1 mark)
Instruction: Please define the main public class for your HTTP server application.
This class will be the container for your server's logic. A suitable name would be 'SimpleHttpServer'.
-----------------------------------------------
*/




    /*
    ----------------------------------------------
    (2 mark)
    Instruction: Now, define the main method, the entry point for your application.
    Follow the standard signature for a main method in Java.
    Operations within this method, like starting a server, might cause I/O issues.
    Declare the method to reflect this possibility appropriately.
    -----------------------------------------------
    */
    



        /*
        ----------------------------------------------
        (3 mark)
        Instruction: Your next step is to instantiate the HTTP server.
        Specify the port number (e.g., 8080) and a backlog value (0 for default).
        To create an HttpServer instance, the `HttpServer` class provides a static method.
        Hint: `create`
        Assign the newly created server object to an HttpServer instance varibale.
        -----------------------------------------------
        */
        




        /*
        ----------------------------------------------
        (2 mark)
        Instruction: Configure the server to handle requests for a specific URL path.
        Requests to the "/hello" path need to be associated with a request handler instance (e.g., 'MyHandler', to be defined later).
        Your HttpServer object has a method to define such a mapping, often called a "context".
        Hint: `createContext`
        -----------------------------------------------
        */
        



        /*
        ----------------------------------------------
        (2 mark)
        Instruction: Determine how the server will manage threads for handling requests.
        For this exercise, use the server's default threading behavior.
        To set the 'Executor' on the server, you will call a specific method on your server instance.
        Pass 'null' to use the default.
        -----------------------------------------------
        */
        



        /*
        ----------------------------------------------
        (1 mark)
        Instruction: It's time to bring your server to life by starting it.
        Call the method on your HttpServer object that makes it start listening for incoming connections.
        -----------------------------------------------
        */
        



        /*
        ----------------------------------------------
        (1 mark)
        Instruction: Provide feedback to the user by printing a message to the console.
        This message should confirm that the server has started and indicate the port number and a sample URL
        (e.g., http://localhost:8080/hello) for access.
        Use 'System.out.println()' for this.
        -----------------------------------------------
        */
        



    /*
    ----------------------------------------------
    (2 mark)
    Instruction: Now, you need to define the logic that handles incoming HTTP requests.
    Create a static inner class for this purpose, for example, 'MyHandler'.
    This class must implement a specific interface from the 'com.sun.net.httpserver' package
    that dictates the structure for request handlers.
    -----------------------------------------------
    */
    


        /*
        ----------------------------------------------
        (3 mark)
        Instruction: Within your 'MyHandler' class, you must implement the primary method
        defined by the 'HttpHandler' interface. This method is invoked for each incoming request.
        The method parameter will encapsulate all request and response details.
        Remember to declare that this method might throw an 'IOException'.
        Hint for the method name: `handle`
        -----------------------------------------------
        */
        



            /*
            ----------------------------------------------
            (3 mark)
            Instruction: Inside the 'handle' method, your first step is to obtain the requested URI.
            The 'HttpExchange' object (your method parameter) provides a way to access this.
            Hint for the method on `HttpExchange`: `getRequestURI`
            Store the result.
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (2 mark)
            Instruction: From the URI you just obtained, extract the specific path component.
            For instance, if the URI is "http://localhost:8080/hello/world", you need to get "/hello/world".
            The 'URI' object has a method for this.
            Hint for the method on `URI`: `getPath`
            Store the path in a String.
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (2 mark)
            Instruction: Prepare the actual content (the body) of the response you want to send back.
            This will be a simple string for this exercise. Feel free to include the path you extracted in your response message.
            Create a String variable to hold this response.
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (3 mark)
            Instruction: Before sending the response body, you must send the HTTP response headers.
            This includes setting the HTTP status code (200 for success) and the length of the response body.
            The 'HttpExchange' object has a method for this. You'll need to calculate the length of your response string in bytes.
            To convert a String to bytes, use its `getBytes()` method.
            Hint for the method on `HttpExchange` to send headers: `sendResponseHeaders`
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (2 mark)
            Instruction: Now, get an output stream from the 'HttpExchange' object.
            This stream is where you will write the actual response body data to send it to the client.
            Hint for the method on `HttpExchange`: `getResponseBody`
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (2 mark)
            Instruction: Write your prepared response string to the output stream.
            Remember that output streams typically work with bytes. Convert your response string to a byte array before writing.
            The 'OutputStream' object has a method for writing byte arrays.
            Hint for the method on `OutputStream`: `write`
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (1 mark)
            Instruction: Finally, it's crucial to close the output stream.
            This ensures all data is flushed to the client and system resources are released.
            The standard method call to close a stream is needed here.
            Hint for the method on `OutputStream`: `close`
            -----------------------------------------------
            */
            



            /*
            ----------------------------------------------
            (2 mark)
            Instruction: For logging purposes, print a message to the server's console
            indicating that a response has been sent and for which path.
            This helps in monitoring server activity. Use 'System.out.println()'.
            -----------------------------------------------
            */
            


