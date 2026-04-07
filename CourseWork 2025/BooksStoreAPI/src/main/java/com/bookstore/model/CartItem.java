//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.model;

public class CartItem {
    private int bookId;
    private int quantity;
    private double price;
    private String bookTitle;
    
    public CartItem() {
    }
    
    public CartItem(int bookId, int quantity, double price, String bookTitle) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
        this.bookTitle = bookTitle;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    public double getSubtotal() {
        return price * quantity;
    }
}