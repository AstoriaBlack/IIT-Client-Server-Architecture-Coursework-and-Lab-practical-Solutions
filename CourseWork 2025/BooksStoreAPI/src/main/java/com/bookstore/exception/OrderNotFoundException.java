//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class OrderNotFoundException extends RuntimeException {
    private final String title = "Order Not Found";
    private final String message;
    
    public OrderNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public OrderNotFoundException(int orderId, int customerId) {
        super("Order with ID " + orderId + " not found for customer with ID " + customerId);
        this.message = "Order with ID " + orderId + " not found for customer with ID " + customerId;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
} 