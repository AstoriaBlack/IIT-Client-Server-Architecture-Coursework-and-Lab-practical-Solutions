//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.resource;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Author;
import com.bookstore.model.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private static final Map<Integer, Author> authors = new HashMap<>();
    private static int nextId = 1;
    
    public static boolean authorExists(int authorId) {
        return authors.containsKey(authorId);
    }
    
    @GET
    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>(authors.values());
        BookResource bookResource = new BookResource();
        
        // Populate books for each author
        for (Author author : authorList) {
            List<Book> authorBooks = bookResource.getBooksByAuthor(author.getId());
            List<String> bookTitles = new ArrayList<>();
            for (Book book : authorBooks) {
                bookTitles.add(book.getTitle());
            }
            author.setBooks(bookTitles);
        }
        
        return authorList;
    }
    
    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") int id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
        
        // Populate books for the author
        BookResource bookResource = new BookResource();
        List<Book> authorBooks = bookResource.getBooksByAuthor(id);
        List<String> bookTitles = new ArrayList<>();
        for (Book book : authorBooks) {
            bookTitles.add(book.getTitle());
        }
        author.setBooks(bookTitles);
        
        return author;
    }
    
    @POST
    public Response addAuthor(Author author) {
        validateAuthor(author);
        
        author.setId(nextId++);
        authors.put(author.getId(), author);
        return Response.status(Response.Status.CREATED)
                      .entity(author)
                      .build();
    }
    
    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        
        validateAuthor(updatedAuthor);
        
        updatedAuthor.setId(id);
        authors.put(id, updatedAuthor);
        
        // Populate books for the updated author
        BookResource bookResource = new BookResource();
        List<Book> authorBooks = bookResource.getBooksByAuthor(id);
        List<String> bookTitles = new ArrayList<>();
        for (Book book : authorBooks) {
            bookTitles.add(book.getTitle());
        }
        updatedAuthor.setBooks(bookTitles);
        
        return updatedAuthor;
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        authors.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("/{id}/books")
    public List<Book> getAuthorBooks(@PathParam("id") int id) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        
        // Get books from BookResource
        BookResource bookResource = new BookResource();
        return bookResource.getBooksByAuthor(id);
    }
    
    private void validateAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty");
        }
    }
}