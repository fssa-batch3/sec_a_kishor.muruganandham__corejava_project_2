package com.fssa.librarymanagement.constants;

/**
 * This class holds constant error messages that are used throughout the library management system.
 * It provides predefined error messages related to various operations and scenarios in the system.
 */
public class ErrorMessageConstants {

	// Error messages related to borrowing and returning books
	public static final String BOOK_ALREADY_BORROWED = "This Book has already been borrowed by you";
	public static final String NO_AVAILABLE_COPIES = "No available copies of the book.";
	public static final String BORROW_LIMIT_EXCEEDED = "Borrow limit exceeded for the user.";
	public static final String FAILED_TO_RETURN_BOOK = "Failed to return the book.";
	public static final String FAILED_TO_BORROW_BOOK = "Failed to borrow the book.";

	// Error messages related to book operations
	public static final String BOOK_UPDATE_FAILED = "Failed to update the book details.";
	public static final String BOOK_ALREADY_EXISTS = "Book already exists.";
	public static final String FAILED_TO_CREATE_BOOK = "Failed to create a new book.";
	public static final String BOOK_NOT_FOUND = "Book not found.";

	// Error messages related to user operations
	public static final String USER_ALREADY_EXISTS = "User already exists.";
	public static final String REGISTRATION_FAILED = "User registration failed.";
	public static final String PASSWORD_MISMATCH = "Password mismatch.";
	public static final String FAILED_TO_UPDATE_USER = "Failed to update user details.";

	// Error message related to user authentication
	public static final String USER_NOT_EXISTS = "User does not exist with the given email and password.";

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private ErrorMessageConstants() {
		// Private constructor to prevent instantiation
	}
}
