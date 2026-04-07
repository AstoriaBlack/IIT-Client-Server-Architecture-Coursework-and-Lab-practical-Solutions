//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.resource;

import com.bookstore.exception.CartNotFoundException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private static Map<Integer, Cart> carts = new HashMap<>();
    
    @GET
    public Cart getCart(@PathParam("customerId") int customerId) {
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        // Get or create cart
        Cart cart = carts.get(customerId);
        if (cart == null) {
            cart = new Cart(customerId);
            carts.put(customerId, cart);
        }
        
        return cart;
    }
    
    @POST
    @Path("/items")
    public Cart addItemToCart(
            @PathParam("customerId") int customerId,
            CartItem item) {
        
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        // Verify quantity
        if (item.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        // Verify book exists and has enough stock
        BookResource bookResource = new BookResource();
        Book book = bookResource.getBookById(item.getBookId());
        
        if (book.getStockQuantity() < item.getQuantity()) {
            throw new OutOfStockException(item.getBookId(), item.getQuantity(), book.getStockQuantity());
        }
        
        // Get or create cart
        Cart cart = getCart(customerId);
        
        // Set book details
        item.setPrice(book.getPrice());
        item.setBookTitle(book.getTitle());
        
        // Add item to cart
        cart.addItem(item);
        
        return cart;
    }
    
    @PUT
    @Path("/items/{bookId}")
    public Cart updateCartItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId,
            CartItem item) {
        
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        // Validate quantity
        if (item.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        // Verify book exists and has enough stock
        BookResource bookResource = new BookResource();
        Book book = bookResource.getBookById(bookId);
        
        if (book.getStockQuantity() < item.getQuantity()) {
            throw new OutOfStockException(bookId, item.getQuantity(), book.getStockQuantity());
        }
        
        // Get cart
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException(customerId);
        }
        
        // Update item quantity
        cart.updateItemQuantity(bookId, item.getQuantity());
        
        return cart;
    }
    
    @DELETE
    @Path("/items/{bookId}")
    public Cart removeItemFromCart(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId) {
        
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        // Verify book exists
        BookResource bookResource = new BookResource();
        bookResource.getBookById(bookId);
        
        // Get cart
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException(customerId);
        }
        
        // Remove item
        cart.removeItem(bookId);
        
        return cart;
    }
    
    public void clearCartInternal(int customerId) {
        Cart cart = carts.get(customerId);
        if (cart != null) {
            cart.clear();
        }
    }
}