/**
 * 
 */
package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.constants.BookConstants;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.BookRequest;

/**
 * A utility class for validating book requests.
 * 
 * @author KishorMuruganandham
 * 
 */
public class BookRequestValidator {

	private BookRequest bookRequest; // The book request object to be validated

	/**
	 * Constructs a BookRequestValidator instance with a specific book to validate.
	 *
	 * @param book The Book object to be validated.
	 */
	public BookRequestValidator(BookRequest bookRequest) {
		this.bookRequest = bookRequest;
	}

	/**
	 * Constructs a BookRequestValidator instance without a specific book. This
	 * constructor can be used when validating book request with individual
	 * validation methods.
	 */
	public BookRequestValidator() {

	}

	/**
	 * Validates all Book Request attributes.
	 *
	 * @throws ValidationException If any validation fails
	 */
	public void validateAll() throws ValidationException {
		if (validateTitle(bookRequest.getBookName()) && validateAuthor(bookRequest.getAuthorName())) {
			validateDescription(bookRequest.getDescription());
		}
	}

	/**
	 * Validates the book title.
	 *
	 * @param title The title to validate
	 * @return true if the title is valid
	 * @throws ValidationException If the title is empty
	 */
	public boolean validateTitle(String title) throws ValidationException {
		if (title == null) {
			throw new ValidationException(BookConstants.BOOK_TITLE_CANNOT_BE_EMPTY);
		}
		title = title.trim();
		if (title.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_TITLE_CANNOT_BE_EMPTY);
		}
		if (title.matches(BookConstants.DIGITS_REGEX)) {
			throw new ValidationException(BookConstants.BOOK_TITLE_CANNOT_CONTAIN_NUMBERS);
		}
		return true;
	}

	/**
	 * Validates the book author.
	 *
	 * @param author The author to validate
	 * @return true if author is valid
	 * @throws ValidationException If author is empty
	 */
	public boolean validateAuthor(String author) throws ValidationException {
		if (author == null) {
			throw new ValidationException(BookConstants.BOOK_AUTHOR_CANNOT_BE_EMPTY);
		}
		author = author.trim();
		if (author.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_AUTHOR_CANNOT_BE_EMPTY);
		}
		if (author.matches(BookConstants.DIGITS_REGEX)) {
			throw new ValidationException(BookConstants.BOOK_AUTHOR_CANNOT_CONTAIN_NUMBERS);
		}
		return true;
	}

	/**
	 * Validates the book description.
	 *
	 * @param description The description to validate
	 * @return true if the description is valid
	 * @throws ValidationException If the description is empty
	 */
	public boolean validateDescription(String description) throws ValidationException {
		if (description == null) {
			throw new ValidationException(BookConstants.BOOK_DESCRIPTION_CANNOT_BE_EMPTY);
		}
		description = description.trim();
		if (description.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_DESCRIPTION_CANNOT_BE_EMPTY);
		}

		return true;
	}

}
