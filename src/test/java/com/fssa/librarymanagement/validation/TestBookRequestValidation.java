/**
 * 
 */
package com.fssa.librarymanagement.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * 
 */
class TestBookRequestValidation {
	private final BookRequestValidator bookRequestValidator = new BookRequestValidator();

	@Test
	void testValidTitle() {
		String validTitle = "Sample Title";
		assertDoesNotThrow(() -> bookRequestValidator.validateTitle(validTitle));
	}

	@Test
	void testInvalidTitle() {
		String invalidTitle = "";
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidTitle_Null() {
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateTitle(null));
		assertEquals("Book title cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidTitle_Format() {
		String invalidTitle = "Core Java 1";
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateTitle(invalidTitle));
		assertEquals("Book title cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidAuthor() {
		String validAuthor = "Sample Author";
		assertDoesNotThrow(() -> bookRequestValidator.validateAuthor(validAuthor));
	}

	@Test
	void testInvalidAuthor_Empty() {
		String invalidAuthor = "";
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidAuthor_Null() {
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateAuthor(null));
		assertEquals("Book author cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidAuthor_Format() {
		String invalidAuthor = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateAuthor(invalidAuthor));
		assertEquals("Book author cannot contain Numbers", result.getMessage());
	}

	@Test
	void testValidDescription() {
		String validDescription = "Sample book description.";
		assertDoesNotThrow(() -> bookRequestValidator.validateDescription(validDescription));
	}

	@Test
	void testInvalidDescription_Empty() {
		String invalidDescription = "";
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateDescription(invalidDescription));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidDescription_Null() {
		ValidationException result = assertThrows(ValidationException.class,
				() -> bookRequestValidator.validateDescription(null));
		assertEquals("Book description cannot be empty", result.getMessage());
	}

}
