//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class CustomerNotFoundException extends RuntimeException {
    private final String title = "Customer Not Found";
    private final String message;
    
    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public CustomerNotFoundException(int customerId) {
        super("Customer with ID " + customerId + " does not exist.");
        this.message = "Customer with ID " + customerId + " does not exist.";
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}