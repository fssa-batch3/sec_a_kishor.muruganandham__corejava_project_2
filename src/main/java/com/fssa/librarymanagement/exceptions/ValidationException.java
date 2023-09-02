package com.fssa.librarymanagement.exceptions;

import java.io.Serial;

/**
 * This exception class is used to handle validation errors related to validation layer operations.
 *
 * @author KishorMuruganandham
 */
public class ValidationException extends Exception {


	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new ValidationException with the specified detail message.
	 *
	 * @param message The detail message describing the validation error.
	 */
	public ValidationException(String message) {
		super(message);
	}


}
