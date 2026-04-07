//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    private final String title = "Book Not Found";
    private final String message;
    
    public BookNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public BookNotFoundException(int bookId) {
        super("Book with ID " + bookId + " does not exist.");
        this.message = "Book with ID " + bookId + " does not exist.";
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}