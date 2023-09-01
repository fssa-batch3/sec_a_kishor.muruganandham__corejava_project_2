package com.fssa.librarymanagement.constants;

/**
 * This class holds constant values related to borrowing books in the library management system.
 * It provides predefined values, Error Messages and Success messages.
 */
public class BorrowConstants {

	public static final String THIS_BOOK_HAS_ALREADY_BEEN_BORROWED_BY_YOU = "This Book has already been borrowed by " +
			"you";
	public static final String NO_AVAILABLE_COPIES_OF_THE_BOOK = "No available copies of the book.";
	public static final String BORROW_LIMIT_EXCEEDED_FOR_THE_USER = "Borrow limit exceeded for the user.";
	public static final String BORROWS_NOT_FOUND = "Borrows not found.";
	/**
	 * The predefined fine amount for late book returns.
	 */
	public static final double FINE_AMOUNT = 10.0;
	/**
	 * The maximum number of books that a user can borrow.
	 */
	public static final int BORROW_LIMIT = 5;

	private BorrowConstants() {
		// Private constructor to prevent instantiation
	}


}
