package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.User;
import com.fssa.librarymanagement.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBorrowService {

	private final User user = new User();
	private final Book book = new Book();
	private BorrowService borrowService;
	private Borrow borrow;

	@BeforeEach
	public void setUp() throws ValidationException {
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
		assertDoesNotThrow(() -> borrowService.borrowBook(borrow));
	}

	@Test
	@Order(2)
	void testInvalidBorrowBook() {
		assertThrows(ServiceException.class, () -> borrowService.borrowBook(borrow));
	}

	@Test
	@Order(3)
	void testInvalidBorrowBook_InvalidBorrowDate() {
		borrow.setBorrowDate(null);
		;
		assertThrows(ServiceException.class, () -> borrowService.borrowBook(borrow));
	}

	@Test
	@Order(4)
	void testValidGetBorrowsByUser() {
		assertDoesNotThrow(() -> borrowService.getBorrowsByUser(borrow.getUser().getUserId()));
	}

	@Test
	@Order(5)
	void testInvalidGetBorrowsByUser() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowsByUser(114));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(6)
	void testValidGetBorrowsByBook() {
		assertDoesNotThrow(() -> borrowService.getBorrowsByBook(borrow.getBook().getBookId()));
	}

	@Test
	@Order(7)
	void testInvalidGetBorrowsByBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowsByBook(54));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(8)
	void testGetAllBorrows() {
		assertDoesNotThrow(() -> borrowService.getAllBorrows());
	}

	@Test
	@Order(9)
	void testValidGetBorrowsById() {
		assertDoesNotThrow(() -> borrowService.getBorrowById(1));
	}

	@Test
	@Order(10)
	void testInvalidGetBorrowsById() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.getBorrowById(114));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(11)
	void testValidGetBorrowByUserAndBook() {
		assertDoesNotThrow(() -> borrowService.getBorrowByUserAndBook(user.getUserId(), book.getBookId()));
	}

	@Test
	@Order(12)
	void testInvalidGetBorrowByUserAndBook() {
		ServiceException result = assertThrows(ServiceException.class,
				() -> borrowService.getBorrowByUserAndBook(1, 0));
		assertEquals("Borrows not found.", result.getMessage());
	}

	@Test
	@Order(13)
	void testInvalidBorrowBook_InvalidBorrowDuration() {
		ValidationException result = assertThrows(ValidationException.class,
				() -> BorrowingDurationEnumMapper.mapToBorrowingDuration(7));
		assertEquals("No Borrowing Duration enum found for the given value", result.getMessage());
	}

	@Test
	@Order(14)
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
	@Order(15)
	void testInvalidReturnBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> borrowService.returnBook(borrow));
		assertEquals("Return date cannot be empty", result.getMessage());
	}

	@Test
	@Order(16)
	void testGetBorrowsByUser_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> borrowService.getBorrowsByUser(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(17)
	void testGetBorrowsByBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> borrowService.getBorrowsByBook(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(18)
	void testGetBorrowByUserAndBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> borrowService.getBorrowByUserAndBook(0, 0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(19)
	void testGetBorrowById_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> borrowService.getBorrowById(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(20)
	void testGetAllBorrows_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> borrowService.getAllBorrows());

		ConnectionUtil.setTestingMode(false);
	}

}
