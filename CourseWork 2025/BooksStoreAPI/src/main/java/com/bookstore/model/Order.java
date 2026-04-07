//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private double total;
    private List<OrderItem> items;
    
    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = new Date();
    }
    
    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = new Date();
        this.items = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    // Replace the getOrderDate method to return a formatted string instead of Date object
    public String getOrderDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(orderDate);
    }
    
    // Add a method to set the date that accepts a Date object
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public void addItem(OrderItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
    
    public void calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        this.total = total;
    }
}