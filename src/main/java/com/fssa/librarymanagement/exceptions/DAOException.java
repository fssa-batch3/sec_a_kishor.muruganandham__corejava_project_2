package com.fssa.librarymanagement.exceptions;

import java.io.Serial;

/**
 * This exception class is used to handle errors related to Data Access Object (DAO) operations.
 *
 * @author KishorMuruganandham
 */
public class DAOException extends Exception {


	@Serial
	private static final long serialVersionUID = 1L;


	/**
	 * Constructs a new DAOException with the specified cause.
	 *
	 * @param ex The cause of the exception.
	 */
	public DAOException(Throwable ex) {
		super(ex);
	}

}
