/**
 * 
 */
package com.fssa.librarymanagement.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fssa.librarymanagement.dao.CommentDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.validation.CommentValidator;

/**
 * 
 */
public class CommentService {

	private CommentDAO commentDAO = new CommentDAO();

	public CommentService() {
		// Default Constructor
	}

	public Comment createComment(Comment comment) throws ServiceException {
		BorrowService borrowService = new BorrowService();
		try {
			
			CommentValidator commentValidator = new CommentValidator(comment);
			commentValidator.validateAll();
			List<Borrow> borrows = borrowService.getBorrowsByUser(comment.getUser().getUserId());
			
	        boolean bookBorrowed = false;

	        // Iterate through the borrows
	        for (Borrow borrow : borrows) {
	            if (borrow.getBook().getBookId() == comment.getBook().getBookId()) {
	                // Check if the borrow date is greater than one day
	                LocalDateTime borrowDate = borrow.getBorrowDate();
	                LocalDateTime now = LocalDateTime.now();
	                if (ChronoUnit.HOURS.between(borrowDate, now) > 5) {
	                	System.out.println("Borr : " + borrow);
	                    bookBorrowed = true;
	                    break;
	                }
	            }
	        }
	        
	        if(bookBorrowed) {
	        	comment.setTrusted(true);
	        }
			comment.setCreatedAt(LocalDateTime.now());
			return commentDAO.createComment(comment);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean updateComment(Comment comment) throws ServiceException {
		try {

			CommentValidator commentValidator = new CommentValidator(comment);
			commentValidator.validateAll();
			return commentDAO.updateComment(comment);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean deleteComment(int commentId) throws ServiceException {
		try {

			return commentDAO.deleteComment(commentId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Comment> listCommentsByBook(int bookId) throws ServiceException {
		try {

			return commentDAO.listCommentByBook(bookId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Comment> listAllComments() throws ServiceException {
		try {
			return commentDAO.listAllComments();

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
