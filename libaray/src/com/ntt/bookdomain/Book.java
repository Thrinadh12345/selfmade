package com.ntt.bookdomain;


public class Book {
	private String bookName;
    private String ISBN;
    private String author;
    private String publisher;
    private String edition;
    private double price;
    private int quantity;

    // constructor
    public Book(String bookName, String ISBN, String author, String publisher, String edition, double price, int quantity) {
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
        this.price = price;
        this.quantity = quantity;
    }

    // getters
    public String getBookName() {
        return bookName;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getEdition() {
        return edition;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}