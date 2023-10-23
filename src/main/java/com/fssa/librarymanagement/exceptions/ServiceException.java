package com.fssa.librarymanagement.exceptions;

import java.io.Serial;

/**
 * This exception class is used to handle errors related to service layer
 * operations.
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

	public ServiceException(Throwable ex) {
		super(ex);
	}

	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
