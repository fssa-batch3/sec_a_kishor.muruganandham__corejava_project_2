/**
 * 
 */
package com.fssa.librarymanagement.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Comment;

/**
 * 
 */
class TestCommentValidation {

	private CommentValidator commentValidator = new CommentValidator();
	private Comment comment;

	@BeforeEach
	void setUp() {
		comment = new Comment();
		comment.setDescription("Nice Book!!");
	}

	@Test
	void testValidDescription() {
		String validDescription = "Sample book description.";
		assertDoesNotThrow(() -> commentValidator.validateDescription(validDescription));
	}

	@Test
	void testInvalidDescription_Empty() {
		String invalidDescription = "";
		ValidationException result = assertThrows(ValidationException.class,
				() -> commentValidator.validateDescription(invalidDescription));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	void testInvalidDescription_Null() {
		ValidationException result = assertThrows(ValidationException.class,
				() -> commentValidator.validateDescription(null));
		assertEquals("Description cannot be empty.", result.getMessage());
	}

	@Test
	void testInvalidDescription_ExceedLimit() {
		String InvalidDescription = "James Clear's 'Atomic Habits' is a transformative masterpiece that dives deep into the science of habits, providing actionable strategies and illuminating stories. This book is a remarkable guide for anyone seeking personal growth and lasting change.";
		ValidationException result = assertThrows(ValidationException.class,
				() -> commentValidator.validateDescription(InvalidDescription));
		assertEquals("Description cannot exceed 230 characters.", result.getMessage());
	}

}
