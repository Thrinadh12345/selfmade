package com.ntt.dao1;
import static org.junit.Assert.*;

import org.junit.Test;

import com.ntt.dao.UserDAO;

public class LoginDAOTest {
	    @Test
	   public void testLogin() {
	    	System.out.println();
	    	System.out.println(" Testing for Login!!!");
	        String userId = "01";
	        String password = "123";
	        String roleid = "2";
	        boolean result = LoginDAO.login(userId, password, roleid);
	        assertTrue(result);
	    }
	    @Test
	    public void testRegister() {
	    	System.out.println();
	    	System.out.println(" Testing for Admin Registration!!!");
	       boolean result = UserDAO.addUser();
	       assertTrue(result);
	    }

}