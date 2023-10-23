/**
 * 
 */
package com.fssa.librarymanagement.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Rating;

/**
 * 
 */
class TestRatingValidation {

	private RatingValidator ratingValidator = new RatingValidator();
	private Rating rating = new Rating();

	@Test
	void testValidRating() {
		rating.setRating(4);
		assertDoesNotThrow(() -> ratingValidator.validateRange(rating.getRating()));
	}

	@Test
	void testInValidRating_Min() {
		rating.setRating(-3);
		ValidationException result = assertThrows(ValidationException.class,
				() -> ratingValidator.validateRange(rating.getRating()));
		assertEquals("Rating must be between 0 and 5.", result.getMessage());
	}

	@Test
	void testInValidRating_Max() {
		rating.setRating(7);
		ValidationException result = assertThrows(ValidationException.class,
				() -> ratingValidator.validateRange(rating.getRating()));
		assertEquals("Rating must be between 0 and 5.", result.getMessage());
	}
}
