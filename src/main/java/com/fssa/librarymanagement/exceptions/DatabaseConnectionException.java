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
     * Constructs a new DatabaseConnectionException with the specified detail message.
     *
     * @param msg The detail message.
     */
    public DatabaseConnectionException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message 
     * and cause of the exception.
     *
     * @param msg The detail message.
     * @param ex The cause of the exception.
     */
    public DatabaseConnectionException(String msg, Throwable ex) {
        super(msg, ex);
    }

    /**
     * Constructs a new DatabaseConnectionException with the cause
     * of the exception.
     *
     * @param ex The cause of the exception.
     */
    public DatabaseConnectionException(Throwable ex) {
        super(ex);
    }
}
