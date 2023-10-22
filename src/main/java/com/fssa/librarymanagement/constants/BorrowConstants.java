package com.fssa.librarymanagement.constants;

/**
 * This class holds constant values related to borrowing books in the library management system.
 * It provides predefined values, Error Messages and Success messages.
 */
public class BorrowConstants {

	/**
	 * The predefined fine amount for late book returns.
	 */
	public static final int FINE_AMOUNT = 10;
	/**
	 * The maximum number of books that a user can borrow.
	 */
	public static final int BORROW_LIMIT = 5;
	public static final String THIS_BOOK_HAS_ALREADY_BEEN_BORROWED_BY_YOU = "This Book has already been borrowed by " +
			"you";
	public static final String NO_AVAILABLE_COPIES_OF_THE_BOOK = "No available copies of the book.";
	public static final String BORROW_LIMIT_EXCEEDED_FOR_THE_USER = "Borrow limit exceeded for the user.";
	public static final String BORROWS_NOT_FOUND = "Borrows not found.";

	// SQL query prefixes
	public static final String SELECT_QUERY_PREFIX = "SELECT user_id, book_id, borrow_date, due_date, return_date FROM borrows ";
	public static final String JOIN_QUERY = "SELECT b.*, u.user_name, u.user_id, u.email_id, u.profile_image,"+
			" bk.book_id, bk.title, bk.cover_image FROM borrows b " +
			"JOIN users u ON b.user_id = u.user_id " +
			"JOIN books bk ON b.book_id = bk.book_id ";

	private BorrowConstants() {
		// Private constructor to prevent instantiation
	}


}
