package com.ntt.dao2;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentBookDAOTest {
	@Test
	public void test5_ReturnBook() {
		System.out.println();
		System.out.println(" Testing for ReturnBook!!!");
		boolean result = StudentBookDAO.returnBook();
		assertEquals(true, result);
	}

	@Test
	public void test4_GetBook() {
		System.out.println();
		System.out.println(" Testing for GetBook!!!");
		boolean result = StudentBookDAO.getBook();
		assertEquals(true, result);
	}

	@Test
	public void test3_CancelRequest() {
		System.out.println();
		System.out.println(" Testing for CancelRequest!!!");
		boolean result = StudentBookDAO.cancelRequest();
		assertEquals(true, result);
	}

	@Test
	public void test2_RequestBook() {
		System.out.println();
		System.out.println(" Testing for RequestBook!!!");
		boolean result = StudentBookDAO.requestBook();
		assertEquals(true, result);
	}

	@Test
	public void test1_SearchBook() {
		System.out.println();
		System.out.println(" Testing for SearchBook!!!");
		try {
			boolean result = StudentBookDAO.searchBook();
			assertTrue(result);
		} catch (BookDAOException e) {
			e.printStackTrace();
		}
	}
}