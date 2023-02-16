package com.ntt.dao2;
import java.sql.*;
import java.util.Scanner;

import com.ntt.bookdomain.Book;


public class StudentBookDAO {
	public static boolean searchBook() throws BookDAOException {
	    Scanner sc = new Scanner(System.in);

	    System.out.print("Enter book title: ");
	    String bookTitle = sc.nextLine();

	    Book result = null;

	    try {
	        // create a connection to the database
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

	        // create a new prepared statement
	        PreparedStatement st = con.prepareStatement("SELECT * FROM books WHERE bookname = ?");

	        // set the values for the prepared statement
	        st.setString(1, bookTitle);

	        // execute the query
	        ResultSet rs = st.executeQuery();

	        // check if any rows were returned
	        if (rs.next()) {
	            // book was found in the database
	           // result = new Book(rs.getString("bookname"), rs.getString("isbn"), rs.getString("author"), rs.getString("publisher"), rs.getString("edition"), rs.getDouble("price"), rs.getInt("quantity"));
	            System.out.println("Book found:");
	            System.out.println("Title: " + rs.getString("bookname"));
	            System.out.println("Author: " + rs.getString("author"));
	            System.out.println("Availability: " + rs.getString("quantity"));
	            System.out.println("BookId: " + rs.getString("isbn"));
	            return true;
	        } else {
	            // book was not found in the database
	        	throw new BookDAOException("Book not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean requestBook()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter userid: ");
        String userid = sc.nextLine();
        System.out.println("Enter ISBN: ");
        String isbn = sc.nextLine();
        try {
            // create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

            // create a new prepared statement to check if the book is available
            PreparedStatement st = con.prepareStatement("SELECT * FROM books WHERE isbn = ? and quantity > 0");

            // set the values for the prepared statement
            st.setString(1, isbn);

            // execute the query
            ResultSet rs = st.executeQuery();

            // check if any rows were returned
            if (rs.next()) {
                // get the book details
                String bookname = rs.getString("bookname");
                String isbn1 = rs.getString("isbn");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                String edition = rs.getString("Edition");
                String price = rs.getString("Price");
                String quantity = rs.getString("Quantity");

                // create a new prepared statement to insert a request
                PreparedStatement requestSt = con.prepareStatement("INSERT INTO book_hire_details (bookname, isbn, author, publisher, edition, price, quantity, userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

                // set the values for the prepared statement
                requestSt.setString(1, bookname);
                requestSt.setString(2, isbn1);
                requestSt.setString(3, author);
                requestSt.setString(4, publisher);
                requestSt.setString(5, edition);
                requestSt.setString(6, price);
                requestSt.setString(7, quantity);
                requestSt.setString(8, userid);

                // execute the query
                requestSt.executeUpdate();

                System.out.println("Request for book with ISBN: " + isbn1 + " has been placed successfully!");
                return true;
            } else {
                // book is not available
                throw new BookDAOException("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (BookDAOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
	public static boolean cancelRequest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter userid: ");
		String userid = sc.nextLine();
		System.out.println("Enter isbn: ");
		String isbn = sc.nextLine();
		try {
		// create a connection to the database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");
		    // create a new prepared statement to delete the request from the database
		    PreparedStatement st = con.prepareStatement("DELETE FROM book_hire_details WHERE userid = ? AND isbn = ?");

		    // set the values for the prepared statement
		    st.setString(1, userid);
		    st.setString(2, isbn);

		    // execute the query
		    int rowsDeleted = st.executeUpdate();

		    if (rowsDeleted > 0) {
		        System.out.println("Request successfully canceled.");
		        return true;
		    } else {
		        System.out.println("No request found to cancel.");
		        return false;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	public static boolean getBook() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter userid: ");
		String userid = sc.nextLine();
		System.out.println("Enter ISBN: ");
		String isbn = sc.nextLine();
		try {
		// create a connection to the database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");
		// create a new prepared statement to check if the user ID exists
		PreparedStatement userSt = con.prepareStatement("SELECT * FROM users WHERE user_id = ?");
		userSt.setString(1, userid);
		ResultSet userRs = userSt.executeQuery();

		    // check if the user ID exists
		    if (userRs.next()) {
			
			    // create a new prepared statement to check if the book is available
			    PreparedStatement st = con.prepareStatement("SELECT * FROM books WHERE isbn = ? and quantity > 0");

			    // set the values for the prepared statement
			    st.setString(1, isbn);

			    // execute the query
			    ResultSet rs = st.executeQuery();

			    // check if any rows were returned
			    if (rs.next()) {
			        // get the book details
			        String isbn1 = rs.getString("isbn");
			        String quantity = rs.getString("quantity");
			        int newQuantity = Integer.parseInt(quantity) - 1;

			        // create a new prepared statement to update the book quantity
			        PreparedStatement updateSt = con.prepareStatement("UPDATE books SET quantity = ? WHERE isbn = ?");

			        // set the values for the prepared statement
			        updateSt.setInt(1, newQuantity);
			        updateSt.setString(2, isbn1);
			        // create a new prepared statement to update the book quantity
			        // execute the query
			        updateSt.executeUpdate();
			        System.out.println("Book Borrowed successfully!!! You must return the book within 15 days otherwise you will be fined...");
			        return true;
			      }
			    else {
		            // book is not available
			    	throw new BookDAOException("Book not found.");
		        }
			    
			    
			    } 
		    else {
		    	System.out.println("user Id is wrong enter correct user id");
		    	return false;
		    }
			}catch (SQLException e) {
		            e.printStackTrace();
			    } catch (BookDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	public static boolean returnBook() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter isbn: ");
	    String Bookid = sc.nextLine();
	    try {
	        // create a connection to the database
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

	        PreparedStatement updateQuantityStmt = con.prepareStatement("UPDATE books SET quantity = quantity+1 WHERE isbn = ?");
	        updateQuantityStmt.setString(1, Bookid);
	        updateQuantityStmt.executeUpdate();

	        System.out.println("Book returned successfully!");
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}