package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Book;

public class BookValidator {

	private Book book;

	public BookValidator(Book book) throws ValidationException {
		this.book = book;
	}

	public BookValidator() {

	}

	public void validateAll() throws ValidationException {
		if (validateTitle(book.getTitle()) && validateAuthor(book.getAuthor()) && validatePublisher(book.getPublisher()) && validateGenre(book.getGenre()) && validateLanguage(book.getLanguage()) && validateDescription(book.getDescription()) && validateTotalCopies(book.getTotalCopies()) && validateAvailableCopies(book.getAvailableCopies(), book.getTotalCopies())) {
			validateLoanedCopies(book.getLoanedCopies(), book.getTotalCopies());
		}
	}

	public boolean validateTitle(String title) throws ValidationException {
		if (title == null || title.isEmpty()) {
			throw new ValidationException("Book title cannot be empty");
		}
		return true;
	}

	public boolean validateAuthor(String author) throws ValidationException {
		if (author == null || author.isEmpty()) {
			throw new ValidationException("Book author cannot be empty");
		}
		return true;
	}

	public boolean validatePublisher(String publisher) throws ValidationException {
		if (publisher == null || publisher.isEmpty()) {
			throw new ValidationException("Book publisher cannot be empty");
		}
		return true;
	}

	public boolean validateGenre(String genre) throws ValidationException {
		if (genre == null || genre.isEmpty()) {
			throw new ValidationException("Book genre cannot be empty");
		}
		return true;
	}

	public boolean validateLanguage(String language) throws ValidationException {
		if (language == null || language.isEmpty()) {
			throw new ValidationException("Book language cannot be empty");
		}
		return true;
	}

	public boolean validateDescription(String description) throws ValidationException {
		if (description == null || description.isEmpty()) {
			throw new ValidationException("Book description cannot be empty");
		}
		return true;
	}

	public boolean validateTotalCopies(int totalCopies) throws ValidationException {
		if (totalCopies <= 0) {
			throw new ValidationException("Total copies should be greater than zero");
		}
		return true;
	}

	public boolean validateAvailableCopies(int availableCopies, int totalCopies) throws ValidationException {
		if (availableCopies < 0 || availableCopies > totalCopies) {
			throw new ValidationException("Invalid number of available copies");
		}
		return true;
	}

	public boolean validateLoanedCopies(int loanedCopies, int totalCopies) throws ValidationException {
		if (loanedCopies < 0 || loanedCopies > totalCopies) {
			throw new ValidationException("Invalid number of loaned copies");
		}
		return true;
	}
}
