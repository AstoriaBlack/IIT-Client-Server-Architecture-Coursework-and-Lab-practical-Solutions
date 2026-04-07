//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.resource;

import com.bookstore.exception.InvalidInputException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.exception.OrderNotFoundException;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.model.Order;
import com.bookstore.model.OrderItem;
import com.bookstore.model.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private static Map<Integer, List<Order>> customerOrders = new HashMap<>();
    private static int nextOrderId = 1;
    
@POST
public Response createOrder(@PathParam("customerId") int customerId) {
    // Verify customer exists
    CustomerResource customerResource = new CustomerResource();
    customerResource.getCustomerById(customerId);
    
    // Get customer's cart
    CartResource cartResource = new CartResource();
    Cart cart = cartResource.getCart(customerId);
    
    if (cart.getItems().isEmpty()) {
        throw new InvalidInputException("Cannot create order with empty cart");
    }
    
    // Create new order
    Order order = new Order(nextOrderId++, customerId);
    
    // Convert cart items to order items
    BookResource bookResource = new BookResource();
    double orderTotal = 0.0;
    
    for (CartItem cartItem : cart.getItems()) {
        // Verify book still exists and has enough stock
        Book book = bookResource.getBookById(cartItem.getBookId());
        
        if (book.getStockQuantity() < cartItem.getQuantity()) {
            throw new OutOfStockException(book.getId(), cartItem.getQuantity(), book.getStockQuantity());
        }
        
        // Create order item with current book price (not cart price in case it changed)
        double currentPrice = book.getPrice();
        OrderItem orderItem = new OrderItem(
            cartItem.getBookId(),
            cartItem.getBookTitle(),
            cartItem.getQuantity(),
            currentPrice
        );
        
        // Calculate item subtotal and add to order total
        double itemSubtotal = currentPrice * cartItem.getQuantity();
        orderTotal += itemSubtotal;
        
        order.addItem(orderItem);
        
        // Update book stock
        bookResource.updateBookStock(cartItem.getBookId(), cartItem.getQuantity());
    }
    
    // Set order total
    order.setTotal(orderTotal);
    
    // Add order to customer's orders
    if (!customerOrders.containsKey(customerId)) {
        customerOrders.put(customerId, new ArrayList<>());
    }
    customerOrders.get(customerId).add(order);
    
    // Clear the cart
    cartResource.clearCartInternal(customerId);
    
    return Response.status(Response.Status.CREATED)
                  .entity(order)
                  .build();
}
    
    @GET
    public List<Order> getCustomerOrders(@PathParam("customerId") int customerId) {
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        List<Order> orders = customerOrders.get(customerId);
        if (orders == null) {
            return new ArrayList<>();
        }
        
        return orders;
    }
    
    @GET
    @Path("/{orderId}")
    public Order getOrderById(
            @PathParam("customerId") int customerId,
            @PathParam("orderId") int orderId) {
        
        // Verify customer exists
        CustomerResource customerResource = new CustomerResource();
        customerResource.getCustomerById(customerId);
        
        List<Order> orders = customerOrders.get(customerId);
        if (orders == null) {
            throw new OrderNotFoundException("No orders found for customer with ID " + customerId);
        }
        
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        
        throw new OrderNotFoundException(orderId, customerId);
    }
}