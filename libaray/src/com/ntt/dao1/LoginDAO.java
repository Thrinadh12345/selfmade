package com.ntt.dao1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ntt.userdomain.User;



public class LoginDAO {
	
	public static boolean register() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter User Id: ");
		String userId = sc.nextLine();

		System.out.println("Enter Role Id (1.student/2.admin): ");
		String roleId = sc.nextLine();

		System.out.println("Enter Password: ");
		String password = sc.nextLine();

		System.out.println("Enter Email: ");
		String email = sc.nextLine();

		System.out.println("Enter Contact Number: ");
		String contactNumber = sc.nextLine();
			User newAdmin = new User(userId, roleId, password, email, contactNumber);
			return saveUser(newAdmin);
	}



    public static boolean login(String userId, String password,String roleid) {
        try {
            // create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

            // create a new prepared statement
            PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password = ? AND role_id = ?");

            // set the values for the prepared statement
            st.setString(1, userId);
            st.setString(2, password);
            st.setString(3, roleid);

            // execute the query
            ResultSet rs = st.executeQuery();

            if (rs.next() && rs.getString("role_id").equals(roleid)) {
                // user was found in the database and has the correct role_id
                System.out.println("Welcome!");
                return true;
            } else {
                // user was not found in the database or has the wrong role_id
                throw new LoginDAOException("Invalid userId, password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginDAOException e) {
            System.out.println(e.getMessage());
            
        }
        return false;
    }


	public static boolean saveUser(User newAdmin) {
	    try {
	        // create a connection to the database
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Gurram@420");

	        // check if user id already exists
	        PreparedStatement checkStmt = con.prepareStatement("SELECT user_id FROM users WHERE user_id = ?");
	        checkStmt.setString(1, newAdmin.getUserId());
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next()) {
	            System.out.println("User ID is already taken. Please try another one.");
	            register();
	            return false;
	        }

	        // check if email already exists
	        checkStmt = con.prepareStatement("SELECT email FROM users WHERE email = ?");
	        checkStmt.setString(1, newAdmin.getEmail());
	        rs = checkStmt.executeQuery();
	        if (rs.next()) {
	            System.out.println("Email is wrong. Please try another one.");
	            register();
	            return false;
	        }

	        // check if contact number already exists
	        checkStmt = con.prepareStatement("SELECT contact_number FROM users WHERE contact_number = ?");
	        checkStmt.setString(1, newAdmin.getContactNumber());
	        rs = checkStmt.executeQuery();
	        if (rs.next()) {
	            System.out.println("Contact Number is Wrong. Please try another one.");
	            register();
	            return false;
	        }

	        // create a new prepared statement
	        PreparedStatement st = con.prepareStatement(
	                "INSERT INTO users (user_id, role_id, password, email, contact_number) VALUES (?, ?, ?, ?, ?)");

	        // set the values for the prepared statement
	        st.setString(1, newAdmin.getUserId());
	        st.setString(2, newAdmin.getRoleId());
	        st.setString(3, newAdmin.getPassword());
	        st.setString(4, newAdmin.getEmail());
	        st.setString(5, newAdmin.getContactNumber());

	        // execute the prepared statement
	        st.executeUpdate();
	        System.out.println("Registered successfully");
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}

}