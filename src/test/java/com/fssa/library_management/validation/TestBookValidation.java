package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
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
		assertEquals("Invalid number of available copies", result.getMessage());
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
	void testInvalidLoanedCopies() {
		int invalidLoanedCopies = -1;
		int validTotalCopies = 5;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> bookValidator.validateLoanedCopies(invalidLoanedCopies,
		                                                                                 validTotalCopies));
		assertEquals("Invalid number of loaned copies", result.getMessage());
	}
}
