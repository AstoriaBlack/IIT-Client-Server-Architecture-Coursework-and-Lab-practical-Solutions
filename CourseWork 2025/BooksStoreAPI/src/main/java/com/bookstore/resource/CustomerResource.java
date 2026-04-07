//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.resource;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Customer;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static int nextId = 1;
    
    private boolean emailExists(String email) {
        for (Customer customer : customers.values()) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidEmail(String email) {
        // Basic email format validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }
    
    @GET
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }
    
    @POST
    public Response addCustomer(Customer customer) {
        validateCustomer(customer);
        
        // Check for duplicate email
        if (emailExists(customer.getEmail())) {
            throw new InvalidInputException("Email " + customer.getEmail() + " is already registered");
        }
        
        customer.setId(nextId++);
        customers.put(customer.getId(), customer);
        return Response.status(Response.Status.CREATED)
                      .entity(customer)
                      .build();
    }
    
    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException(id);
        }
        
        validateCustomer(updatedCustomer);
        
        updatedCustomer.setId(id);
        customers.put(id, updatedCustomer);
        return updatedCustomer;
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException(id);
        }
        customers.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Customer email cannot be empty");
        }
        
        if (!isValidEmail(customer.getEmail())) {
            throw new InvalidInputException("Invalid email format");
        }
        
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Customer password cannot be empty");
        }
    }
}