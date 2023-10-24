/**
 * 
 */
package com.fssa.librarymanagement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ServiceException;

/**
 * 
 */
class TestServiceException {
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

}
