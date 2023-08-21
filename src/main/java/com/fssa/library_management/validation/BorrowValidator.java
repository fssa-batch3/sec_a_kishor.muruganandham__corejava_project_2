package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.Borrow;

import java.time.LocalDate;

public class BorrowValidator {

	private Borrow borrow;

	public BorrowValidator(Borrow borrow) {
		this.borrow = borrow;
	}

	public BorrowValidator() {

	}
	// TODO: Validate both User and Book, Validate Borrow Object
	// TODO: Eg: UserConstants,etc

	public boolean validateBorrowDate(LocalDate borrowDate) throws ValidationException {
		if (borrowDate == null) {
			throw new ValidationException("Borrow date cannot be empty");
		}
		return true;
	}

	public boolean validateReturnDate(LocalDate returnDate) throws ValidationException {
		LocalDate borrowDate = borrow.getBorrowDate();
		if (returnDate == null) {
			throw new ValidationException("Return date cannot be empty");
		}
		if (returnDate.isBefore(borrowDate)) {
			throw new ValidationException("Return date should be after the borrow date");
		}
		return true;
	}
}
