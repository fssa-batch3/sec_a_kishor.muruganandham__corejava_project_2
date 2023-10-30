/**
 *
 */
package com.fssa.librarymanagement.exception;

import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
class TestDatabaseConnectionException {

	@Test
	void testDatabaseConnectionException() {
		try {
			throw new DatabaseConnectionException("Database Connection exception with message");
		} catch (DatabaseConnectionException e) {
			assertEquals("Database Connection exception with message", e.getMessage());
		}
	}

	@Test
	void testDatabaseConnectionExceptionWithThrowable() {
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new DatabaseConnectionException(cause);
		} catch (DatabaseConnectionException e) {
			assertEquals(cause, e.getCause());
		}
	}

	@Test
	void testDatabaseConnectionExceptionWithMessageAndThrowable() {
		String message = "Database Connection exception message";
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new DatabaseConnectionException(message, cause);
		} catch (DatabaseConnectionException e) {
			assertEquals(message, e.getMessage());
			assertEquals(cause, e.getCause());
		}
	}

}
