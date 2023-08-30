package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
		user.setUserId(8);
		book.setBookId(9);
		borrow.setUser(user);
		borrow.setBook(book);
		borrow.setBorrowDate(LocalDate.now());
	}

	@Order(1)
	@Test
	void testValidBorrowBook() {
		try {
			String result = borrowService.borrowBook(borrow);
			assertEquals("Book borrowed successfully.", result);

		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Order(2)
	@Test
	void testInvalidBorrowBook() {
		assertThrows(ServiceException.class, () -> borrowService.borrowBook(borrow));
	}

	@Order(3)
	@Test
	void testValidReturnBook() {
		try {
			borrow.setReturnDate(borrow.getDueDate().plusDays(3));
			String result = borrowService.returnBook(borrow);
			assertEquals("Added return date successfully.", result);
			assertEquals(30, borrow.getFine());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(4)
	void testInvalidReturnBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.returnBook(borrow));
		assertEquals("Failed to return book", result.getMessage());
	}

	@Test
	@Order(5)
	void testValidGetBorrowsByUser() {
		try {
			List<Borrow> borrows = borrowService.getBorrowsByUser(borrow.getUser().getUserId());
			for (Borrow i : borrows) {
				System.out.println(i.getBook());
				System.out.println(i.getUser());
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
		try {
			List<Borrow> borrows = borrowService.getBorrowsByUser(114);
			assertNull(borrows);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(7)
	void testValidGetBorrowsByBook() {
		try {
			List<Borrow> borrows = borrowService.getBorrowsByBook(borrow.getBook().getBookId());
			for (Borrow i : borrows) {
				System.out.println(i.getBook());
				System.out.println(i.getUser());
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
		try {
			List<Borrow> borrows = borrowService.getBorrowsByBook(54);
			assertNull(borrows);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
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
}
