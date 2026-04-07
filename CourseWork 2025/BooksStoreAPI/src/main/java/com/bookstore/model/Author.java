//w2087749_M.A.K.G. Jayaweera_20230734
package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private String biography;
    private List<String> books;
    
    public Author() {
        this.books = new ArrayList<>();
    }
    
    public Author(int id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.books = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public List<String> getBooks() {
        return books;
    }
    
    public void setBooks(List<String> books) {
        this.books = books;
    }
    
    public void addBook(String bookTitle) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(bookTitle);
    }
}