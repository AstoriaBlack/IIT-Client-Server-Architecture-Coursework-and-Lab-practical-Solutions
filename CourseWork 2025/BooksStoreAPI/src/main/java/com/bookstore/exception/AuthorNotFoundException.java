//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.exception;

public class AuthorNotFoundException extends RuntimeException {
    private final String title = "Author Not Found";
    private final String message;
    
    public AuthorNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
    public AuthorNotFoundException(int authorId) {
        super("Author with ID " + authorId + " does not exist.");
        this.message = "Author with ID " + authorId + " does not exist.";
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}