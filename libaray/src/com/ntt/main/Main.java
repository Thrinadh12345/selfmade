package com.ntt.main;

import java.util.Scanner;

import com.ntt.dao.AdminBookDAO;
import com.ntt.dao.UserDAO;
import com.ntt.dao1.LoginDAO;
import com.ntt.dao2.StudentBookDAO;
import com.ntt.userdomain.User;

public class Main {
	public static void main(String[] args) {
		System.out.println("---------Welcome to Library Management System-----------");
		library_home();
	}

	static User user = new User("userId", "roleId", "password", "email", "contactNumber");

	private static void library_home()  {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("1.ADMIN REGISTRATION ");
		System.out.println("2.USERS LOGIN ");
		System.out.println("3.EXIT ");
		System.out.println();
		System.out.println("Enter your choice: ");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			System.out.println("WELCOME TO ADMIN REGISTRATION ");
			System.out.println();
			LoginDAO.register();
			login();
			break;
		case 2:
			login();
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("Enter valid Input... ");
			library_home();
			break;
		}
	}
	
	
//book	
	public static void bookInfo()  {
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		int status = 0;
		do {
			System.out.println();
			System.out.println("1.ADD BOOK");
			System.out.println("2.UPDATE BOOK");
			System.out.println("3.DELETE BOOK");
			System.out.println();
			System.out.println("Enter your choice: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				AdminBookDAO.addBookFromUser();
				break;
			case 2:
				AdminBookDAO.updateBookFromUser();
				break;
			case 3:
				AdminBookDAO.deleteBook();
				break;
			}
			System.out.println("Do you wish to continue(press any number or zero to main menu)");
			status = sc.nextInt();
		} while (!(status == 0));
		System.out.println("MAIN MENU!!!");
	}
//user
	public static void userInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.ADD USER");
		System.out.println("2.DELETE USER");
		System.out.println();
		System.out.println("Enter your choice: ");
		String userType = sc.nextLine();
		if (userType.equals("1")) {
			UserDAO.addUser();
		} else if (userType.equals("2")) {
			UserDAO.deleteuser();
		}
	}
	
	

	// LOGIN
	private static void login()  {
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		int status = 0;
		System.out.println("WELCOME TO USERS LOGIN ");
		System.out.println();
		System.out.println("1.STUDENT LOGIN ");
		System.out.println("2.ADMIN LOGIN ");
		System.out.println();
		System.out.println("Enter your choice: ");
		String loginType = sc.nextLine();
		// student
		if (loginType.equals("1")) {
			System.out.println("Enter Student Id: ");
			String userId = sc.nextLine();
			System.out.println("Enter password: ");
			String password = sc.nextLine();
			if (LoginDAO.login(userId, password, loginType)) {
				do {
					System.out.println("Enter your choice: ");
					System.out.println();
					System.out.println("1.SEARCH BOOK 2.REQUEST BOOK 3.CANCEL REQUEST 4.GET BOOK 5.RETURN BOOK");
					ch = sc.nextInt();
					switch (ch) {
					case 1:
						try {
							StudentBookDAO.searchBook();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 2:
						try {
							StudentBookDAO.requestBook();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 3:
						StudentBookDAO.cancelRequest();
						break;
					case 4:
						try {
							StudentBookDAO.getBook();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 5:
						StudentBookDAO.returnBook();
					}
					System.out.println("Do you wish to continue(press any number not zero)");
					status = sc.nextInt();
				} while (!(status == 0));
				System.out.println("**Thank You! Have a nice day!**");
			} else {
				login();
			}
// ADMIN
		} else if (loginType.equals("2")) {
			System.out.println("Enter Admin Id: ");
			String userId = sc.nextLine();
			System.out.println("Enter Admin Password: ");
			String password = sc.nextLine();
			if (LoginDAO.login(userId, password, loginType)) {
				adminProcess();
			} else {
				login();
			}

		}
	}
	private static void adminProcess()  {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.ACCESS BOOK INFORMATION");
		System.out.println("2.ACCESS USER DETAILS");
		System.out.println("3.EXIT ");
		System.out.println();
		System.out.println("Enter your choice: ");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			bookInfo();
			adminProcess();
			break;
		case 2:
			userInfo();
			adminProcess();
			break;
		case 3:
			System.err.println("\"**Thank You! Have a nice day!**\"");
			System.exit(0);
		default:
			System.out.println("Enter valid Input... ");
			adminProcess();
			break;
		}
	}


}