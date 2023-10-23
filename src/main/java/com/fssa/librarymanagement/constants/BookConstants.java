package com.fssa.librarymanagement.constants;

/**
 * This class holds constant values related to books in the library management
 * system. It provides predefined values, Error Messages and Success messages.
 */
public class BookConstants {

	public static final String BOOK_NOT_FOUND = "Book not found.";
	public static final String BOOK_TITLE_CANNOT_BE_EMPTY = "Book title cannot be empty";
	public static final String BOOK_TITLE_CANNOT_CONTAIN_NUMBERS = "Book title cannot contain Numbers";
	public static final String BOOK_AUTHOR_CANNOT_BE_EMPTY = "Book author cannot be empty";
	public static final String BOOK_AUTHOR_CANNOT_CONTAIN_NUMBERS = "Book author cannot contain Numbers";
	public static final String BOOK_PUBLISHER_CANNOT_BE_EMPTY = "Book publisher cannot be empty";
	public static final String BOOK_PUBLISHER_CANNOT_CONTAIN_NUMBERS = "Book publisher cannot contain Numbers";
	public static final String BOOK_GENRE_CANNOT_BE_EMPTY = "Book genre cannot be empty";
	public static final String BOOK_GENRE_CANNOT_CONTAIN_NUMBERS = "Book genre cannot contain Numbers";
	public static final String BOOK_LANGUAGE_CANNOT_BE_EMPTY = "Book language cannot be empty";
	public static final String BOOK_LANGUAGE_CANNOT_CONTAIN_NUMBERS = "Book language cannot contain Numbers";
	public static final String BOOK_DESCRIPTION_CANNOT_BE_EMPTY = "Book description cannot be empty";
	public static final String TOTAL_COPIES_SHOULD_BE_GREATER_THAN_ZERO = "Total copies should be greater than zero";
	public static final String AVAILABE_COPIES_CANNOT_BE_LESS_THAN_0 = "Availabe copies cannot be less than 0";
	public static final String AVAILABLE_COPIES_CANNOT_BE_GREATER_THAN_TOTAL_COPIES = "Available copies cannot be "
			+ "greater than total copies";
	public static final String LOANED_COPIES_CANNOT_BE_LESS_THAN_0 = "Loaned copies cannot be less than 0";
	public static final String LOANED_COPIES_CANNOT_BE_GREATER_THAN_TOTAL_COPIES = "Loaned copies cannot be greater "
			+ "than total copies";
	public static final String COVER_IMAGE_URL_CANNOT_BE_EMPTY = "Cover image URL cannot be empty";
	public static final String INVALID_COVER_IMAGE_URL = "Invalid Cover image URL. URL should be in the format "
			+ "'http://www.example.com/index.html'";
	public static final String DIGITS_REGEX = ".*[0-9].*";

	private BookConstants() {
		// Private constructor to prevent instantiation
	}

}
