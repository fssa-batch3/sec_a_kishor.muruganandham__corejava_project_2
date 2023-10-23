package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBorrowService {

	private final User user = new User();
	private final Book book = new Book();
	private BorrowService borrowService;
	private Borrow borrow;

	@BeforeEach
	public void setUp() {
		borrowService = new BorrowService();
		borrow = new Borrow();
		user.setUserId(1);
		book.setBookId(5);
		borrow.setUser(user);
		borrow.setBook(book);
		borrow.setBorrowDate(LocalDateTime.now());
		borrow.setDueDate(BorrowingDurationEnumMapper.mapToBorrowingDuration(5));
	}

	@Test
	@Order(1)
	void testValidBorrowBook() {
		try {
			boolean result = borrowService.borrowBook(borrow);
			assertTrue(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(2)
	void testInvalidBorrowBook() {
		assertThrows(ServiceException.class, () -> borrowService.borrowBook(borrow));
	}

	@Test
	@Order(3)
	void testValidReturnBook() {
		try {
			borrow.setReturnDate(borrow.getDueDate().plusDays(3));
			boolean result = borrowService.returnBook(borrow);
			assertTrue(result);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(4)
	void testInvalidReturnBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.returnBook(borrow));
		assertEquals("Return date cannot be empty", result.getMessage());
	}

	@Test
	@Order(5)
	void testValidGetBorrowsByUser() {
		try {
			List<Borrow> borrows = borrowService.getBorrowsByUser(borrow.getUser().getUserId());
			for (Borrow i : borrows) {
				System.out.println(i);
				System.out.println(i);
			}
			assertNotNull(borrows);
			assertFalse(borrows.isEmpty());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(6)
	void testInvalidGetBorrowsByUser() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowsByUser(114));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(7)
	void testValidGetBorrowsByBook() {
		try {
			List<Borrow> borrows = borrowService.getBorrowsByBook(borrow.getBook().getBookId());
			for (Borrow i : borrows) {
				System.out.println(i);
				System.out.println(i);
			}
			assertNotNull(borrows);
			assertFalse(borrows.isEmpty());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(8)
	void testInvalidGetBorrowsByBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowsByBook(54));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(9)
	void testGetAllBorrows() {
		try {
			List<Borrow> borrows = borrowService.getAllBorrows();
			assertNotNull(borrows);
			assertFalse(borrows.isEmpty());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(10)
	void testValidGetBorrowsById() {
		assertDoesNotThrow(() -> borrowService.getBorrowById(1));
	}

	@Test
	@Order(11)
	void testInvalidGetBorrowsById() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowById(114));
		assertEquals("Borrows not found.", result.getMessage());
	}

}
