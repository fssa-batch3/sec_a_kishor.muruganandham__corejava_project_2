/**
 * 
 */
package com.fssa.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
class TestBorrowDAO {
	private BorrowDAO borrowDAO;

	@BeforeEach
	public void setUp() {
		borrowDAO = new BorrowDAO();
	}

	@Test
	void testBorrowBook() {
		ConnectionUtil.setTestingMode(true);
		Borrow borrow = new Borrow();
		assertThrows(DAOException.class, () -> borrowDAO.borrowBook(borrow));

		ConnectionUtil.setTestingMode(false);
	}
	
	@Test
	void testReturnBook() {
		ConnectionUtil.setTestingMode(true);
		Borrow borrow = new Borrow();
		assertThrows(DAOException.class, () -> borrowDAO.returnBook(borrow));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetBorrowsByUser() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getBorrowsByUser(0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetBorrowsByBook() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getBorrowsByBook(0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetBorrowByUserAndBook() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getBorrowByUserAndBook(0,0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetBorrowById() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getBorrowById(0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetAvailableCopiesCount() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getAvailableCopiesCount(0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetBorrowedBooksCountByUser() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getBorrowedBooksCountByUser(0));
		
		ConnectionUtil.setTestingMode(false);
	}
	@Test
	void testGetAllBorrows() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(DAOException.class, () -> borrowDAO.getAllBorrows());
		
		ConnectionUtil.setTestingMode(false);
	}
}
