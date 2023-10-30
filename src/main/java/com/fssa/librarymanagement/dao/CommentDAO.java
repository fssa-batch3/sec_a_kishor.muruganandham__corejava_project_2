package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.constants.CommentConstants;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildCommentFromResultSet;

/**
 * Data Access Object (DAO) class for handling comments related database operations.
 */
public class CommentDAO {

	/**
	 * Constructs a new CommentDAO object for performing database operations related to comments.
	 */
	public CommentDAO() {
		// Default constructor
	}

	/**
	 * Create a new comment.
	 *
	 * @param comment The comment object to be created.
	 * @return The created comment.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public Comment createComment(Comment comment) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(CommentConstants.INSERT_COMMENT_QUERY,
		                                                         Statement.RETURN_GENERATED_KEYS)) {

			pst.setInt(1, comment.getUser().getUserId());
			pst.setInt(2, comment.getBook().getBookId());
			pst.setString(3, comment.getDescription());
			pst.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));
			pst.setBoolean(5, comment.isTrusted());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1);
					comment.setCommentId(generatedId); // Assuming Comment class has a setCommentId method
				}
			}

			return comment;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Update an existing comment.
	 *
	 * @param comment The comment object to be updated.
	 * @return true if the comment is successfully updated, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public boolean updateComment(Comment comment) throws DAOException {
		boolean hasUpdated = false;
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(CommentConstants.UPDATE_COMMENT_QUERY)) {

			pst.setString(1, comment.getDescription());
			pst.setTimestamp(2, Timestamp.valueOf(comment.getEditedAt()));
			pst.setInt(3, comment.getCommentId());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				hasUpdated = true;
			}
			return hasUpdated;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Delete a comment by its ID.
	 *
	 * @param commentId The ID of the comment to be deleted.
	 * @return true if the comment is successfully deleted, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public boolean deleteComment(int commentId) throws DAOException {
		boolean isDeleted = false;
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(CommentConstants.DELETE_COMMENT_QUERY)) {

			pst.setInt(1, commentId);

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				isDeleted = true;
			}
			return isDeleted;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * List comments associated with a specific book.
	 *
	 * @param bookId The ID of the book for which comments are to be listed.
	 * @return A list of comments related to the specified book.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public List<Comment> listCommentByBook(int bookId) throws DAOException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(CommentConstants.LIST_COMMENTS_BY_BOOK_QUERY)) {

			pst.setInt(1, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Comment comment = buildCommentFromResultSet(rs);
					comments.add(comment);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return comments;
	}

	/**
	 * List all comments in the database.
	 *
	 * @return A list of all comments available in the database.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public List<Comment> listAllComments() throws DAOException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(CommentConstants.LIST_ALL_COMMENTS_QUERY)) {

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Comment comment = buildCommentFromResultSet(rs);
					comments.add(comment);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return comments;
	}

}
