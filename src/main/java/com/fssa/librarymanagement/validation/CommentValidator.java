package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * A utility class for validating comments.
 * 
 * @author KishorMuruganandham
 * 
 */
public class CommentValidator {

    /**
     * Constructs a CommentValidator instance.
     */
    public CommentValidator() {
        // Default Constructor
    }

    /**
     * Validates a comment description.
     *
     * @param description The description to validate
     * @throws ValidationException If the description is empty or exceeds 230 characters
     */
    public void validateDescription(String description) throws ValidationException {
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("Description cannot be empty.");
        } else if (description.length() > 230) {
            throw new ValidationException("Description cannot exceed 230 characters.");
        }
    }
}
