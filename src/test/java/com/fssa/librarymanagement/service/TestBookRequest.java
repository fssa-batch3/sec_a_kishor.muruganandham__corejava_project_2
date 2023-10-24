/**
 * 
 */
package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.BookRequest;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
class TestBookRequest {

	private BookRequestService bookRequestService;
	private BookRequest bookRequest;

	@BeforeEach
	public void setUp() {
		bookRequestService = new BookRequestService();
		bookRequest = new BookRequest();
		bookRequest.setBookName("Building Second Brain");
		bookRequest.setAuthorName("Tiago Forte");
		bookRequest.setSourceLink("");
		bookRequest.setDescription("It will be useful");
	}

	@Test
	@Order(1)
	void testValidCreateBookRequest() {
		assertDoesNotThrow(() -> bookRequestService.createBookRequest(bookRequest));

	}

	@Test
	@Order(12)
	void testInvalidCreateBookRequest() {
		bookRequest = new BookRequest();
		ServiceException result = assertThrows(ServiceException.class,
				() -> bookRequestService.createBookRequest(bookRequest));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	@Order(3)
	void testValidGetAllBookRequests() {
		assertDoesNotThrow(() -> bookRequestService.getAllBookRequests());
	}

	@Test
	@Order(4)
	void testInValidGetAllBookRequests() {

		assertDoesNotThrow(() -> bookRequestService.getAllBookRequests());
	}
	
	@Test
	void testInValidGetAllBookRequests_Exception() {
		ConnectionUtil.setTestingMode(true);
		
		assertThrows(ServiceException.class, () -> bookRequestService.getAllBookRequests());
		
		ConnectionUtil.setTestingMode(false);
	}
}
