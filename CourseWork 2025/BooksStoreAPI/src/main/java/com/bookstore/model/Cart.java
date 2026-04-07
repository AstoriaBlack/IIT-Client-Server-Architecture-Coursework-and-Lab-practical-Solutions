//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int customerId;
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
    public void addItem(CartItem item) {
        // Check if the book is already in the cart
        for (CartItem existingItem : items) {
            if (existingItem.getBookId() == item.getBookId()) {
                // Update quantity instead of adding a new item
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // If the book is not in the cart, add it
        items.add(item);
    }
    
    public void updateItemQuantity(int bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId() == bookId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void removeItem(int bookId) {
        items.removeIf(item -> item.getBookId() == bookId);
    }
    
    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return Math.round(total * 100.0) / 100.0;
    }
    
    public void clear() {
        items.clear();
    }
}