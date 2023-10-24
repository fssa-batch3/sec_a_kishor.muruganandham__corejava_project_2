/**
 * 
 */
package com.fssa.librarymanagement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;

/**
 * 
 */
class TestDAOException {
	@Test
	void testDAOException() {
		try {
			throw new DAOException("DAO exception with message");
		} catch (DAOException e) {
			assertEquals("DAO exception with message", e.getMessage());
		}
	}

	@Test
	void testDAOExceptionWithThrowable() {
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new DAOException(cause);
		} catch (DAOException e) {
			assertEquals(cause, e.getCause());
		}
	}

	@Test
	void testDAOExceptionWithMessageAndThrowable() {
		String message = "DAO exception message";
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new DAOException(message, cause);
		} catch (DAOException e) {
			assertEquals(message, e.getMessage());
			assertEquals(cause, e.getCause());
		}
	}

}
