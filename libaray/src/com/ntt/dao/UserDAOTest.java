package com.ntt.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserDAOTest {
	 @Test
	    public void testAddUser() {
	    	System.out.println();
	    	System.out.println(" Testing for AddUser!!!");
	       boolean result = UserDAO.addUser();
	       assertTrue(result);
	    }
	 @Test
	    public void testDeleteUser() {
	    	System.out.println();
	    	System.out.println(" Testing for DeleteUser!!!");
	       boolean result = UserDAO.deleteuser();
	       assertTrue(result);
	    }
	
}