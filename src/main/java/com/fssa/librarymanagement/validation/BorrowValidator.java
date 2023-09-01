package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;

import java.time.LocalDate;

/**
 * This class provides Validations for all Borrow data.
 *
 * @author KishorMuruganandham
 */
public class BorrowValidator {

	private Borrow borrow; // The Borrow object to be validated

	/**
	 * Constructs a BorrowValidator instance with a specific borrow to validate.
	 *
	 * @param borrow The Borrow object to be validated.
	 */
	public BorrowValidator(Borrow borrow) {
		this.borrow = borrow;
	}

	/**
	 * Constructs a BorrowValidator instance without a specific borrow.
	 * This constructor can be used when validating borrows with individual validation methods.
	 */
	public BorrowValidator() {
		// Default constructor
	}

	/**
	 * Validates all Borrow attributes.
	 *
	 * @return true if all attributes are valid
	 * @throws ValidationException If any validation fails
	 */
	public boolean validateAll() throws ValidationException {
		// TO Do validations for user and book specific fields
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
	 * @return true if the return date is valid
	 * @throws ValidationException If the return date is empty or before borrow date
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
