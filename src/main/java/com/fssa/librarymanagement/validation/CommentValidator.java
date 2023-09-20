/**
 * 
 */
package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Comment;

/**
 * 
 */
public class CommentValidator {

	private Comment comment;

	public CommentValidator(Comment comment) {
		this.comment = comment;
	}

	public CommentValidator() {
		// Default Constructor
	}
	
    public void validateAll() throws ValidationException{
        validateDescription(comment);
    }

    public void validateDescription(Comment comment) throws ValidationException {
        String description = comment.getDescription();
        if (description == null || description.trim().isEmpty()) {
            throw new ValidationException("Description cannot be empty.");
        } else if (description.length() > 230) {
        	throw new ValidationException("Description cannot exceed 230 characters.");
        }
    }
}
