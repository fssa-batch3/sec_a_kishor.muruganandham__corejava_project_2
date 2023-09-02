package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBookValidation {
	private final BookValidator bookValidator = new BookValidator();

	@Test
	void testValidTitle() {
		String validTitle = "Sample Title";
		assertDoesNotThrow(() -> bookValidator.validateTitle(validTitle));
	}

	@Test
	void testInvalidTitle() {
		String invalidTitle = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalid_Null() {
		ValidationException result = assertThrows(ValidationException.class, () -> bookValidator.validateTitle(null));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidTitle_Format() {
		String invalidTitle = "Core Java 1";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidAuthor() {
		String validAuthor = "Sample Author";
		assertDoesNotThrow(() -> bookValidator.validateAuthor(validAuthor));
	}

	@Test
	void testInvalidAuthor_Empty() {
		String invalidAuthor = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidAuthor_Null() {
		ValidationException result = assertThrows(ValidationException.class, () -> bookValidator.validateAuthor(null));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidAuthor_Format() {
		String invalidAuthor = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidPublisher() {
		String validPublisher = "Sample Publisher";
		assertDoesNotThrow(() -> bookValidator.validatePublisher(validPublisher));
	}

	@Test
	void testInvalidPublisher_Empty() {
		String invalidPublisher = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(invalidPublisher));
		assertEquals("Book publisher cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidPublisher_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(null));
		assertEquals("Book publisher cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidPublisher_Format() {
		String invalidPublisher = "Bharathi Publishers 12";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validatePublisher(invalidPublisher));
		assertEquals("Book publisher cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidGenre() {
		String validGenre = "Fiction";
		assertDoesNotThrow(() -> bookValidator.validateGenre(validGenre));
	}

	@Test
	void testInvalidGenre_Empty() {
		String invalidGenre = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateGenre(invalidGenre));
		assertEquals("Book genre cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidGenre_Null() {
		ValidationException result = assertThrows(ValidationException.class, () -> bookValidator.validateGenre(null));
		assertEquals("Book genre cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidGenre_Format() {
		String invalidGenre = "Tech5";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateGenre(invalidGenre));
		assertEquals("Book genre cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidLanguage() {
		String validLanguage = "English";
		assertDoesNotThrow(() -> bookValidator.validateLanguage(validLanguage));
	}

	@Test
	void testInvalidLanguage_Empty() {
		String invalidLanguage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(invalidLanguage));
		assertEquals("Book language cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidLanguage_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(null));
		assertEquals("Book language cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidLanguage_Format() {
		String invalidLanguage = "English2";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLanguage(invalidLanguage));
		assertEquals("Book language cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidDescription() {
		String validDescription = "Sample book description.";
		assertDoesNotThrow(() -> bookValidator.validateDescription(validDescription));
	}

	@Test
	void testInvalidDescription_Empty() {
		String invalidDescription = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateDescription(invalidDescription));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidDescription_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateDescription(null));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

	@Test
	void testValidCoverImageURL() {
		String validProfileImage = "https://example.com/";
		assertDoesNotThrow(() -> bookValidator.validateCoverImage(validProfileImage));
	}

	@Test
	void testInvalidCoverImageURL_Empty() {
		String emptyProfileImage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(emptyProfileImage));
		assertEquals("Cover image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidCoverImageURL_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(null));
		assertEquals("Cover image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidCoverImageURL_Format() {
		String invalidProfileImage = "kishor.com";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateCoverImage(invalidProfileImage));
		assertEquals("Invalid Cover image URL. URL should be in the format 'http://www.example.com/index.html'",
		             result.getMessage());
	}

	@Test
	void testValidTotalCopies() {
		int validTotalCopies = 5;
		assertDoesNotThrow(() -> bookValidator.validateTotalCopies(validTotalCopies));
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
		assertDoesNotThrow(() -> bookValidator.validateAvailableCopies(validAvailableCopies, validTotalCopies));
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
	void testInvalidAvailableCopies_LessThanZero() {
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
		assertDoesNotThrow(() -> bookValidator.validateLoanedCopies(validLoanedCopies, validTotalCopies));
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

	@Test
	void testInvalidLoanedCopies_LessThanZero() {
		int invalidLoanedCopies = -1;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLoanedCopies(invalidLoanedCopies,
		                                                                                   validTotalCopies));
		assertEquals("Loaned copies cannot be less than 0", result.getMessage());
	}
}
