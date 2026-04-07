//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class CartNotFoundException extends RuntimeException {
    private final String title = "Cart Not Found";
    private final String message;
    
    public CartNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public CartNotFoundException(int customerId) {
        super("Cart for customer with ID " + customerId + " does not exist.");
        this.message = "Cart for customer with ID " + customerId + " does not exist.";
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}