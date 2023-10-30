package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.dao.CommentDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.validation.CommentValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * The CommentService class is responsible for managing comments in a library management system.
 * It provides methods for creating, updating, deleting, and listing comments.
 *
 * @author KishorMuruganandham
 */

public class CommentService {

	private final CommentDAO commentDAO = new CommentDAO();

	/**
	 * Constructs a new CommentService instance.
	 */

	public CommentService() {
		// Default Constructor
	}

	/**
	 * Creates a new comment and validates it before storing it in the database.
	 *
	 * @param comment The comment to be created.
	 * @return The created comment.
	 * @throws ServiceException If an error occurs during the service operation.
	 */

	public Comment createComment(Comment comment) throws ServiceException {
		BorrowService borrowService = new BorrowService();
		try {

			CommentValidator commentValidator = new CommentValidator();
			commentValidator.validateDescription(comment.getDescription());
			List<Borrow> borrows = borrowService.getBorrowsByUser(comment.getUser().getUserId());

			boolean bookBorrowed = false;

			for (Borrow borrow : borrows) {
				if (borrow.getBook().getBookId() == comment.getBook().getBookId()) {
					// Check if the borrow date is greater than one day
					LocalDateTime borrowDate = borrow.getBorrowDate();
					LocalDateTime now = LocalDateTime.now();
					if (ChronoUnit.HOURS.between(borrowDate, now) > 5) {
						bookBorrowed = true;
						break;
					}
				}
			}

			if (bookBorrowed) {
				comment.setTrusted(true);
			}
			comment.setCreatedAt(LocalDateTime.now());
			return commentDAO.createComment(comment);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing comment with new information.
	 *
	 * @param comment The comment to be updated.
	 * @return true if the comment was successfully updated; false otherwise.
	 * @throws ServiceException If an error occurs during the service operation.
	 */

	public boolean updateComment(Comment comment) throws ServiceException {
		try {

			CommentValidator commentValidator = new CommentValidator();
			commentValidator.validateDescription(comment.getDescription());
			comment.setEditedAt(LocalDateTime.now());
			return commentDAO.updateComment(comment);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Deletes a comment by its comment ID.
	 *
	 * @param commentId The unique identifier of the comment to be deleted.
	 * @return true if the comment was successfully deleted; false otherwise.
	 * @throws ServiceException If an error occurs during the service operation.
	 */

	public boolean deleteComment(int commentId) throws ServiceException {
		try {

			return commentDAO.deleteComment(commentId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Lists all comments associated with a specific book.
	 *
	 * @param bookId The unique identifier of the book.
	 * @return A list of comments related to the book.
	 * @throws ServiceException If an error occurs during the service operation.
	 */

	public List<Comment> listCommentsByBook(int bookId) throws ServiceException {
		try {

			return commentDAO.listCommentByBook(bookId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Lists all comments in the library management system.
	 *
	 * @return A list of all comments.
	 * @throws ServiceException If an error occurs during the service operation.
	 */

	public List<Comment> listAllComments() throws ServiceException {
		try {
			return commentDAO.listAllComments();

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
