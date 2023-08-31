package com.fssa.librarymanagement.exceptions;

import java.io.Serial;

/**
 * This exception class is used to handle errors related to service layer operations.
 *
 * @author KishorMuruganandham
 */
public class ServiceException extends Exception {


	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new ServiceException with the specified detail message.
	 *
	 * @param msg The detail message.
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Creates a new ServiceException with the specified cause.
	 *
	 * @param ex The cause of the exception.
	 */
	public ServiceException(Throwable ex) {
		super(ex);
	}

	/**
	 * Creates a new ServiceException with the specified detail message and cause.
	 *
	 * @param msg The detail message.
	 * @param ex  The cause of the exception.
	 */
	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
