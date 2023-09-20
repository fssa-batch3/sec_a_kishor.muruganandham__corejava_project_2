/**
 * 
 */
package com.fssa.librarymanagement.service;

import java.util.List;

import com.fssa.librarymanagement.dao.CommentDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
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

	public boolean createComment(Comment comment) throws ServiceException {
		try {
			CommentValidator commentValidator = new CommentValidator();
			commentValidator.validateAll();

			return commentDAO.createComment(comment);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean updateComment(Comment comment) throws ServiceException {
		try {

			CommentValidator commentValidator = new CommentValidator();
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
