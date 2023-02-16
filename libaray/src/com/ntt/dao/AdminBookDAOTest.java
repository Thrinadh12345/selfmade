 package com.ntt.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdminBookDAOTest {
	
    @Test
    public void testAddBook() {
    	System.out.println(" Testing for AddBook!!!");
        String bookname = "java";
        String ISBN = "1998";
        String author = "ram";
        String publisher = "sita";
        String edition = "5";
        double price = 500.00;
        int quantity = 3;

        boolean result = AdminBookDAO.addBook(bookname, ISBN, author, publisher, edition, price, quantity);
        assertTrue(result);
    }
    
    @Test
    public void testUpdateBook() {
    	System.out.println();
    	System.out.println(" Testing for UpdateBook!!!");
        String ISBN = "1";
        String newBookName = "c";
        String newAuthor = "sita";
        String newPublisher = "ram";
        String newEdition = "5";
        Double newPrice = 600.0;
        int newQuantity = 7;
        
        boolean result = AdminBookDAO.updateBook(ISBN, newBookName, newAuthor, newPublisher, newEdition, newPrice, newQuantity);
        assertEquals(true, result);
    }

    @Test
    public void testDeleteBook() {
    	System.out.println();
    	System.out.println(" Testing for DeleteBook!!!");
       boolean result = AdminBookDAO.deleteBook();
       assertTrue(result);
    }
}
