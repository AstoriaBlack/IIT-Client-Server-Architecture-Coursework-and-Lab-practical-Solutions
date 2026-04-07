//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class OutOfStockException extends RuntimeException {
    private final String title = "Out Of Stock";
    private final String message;
    
    public OutOfStockException(String message) {
        super(message);
        this.message = message;
    }
    
    public OutOfStockException(int bookId, int requestedQuantity, int availableQuantity) {
        super("Book with ID " + bookId + " has only " + availableQuantity + 
              " items in stock, but " + requestedQuantity + " were requested");
        this.message = "Book with ID " + bookId + " has only " + availableQuantity + 
              " items in stock, but " + requestedQuantity + " were requested";
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}