package com.ntt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.ntt.dao1.LoginDAO;
import com.ntt.userdomain.User;

public class UserDAO {
	
	public static boolean addUser() {
		Scanner sc =new Scanner(System.in);
			System.out.println("Enter user id: ");
			String userId1 = sc.nextLine();

			System.out.println("Enter role id (1.student/2.admin): ");
			String roleId = sc.nextLine();

			System.out.println("Enter password: ");
			String password1 = sc.nextLine();

			System.out.println("Enter email: ");
			String email = sc.nextLine();

			System.out.println("Enter contact number: ");
			String contactNumber = sc.nextLine();

			// create a new user object with the entered information
			User newUser = new User(userId1, roleId, password1, email, contactNumber);

			// save the newuser to the database and return the result
			return LoginDAO.saveUser(newUser);
		}

	public static boolean deleteuser() {
		Scanner sc =new Scanner(System.in);
		System.out.println("enter the user id:");
		String userId1 = sc.nextLine();
			try {
				// create a connection to the database
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db",
						"root", "Gurram@420");

				// create a new prepared statement to delete the user
				PreparedStatement st = con.prepareStatement("DELETE FROM users WHERE user_id = ?");

				// set the value for the prepared statement
				st.setString(1, userId1);

				// execute the query
				int rowsAffected = st.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("User with id: " + userId1 + " has been deleted successfully!");
					return true;
				} else {
					throw new UserDAOException("USER ID NOT FOUND");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (UserDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
}