/**
 *
 */
package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.BookRequest;
import com.fssa.librarymanagement.utils.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
class TestBookRequestDAO {
	private BookRequestDAO bookRequestDAO;

	@BeforeEach
	public void setUp() {
		bookRequestDAO = new BookRequestDAO();
	}

	@Test
	void testCreateBookRequest() {
		ConnectionUtil.setTestingMode(true);
		BookRequest bookRequest = new BookRequest();
		assertThrows(DAOException.class, () -> bookRequestDAO.createBookRequest(bookRequest));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetAllBookRequests() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookRequestDAO.getAllBookRequests());

		ConnectionUtil.setTestingMode(false);
	}
}
