package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBookValidation {
	private final BookValidator bookValidator = new BookValidator();

	@Test
	void testValidTitle() {
		String validTitle = "Sample Title";
		boolean result = false;
		try {
			result = bookValidator.validateTitle(validTitle);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidTitle() {
		String invalidTitle = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullTitle() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTitle(null));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidTitleFormat() {
		String invalidTitle = "Core Java 1";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidAuthor() {
		String validAuthor = "Sample Author";
		boolean result = false;
		try {
			result = bookValidator.validateAuthor(validAuthor);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidAuthor() {
		String invalidAuthor = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullAuthor() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAuthor(null));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidAuthorFormat() {
		String invalidAuthor = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidPublisher() {
		String validPublisher = "Sample Publisher";
		boolean result = false;
		try {
			result = bookValidator.validatePublisher(validPublisher);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidPublisher() {
		String invalidPublisher = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(invalidPublisher));
		assertEquals("Book publisher cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullPublisher() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(null));
		assertEquals("Book publisher cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidPublisherFormat() {
		String invalidPublisher = "Bharathi Publishers 12";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(invalidPublisher));
		assertEquals("Book publisher cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidGenre() {
		String validGenre = "Fiction";
		boolean result = false;
		try {
			result = bookValidator.validateGenre(validGenre);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidGenre() {
		String invalidGenre = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateGenre(invalidGenre));
		assertEquals("Book genre cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullGenre() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateGenre(null));
		assertEquals("Book genre cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidGenreFormat() {
		String invalidGenre = "Tech5";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateGenre(invalidGenre));
		assertEquals("Book genre cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidLanguage() {
		String validLanguage = "English";
		boolean result = false;
		try {
			result = bookValidator.validateLanguage(validLanguage);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidLanguage() {
		String invalidLanguage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(invalidLanguage));
		assertEquals("Book language cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullLanguage() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(null));
		assertEquals("Book language cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidLanguageFormat() {
		String invalidLanguage = "English2";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(invalidLanguage));
		assertEquals("Book language cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidDescription() {
		String validDescription = "Sample book description.";
		boolean result = false;
		try {
			result = bookValidator.validateDescription(validDescription);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidDescription() {
		String invalidDescription = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateDescription(invalidDescription));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidNullDescription() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateDescription(null));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

	@Test
	void testValidUrl() throws ValidationException {
		String validProfileImage = "https://example.com/";
		boolean result = bookValidator.validateCoverImage(validProfileImage);
		assertTrue(result);
	}

	@Test
	void testInValidUrlEmptyUrl() {
		String emptyProfileImage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(emptyProfileImage));
		assertEquals("Cover image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInValidUrlNullUrl() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(null));
		assertEquals("Cover image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidUrl() {
		String invalidProfileImage = "kishor.com";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(invalidProfileImage));
		assertEquals("Invalid Cover image URL. URL should be in the format 'http://www.example.com/index.html'",
		             result.getMessage());
	}

	@Test
	void testValidTotalCopies() {
		int validTotalCopies = 5;
		boolean result = false;
		try {
			result = bookValidator.validateTotalCopies(validTotalCopies);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidTotalCopies() {
		int invalidTotalCopies = -1;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTotalCopies(invalidTotalCopies));
		assertEquals("Total copies should be greater than zero", result.getMessage());
	}

	@Test
	void testValidAvailableCopies() {
		int validAvailableCopies = 3;
		int validTotalCopies = 5;
		boolean result = false;
		try {
			result = bookValidator.validateAvailableCopies(validAvailableCopies, validTotalCopies);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidAvailableCopies() {
		int invalidAvailableCopies = 6;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAvailableCopies(invalidAvailableCopies,
		                                                                                      validTotalCopies));
		assertEquals("Available copies cannot be greater than total copies", result.getMessage());
	}

	@Test
	void testInvalidAvailableCopiesLessThanZero() {
		int invalidAvailableCopies = -2;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAvailableCopies(invalidAvailableCopies,
		                                                                                      validTotalCopies));
		assertEquals("Availabe copies cannot be less than 0", result.getMessage());
	}

	@Test
	void testValidLoanedCopies() {
		int validLoanedCopies = 2;
		int validTotalCopies = 5;
		boolean result = false;
		try {
			result = bookValidator.validateLoanedCopies(validLoanedCopies, validTotalCopies);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
		assertTrue(result);
	}

	@Test
	void testInvalidLoanedCopiesLessThanZero() {
		int invalidLoanedCopies = -1;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLoanedCopies(invalidLoanedCopies,
		                                                                                   validTotalCopies));
		assertEquals("Loaned copies cannot be less than 0", result.getMessage());
	}

	@Test
	void testInvalidLoanedCopies() {
		int invalidLoanedCopies = 7;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLoanedCopies(invalidLoanedCopies,
		                                                                                   validTotalCopies));
		assertEquals("Loaned copies cannot be greater than total copies", result.getMessage());
	}
}
