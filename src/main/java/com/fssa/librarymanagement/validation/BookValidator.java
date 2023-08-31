package com.fssa.librarymanagement.validation;

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
	 * Constructs a BookValidator instance without a specific book.
	 * This constructor can be used when validating books with individual validation methods.
	 */
	public BookValidator() {
		// Default constructor
	}

	/**
	 * Validates all Book attributes.
	 *
	 * @return true if all attributes are valid, false otherwise
	 * @throws ValidationException If any validation fails
	 */
	public boolean validateAll() throws ValidationException {
		if (validateTitle(book.getTitle()) && validateAuthor(book.getAuthor()) && validatePublisher(book.getPublisher())
				&& validateGenre(book.getGenre()) && validateLanguage(book.getLanguage())
				&& validateDescription(book.getDescription()) && validateTotalCopies(book.getTotalCopies())
				&& validateAvailableCopies(book.getAvailableCopies(), book.getTotalCopies())) {
			validateLoanedCopies(book.getLoanedCopies(), book.getTotalCopies());
			return true;
		}
		return false;
	}

	/**
	 * Validates the book title.
	 *
	 * @param title The title to validate
	 * @return true if the title is valid
	 * @throws ValidationException If the title is empty
	 */
	public boolean validateTitle(String title) throws ValidationException {
		if (title == null || title.isEmpty()) {
			throw new ValidationException("Book title cannot be empty");
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
		if (author == null || author.isEmpty()) {
			throw new ValidationException("Book author cannot be empty");
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
		if (publisher == null || publisher.isEmpty()) {
			throw new ValidationException("Book publisher cannot be empty");
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
		if (genre == null || genre.isEmpty()) {
			throw new ValidationException("Book genre cannot be empty");
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
		if (language == null || language.isEmpty()) {
			throw new ValidationException("Book language cannot be empty");
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
		if (description == null || description.isEmpty()) {
			throw new ValidationException("Book description cannot be empty");
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
			throw new ValidationException("Total copies should be greater than zero");
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
		if (availableCopies < 0 || availableCopies > totalCopies) {
			throw new ValidationException("Invalid number of available copies");
		}
		return true;
	}

	/**
	 * Validates the number of loaned copies.
	 *
	 * @param loanedCopies The loaned copies to validate
	 * @param totalCopies  The total copies for comparison
	 * @return true if loaned copies are valid
	 * @throws ValidationException If loaned, copies are negative or exceed total copies
	 */
	public boolean validateLoanedCopies(int loanedCopies, int totalCopies) throws ValidationException {
		if (loanedCopies < 0 || loanedCopies > totalCopies) {
			throw new ValidationException("Invalid number of loaned copies");
		}
		return true;
	}
}
