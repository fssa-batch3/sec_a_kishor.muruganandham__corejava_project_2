/**
 * 
 */
package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * 
 */
public class RatingValidator {

	public RatingValidator() {
		// Default Constructor
	}

	public void validateRange(int rating) throws ValidationException {
		if (rating < 0 || rating > 5) {
			throw new ValidationException("Rating must be between 0 and 5.");
		}
	}
}
