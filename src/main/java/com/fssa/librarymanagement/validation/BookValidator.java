package com.fssa.librarymanagement.validation;

import java.net.MalformedURLException;
import java.net.URL;

import com.fssa.librarymanagement.constants.BookConstants;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Book;

/**
 * This class provides Validations for all Book data.
 *
 * @author KishorMuruganandham
 */
public class BookValidator {

	private Book book; // The book object to be validated

	/**
	 * Constructs a BookValidator instance with a specific book to validate.
	 *
	 * @param book The Book object to be validated.
	 */
	public BookValidator(Book book) {
		this.book = book;
	}

	/**
	 * Constructs a BookValidator instance without a specific book. This constructor
	 * can be used when validating books with individual validation methods.
	 */
	public BookValidator() {
		// Default constructor
	}

	/**
	 * Validates all Book attributes.
	 *
	 * @throws ValidationException If any validation fails
	 */
	public void validateAll() throws ValidationException {
		if (validateTitle(book.getTitle()) && validateAuthor(book.getAuthor()) && validatePublisher(book.getPublisher())
				&& validateGenre(book.getGenre()) && validateLanguage(book.getLanguage())
				&& validateDescription(book.getDescription()) && validateTotalCopies(book.getTotalCopies())
				&& validateAvailableCopies(book.getAvailableCopies(), book.getTotalCopies())) {
			validateLoanedCopies(book.getLoanedCopies(), book.getTotalCopies());

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
	 * Validates the book publisher.
	 *
	 * @param publisher The publisher to validate
	 * @return true if publisher is valid
	 * @throws ValidationException If publisher is empty
	 */
	public boolean validatePublisher(String publisher) throws ValidationException {
		if (publisher == null) {
			throw new ValidationException(BookConstants.BOOK_PUBLISHER_CANNOT_BE_EMPTY);
		}
		publisher = publisher.trim();
		if (publisher.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_PUBLISHER_CANNOT_BE_EMPTY);
		}
		if (publisher.matches(BookConstants.DIGITS_REGEX)) {
			throw new ValidationException(BookConstants.BOOK_PUBLISHER_CANNOT_CONTAIN_NUMBERS);
		}
		return true;
	}

	/**
	 * Validates the book genre.
	 *
	 * @param genre The genre to validate
	 * @return true if genre is valid
	 * @throws ValidationException If the genre is empty
	 */
	public boolean validateGenre(String genre) throws ValidationException {
		if (genre == null) {
			throw new ValidationException(BookConstants.BOOK_GENRE_CANNOT_BE_EMPTY);
		}
		genre = genre.trim();
		if (genre.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_GENRE_CANNOT_BE_EMPTY);
		}
		if (genre.matches(BookConstants.DIGITS_REGEX)) {
			throw new ValidationException(BookConstants.BOOK_GENRE_CANNOT_CONTAIN_NUMBERS);
		}
		return true;
	}

	/**
	 * Validates the book language.
	 *
	 * @param language The language to validate
	 * @return true if language is valid
	 * @throws ValidationException If language is empty
	 */
	public boolean validateLanguage(String language) throws ValidationException {
		if (language == null) {
			throw new ValidationException(BookConstants.BOOK_LANGUAGE_CANNOT_BE_EMPTY);
		}
		language = language.trim();
		if (language.isEmpty()) {
			throw new ValidationException(BookConstants.BOOK_LANGUAGE_CANNOT_BE_EMPTY);
		}
		if (language.matches(BookConstants.DIGITS_REGEX)) {
			throw new ValidationException(BookConstants.BOOK_LANGUAGE_CANNOT_CONTAIN_NUMBERS);
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

	/**
	 * Validates a profile image URL.
	 *
	 * @param coverImage The profile image URL to validate
	 * @return true if profile image URL is valid
	 * @throws ValidationException If profile image URL is empty or invalid
	 */
	public boolean validateCoverImage(String coverImage) throws ValidationException {
		if (coverImage == null) {
			throw new ValidationException(BookConstants.COVER_IMAGE_URL_CANNOT_BE_EMPTY);
		}
		coverImage = coverImage.trim();
		if (coverImage.isEmpty()) {
			throw new ValidationException(BookConstants.COVER_IMAGE_URL_CANNOT_BE_EMPTY);
		}
		try {
			new URL(coverImage);
		} catch (MalformedURLException e) {
			throw new ValidationException(BookConstants.INVALID_COVER_IMAGE_URL);
		}
		return true;
	}

	/**
	 * Validates the total number of copies.
	 *
	 * @param totalCopies The total copies to validate
	 * @return true if total copies are valid
	 * @throws ValidationException If total copies is non-positive
	 */
	public boolean validateTotalCopies(int totalCopies) throws ValidationException {
		if (totalCopies <= 0) {
			throw new ValidationException(BookConstants.TOTAL_COPIES_SHOULD_BE_GREATER_THAN_ZERO);
		}
		return true;
	}

	/**
	 * Validates the number of available copies.
	 *
	 * @param availableCopies The available copies to validate
	 * @param totalCopies     The total copies for comparison
	 * @return true if available copies is valid
	 * @throws ValidationException If available copies is negative or exceeds total
	 *                             copies
	 */
	public boolean validateAvailableCopies(int availableCopies, int totalCopies) throws ValidationException {
		if (availableCopies < 0) {
			throw new ValidationException(BookConstants.AVAILABE_COPIES_CANNOT_BE_LESS_THAN_0);
		}
		if (availableCopies > totalCopies) {
			throw new ValidationException(BookConstants.AVAILABLE_COPIES_CANNOT_BE_GREATER_THAN_TOTAL_COPIES);
		}
		return true;
	}

	/**
	 * Validates the number of loaned copies.
	 *
	 * @param loanedCopies The loaned copies to validate
	 * @param totalCopies  The total copies for comparison
	 * @return true if loaned copies are valid
	 * @throws ValidationException If loaned, copies are negative or exceed total
	 *                             copies
	 */
	public boolean validateLoanedCopies(int loanedCopies, int totalCopies) throws ValidationException {
		if (loanedCopies < 0) {
			throw new ValidationException(BookConstants.LOANED_COPIES_CANNOT_BE_LESS_THAN_0);
		}
		if (loanedCopies > totalCopies) {
			throw new ValidationException(BookConstants.LOANED_COPIES_CANNOT_BE_GREATER_THAN_TOTAL_COPIES);
		}
		return true;
	}

}
