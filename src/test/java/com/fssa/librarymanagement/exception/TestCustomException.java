/**
 * 
 */
package com.fssa.librarymanagement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * 
 */
class TestCustomException {

	@Test
	void testCustomException() {
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

	@Test
	void testServiceException() {
		try {
			throw new ServiceException("Service exception with message");
		} catch (ServiceException e) {
			assertEquals("Service exception with message", e.getMessage());
		}
	}

	@Test
	void testServiceExceptionWithThrowable() {
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new ServiceException(cause);
		} catch (ServiceException e) {
			assertEquals(cause, e.getCause());
		}
	}

	@Test
	void testServiceExceptionWithMessageAndThrowable() {
		String message = "Service exception message";
		Throwable cause = new RuntimeException("Root cause");
		try {
			throw new ServiceException(message, cause);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
			assertEquals(cause, e.getCause());
		}
	}

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
