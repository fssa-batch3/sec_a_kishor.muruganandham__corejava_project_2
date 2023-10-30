/**
 *
 */
package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class TestRatingValidation {

	private final RatingValidator ratingValidator = new RatingValidator();
	private final Rating rating = new Rating();

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
