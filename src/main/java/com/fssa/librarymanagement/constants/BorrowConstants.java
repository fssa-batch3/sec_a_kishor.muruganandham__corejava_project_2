package com.fssa.librarymanagement.constants;

/**
 * This class holds constant values related to borrowing books in the library management system.
 * It provides predefined values such as fine amount and borrow limit.
 */
public class BorrowConstants {

	/**
	 * The predefined fine amount for late book returns.
	 */
	public static final double FINE_AMOUNT = 10.0;

	/**
	 * The maximum number of books that a user can borrow.
	 */
	public static final int BORROW_LIMIT = 5;

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private BorrowConstants() {
		// Private constructor to prevent instantiation
	}
}
