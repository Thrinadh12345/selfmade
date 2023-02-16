package com.ntt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ntt.dao2.BookDAOException;

public class AdminBookDAO {
    public static void addBookFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Book Name: ");
        String bookname = sc.nextLine();
        System.out.println("Enter ISBN: ");
        String ISBN = sc.nextLine();
        System.out.println("Enter Author Name: ");
        String author = sc.nextLine();
        System.out.println("Enter Publisher Name: ");
        String publisher = sc.nextLine();
        System.out.println("Enter Edition: ");
        String edition = sc.nextLine();
        System.out.println("Enter Price: ");
        Double price = sc.nextDouble();
        System.out.println("Enter Quantity: ");
        int quantity = sc.nextInt();
        addBook(bookname, ISBN, author, publisher, edition, price, quantity);
    }
    public static void updateBookFromUser()  {
    	Scanner sc = new Scanner(System.in);

    	System.out.println("Enter ISBN of book to update: ");
    	String isbn = sc.nextLine();

    	System.out.println("Enter new Book Name: ");
    	String newBookName = sc.nextLine();

    	System.out.println("Enter new Author Name: ");
    	String newAuthor = sc.nextLine();

    	System.out.println("Enter new Publisher Name: ");
    	String newPublisher = sc.nextLine();

    	System.out.println("Enter new Edition: ");
    	String newEdition = sc.nextLine();

    	System.out.println("Enter new Price: ");
    	Double newPrice = sc.nextDouble();

    	System.out.println("Enter new Quantity: ");
    	int newQuantity = sc.nextInt();

    	updateBook(isbn, newBookName, newAuthor, newPublisher, newEdition, newPrice, newQuantity);
    }
    
    public static boolean addBook(String bookname, String ISBN, String author, String publisher, String edition, double price, int quantity) {
    	try {
    		
    	// create a connection to the database
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");
    	        // check if ISBN already exists
    	        PreparedStatement stCheck = con.prepareStatement("SELECT * FROM books WHERE ISBN = ?");
    	        stCheck.setString(1, ISBN);
    	        ResultSet rs = stCheck.executeQuery();
    	        if (rs.next()) {
    	            System.out.println("A Book with ISBN " + ISBN + " already exists. Please enter a different ISBN.");
    	            con.close();
    	            return false;
    	        }

    	        // create a new prepared statement
    	        PreparedStatement st = con.prepareStatement("INSERT INTO books (bookname, ISBN, author, publisher, edition, price, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)");
    	        // set the values for the prepared statement
    	        st.setString(1, bookname);
    	        st.setString(2, ISBN);
    	        st.setString(3, author);
    	        st.setString(4, publisher);
    	        st.setString(5, edition);
    	        st.setDouble(6, price);
    	        st.setInt(7, quantity);
    	        // execute the update
    	        st.executeUpdate();
    	        // close the connection to the database
    	        con.close();
    	        System.out.println("Book added successfully!");
    	        return true;
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    	}
    public static boolean updateBook(String newISBN, String newBookName, String newAuthor, String newPublisher, String newEdition, Double newPrice, int newQuantity) {
        try {
            // create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

            // create a new prepared statement to update the book
            PreparedStatement st = con.prepareStatement("UPDATE books SET bookname = ?, author = ?, publisher = ?, edition = ?, price = ?, quantity = ? WHERE isbn = ?");
            // set the values for the prepared statement
            st.setString(1, newBookName);
            st.setString(7, newISBN);
            st.setString(2, newAuthor);
            st.setString(3, newPublisher);
            st.setString(4, newEdition);
            st.setDouble(5, newPrice);
            st.setInt(6, newQuantity);

            // execute the query
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book with ISBN: " + newISBN + " has been updated successfully!");
                con.close();
                return true;
            } else {
                throw new BookDAOException("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (BookDAOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean deleteBook()  {
        try {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Enter ISBN of book to delete: ");
				String ISBN = sc.nextLine();
            // create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

            // create a new prepared statement
            PreparedStatement st = con.prepareStatement("DELETE FROM books WHERE isbn = ?");

            // set the values for the prepared statement
            st.setString(1, ISBN);

            // execute the query
                int rowsAffected =st.executeUpdate();
                con.close();
            if (rowsAffected > 0) {
                System.out.println("Book with ISBN: " + ISBN + " has been deleted successfully!");
                return true;
            } else {
            	throw new BookDAOException("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (BookDAOException e) {
            System.out.println(e.getMessage());
            // code to handle the exception and continue program execution
            return false;
        }
    }
}