package com.fssa.librarymanagement.constants;

public class ErrorMessageConstants {

	public static final String BOOK_ALREADY_BORROWED = "This Book has been already borrowed by you";
	public static final String INVALID_BORROW_DATE = "Borrow Details are not Valid";
	public static final String NO_AVAILABLE_COPIES = "No available copies of the book.";
	public static final String BORROW_LIMIT_EXCEEDED = "Borrow limit exceeded for the user.";
	public static final String FAILED_TO_UPDATE_COPIES = "Failed to Update Number of copies in book";
	public static final String RETURN_DATE_INVALID = "Return date is not Valid";
	public static final String FAILED_TO_RETURN_BOOK = "Failed to return book.";
	public static final String FAILED_TO_GET_BORROW_DETAILS = "Failed to Get Borrow Details";
	public static final String FAILED_TO_GET_BORROW_LIST = "Failed to get Borrow List";
	public static final String FAILED_TO_BORROW_BOOK = "Failed to borrow book.";
	public static final String BOOK_UPDATE_FAILED = "Book Update Failed";
	public static final String FAILED_TO_GET_BOOK_LIST = "Failed to get book list";
	public static final String FAILED_TO_GET_BOOK_DETAILS = "Failed to get book details";
	public static final String BOOK_ALREADY_EXISTS = "Book already exists";
	public static final String FAILED_TO_CREATE_BOOK = "Failed to create book";
	public static final String FAILED_TO_ADD_BOOK = "Failed to add book";
	public static final String BOOK_NOT_FOUND = "Book not found";
	public static final String FAILED_TO_UPDATE_BOOK = "Failed to Update Book";
	public static final String USER_ALREADY_EXISTS = "User Already Exists";
	public static final String REGISTRATION_FAILED = "Registration Failed";
	public static final String INVALID_USER = "Invalid User";
	public static final String PASSWORD_MISMATCH = "Password Mismatch";
	public static final String LOGIN_FAILED = "Login Failed";
	public static final String FAILED_TO_RETRIEVE_USER_LIST = "Failed to Retrieve User List.";
	public static final String FAILED_TO_UPDATE_USER = "Failed to Update User";
	public static final String FAILED_TO_DELETE_USER = "Failed to Delete User";
	public static final String USER_NOT_EXISTS = "User not exists";

	private ErrorMessageConstants() {
		// Private constructor to prevent instantiation
	}

}
