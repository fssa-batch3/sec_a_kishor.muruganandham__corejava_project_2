package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class TestBorrowValidation {

	private BorrowValidator borrowValidator;
	private Borrow borrow;

	@BeforeEach
	void setUp() {
		borrow = new Borrow();
		borrowValidator = new BorrowValidator(borrow);
	}

	@Test
	void testValidBorrowDate() {
		LocalDate validBorrowDate = LocalDate.now();
		boolean result = false;
		try {
			result = borrowValidator.validateBorrowDate(validBorrowDate);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(result);
	}

	@Test
	void testInvalidBorrowDate() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> borrowValidator.validateBorrowDate(null));
		assertEquals("Borrow date cannot be empty", result.getMessage());
	}

	@Test
	void testValidReturnDate() {
		LocalDate borrowDate = LocalDate.now().minus(Period.ofDays(7));
		LocalDate validReturnDate = LocalDate.now();
		borrow.setBorrowDate(borrowDate);

		boolean result = false;
		try {
			result = borrowValidator.validateReturnDate(validReturnDate);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(result);
	}

	@Test
	void testInvalidReturnDate() {
		LocalDate borrowDate = LocalDate.now();
		LocalDate invalidReturnDate = borrowDate.minus(Period.ofDays(1));
		borrow.setBorrowDate(borrowDate);

		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> borrowValidator.validateReturnDate(invalidReturnDate));
		assertEquals("Return date should be after the borrow date", result.getMessage());
	}

	@Test
	void testValidReturnDateForAlreadyReturnedBorrow() {
		LocalDate borrowDate = LocalDate.now().minus(Period.ofDays(10));
		LocalDate returnDate = LocalDate.now().minus(Period.ofDays(5));
		borrow.setBorrowDate(borrowDate);

		boolean result = false;
		try {
			result = borrowValidator.validateReturnDate(returnDate);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(result);
	}
}
