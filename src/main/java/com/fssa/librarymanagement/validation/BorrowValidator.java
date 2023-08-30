package com.fssa.librarymanagement.validation;

import java.time.LocalDate;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;

/**
 * This class provides Validations for all Borrow data.
 *
 *
 * @author KishorMuruganandham
 */
public class BorrowValidator {

	private Borrow borrow;

	public BorrowValidator(Borrow borrow) {
		this.borrow = borrow;
	}

	public BorrowValidator() {

	}

	/**
	 * Validates all Borrow attributes.
	 *
	 * @return true if all attributes are valid
	 * @throws ValidationException If any validation fails
	 */
	public boolean validateAll() throws ValidationException {
		return validateBorrowDate(borrow.getBorrowDate()) && validateReturnDate(borrow.getReturnDate());
	}

	/**
	 * Validates the borrow date.
	 *
	 * @param borrowDate The borrow date to validate
	 * @return true if borrow date is valid
	 * @throws ValidationException If borrow date is empty
	 */
	public boolean validateBorrowDate(LocalDate borrowDate) throws ValidationException {
		if (borrowDate == null) {
			throw new ValidationException("Borrow date cannot be empty");
		}
		return true;
	}

	/**
	 * Validates the return date.
	 *
	 * @param returnDate The return date to validate
	 * @return true if return date is valid
	 * @throws ValidationException If return date is empty or before borrow date
	 */
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
