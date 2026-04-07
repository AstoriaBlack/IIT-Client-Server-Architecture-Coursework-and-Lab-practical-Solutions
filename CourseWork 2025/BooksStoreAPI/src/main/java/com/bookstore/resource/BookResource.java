//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.resource;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.model.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Year;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private static Map<Integer, Book> books = new HashMap<>();
    private static int nextId = 1;
    
    @GET
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }
    
    @POST
    public Response addBook(Book book) {
        validateBook(book);
        
        // Check if author exists by directly accessing the authors map in AuthorResource
        if (!AuthorResource.authorExists(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        // Check for duplicate ISBN to avoid theem
        if (isbnExists(book.getIsbn())) {
        throw new InvalidInputException("A book with ISBN " + book.getIsbn() + " already exists");
    }
       
        book.setId(nextId++);
        books.put(book.getId(), book);
        return Response.status(Response.Status.CREATED)
                      .entity(book)
                      .build();
    }
    private boolean isbnExists(String isbn) {
    for (Book existingBook : books.values()) {
        if (existingBook.getIsbn().equals(isbn)) {
            return true;
        }
    }
    return false;
}
    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        
        validateBook(updatedBook);
        
        // Check if author exists
        if (!AuthorResource.authorExists(updatedBook.getAuthorId())) {
            throw new AuthorNotFoundException(updatedBook.getAuthorId());
        }
        
        
        updatedBook.setId(id);
        books.put(id, updatedBook);
        return updatedBook;
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        books.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathParam("authorId") int authorId) {
        // Check if author exists
        if (!AuthorResource.authorExists(authorId)) {
            throw new AuthorNotFoundException(authorId);
        }
        
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthorId() == authorId) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
    
    public void updateBookStock(int bookId, int quantity) {
        Book book = getBookById(bookId);
        book.setStockQuantity(book.getStockQuantity() - quantity);
        books.put(bookId, book);
    }
    
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty");
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new InvalidInputException("Book ISBN cannot be empty");
        }
        
        Double price = book.getPrice();
        if (price == null) {
            throw new InvalidInputException("Book price cannot be null");
        }
        if (price <= 0) {
            throw new InvalidInputException("Book price must be greater than zero");
        }
        
        Integer stockQuantity = book.getStockQuantity();
        if (stockQuantity == null) {
            throw new InvalidInputException("Book stock quantity cannot be null");
        }
        if (stockQuantity < 0) {
            throw new InvalidInputException("Book stock quantity cannot be negative");
        }
        
        Integer publicationYear = book.getPublicationYear();
        if (publicationYear == null) {
            throw new InvalidInputException("Publication year cannot be null");
        }
        // Validate publication year is not in the future
        int currentYear = Year.now().getValue();
        if (publicationYear > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }
    }
}