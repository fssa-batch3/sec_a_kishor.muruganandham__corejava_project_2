/**
 * 
 */
package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * 
 */
public class CommentValidator {

	public CommentValidator() {
		// Default Constructor
	}

	public void validateDescription(String description) throws ValidationException {
		if (description == null || description.trim().isEmpty()) {
			throw new ValidationException("Description cannot be empty.");
		} else if (description.length() > 230) {
			throw new ValidationException("Description cannot exceed 230 characters.");
		}
	}
}
