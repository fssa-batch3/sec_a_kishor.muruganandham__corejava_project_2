package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * A utility class for validating ratings.
 *
 * @author KishorMuruganandham
 */
public class RatingValidator {

	/**
	 * Constructs a RatingValidator instance.
	 */
	public RatingValidator() {
		// Default Constructor
	}

	/**
	 * Validates a rating value to ensure it falls within the range of 0 to 5.
	 *
	 * @param rating The rating value to validate
	 * @throws ValidationException If the rating is outside the valid range (0 to 5)
	 */
	public void validateRange(int rating) throws ValidationException {
		if (rating < 0 || rating > 5) {
			throw new ValidationException("Rating must be between 0 and 5.");
		}
	}
}
