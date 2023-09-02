package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class TestBorrowValidation {

	private BorrowValidator borrowValidator = new BorrowValidator();
	private Borrow borrow;

	@BeforeEach
	void setUp() {
		borrow = new Borrow();
		borrowValidator = new BorrowValidator(borrow);
	}

	@Test
	void testValidBorrowDate() {
		LocalDate validBorrowDate = LocalDate.now();
		assertDoesNotThrow(() -> borrowValidator.validateBorrowDate(validBorrowDate));
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
		assertDoesNotThrow(() -> borrowValidator.validateReturnDate(validReturnDate));
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
}
