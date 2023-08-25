package com.fssa.librarymanagement.exceptions;

/**
 * This exception class is used to handle validation errors related to validation layer operations.
 *
 * @author KishorMuruganandham
 */
public class ValidationException extends Exception {


	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new ValidationException with the specified detail message.
	 *
	 * @param message The detail message describing the validation error.
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * Creates a new ValidationException with the specified cause.
	 *
	 * @param ex The cause of the validation exception.
	 */
	public ValidationException(Throwable ex) {
		super(ex);
	}

	/**
	 * Creates a new ValidationException with the specified detail message and cause.
	 *
	 * @param message The detail message describing the validation error.
	 * @param ex      The cause of the validation exception.
	 */
	public ValidationException(String message, Throwable ex) {
		super(message, ex);
	}
}
