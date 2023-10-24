/**
 * 
 */
package com.fssa.librarymanagement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * 
 */
class TestValidationException {
	@Test
	void testValidationException() {
		try {
			throw new ValidationException("Validation exception with message");
		} catch (ValidationException e) {
			assertEquals("Validation exception with message", e.getMessage());
		}
	}

	@Test
	void testValidationExceptionWithThrowable() {
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new ValidationException(cause);
		} catch (ValidationException e) {
			assertEquals(cause, e.getCause());
		}
	}

	@Test
	void testValidationExceptionWithMessageAndThrowable() {
		String message = "Validation exception message";
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new ValidationException(message, cause);
		} catch (ValidationException e) {
			assertEquals(message, e.getMessage());
			assertEquals(cause, e.getCause());
		}
	}
}
