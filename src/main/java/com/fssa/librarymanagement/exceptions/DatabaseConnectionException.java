package com.fssa.librarymanagement.exceptions;

import java.io.Serial;

/**
 * This exception class is used to handle errors related to database connection.
 *
 * @author KishorMuruganandham
 */
public class DatabaseConnectionException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new DatabaseConnectionException with the specified detail
	 * message.
	 *
	 * @param msg The detail message.
	 */
	public DatabaseConnectionException(String msg) {
		super(msg);
	}

	public DatabaseConnectionException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public DatabaseConnectionException(Throwable ex) {
		super(ex);
	}

}
