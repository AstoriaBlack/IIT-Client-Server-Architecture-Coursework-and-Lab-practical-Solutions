//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class InvalidInputException extends RuntimeException {
    private final String title = "Invalid Input";
    private final String message;
    
    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}