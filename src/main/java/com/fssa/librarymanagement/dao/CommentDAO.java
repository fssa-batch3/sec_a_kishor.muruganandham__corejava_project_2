/**
 * 
 */
package com.fssa.librarymanagement.dao;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildCommentFromResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
public class CommentDAO {

	private static final String INSERT_COMMENT_QUERY = "INSERT INTO comments (user_id, book_id, description, created_at, is_trusted) "
			+ "VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_COMMENT_QUERY = "UPDATE comments SET description = ?, edited_at = ?, is_edited = TRUE WHERE comment_id = ?";

	private static final String DELETE_COMMENT_QUERY = "UPDATE comments SET is_active = false WHERE comment_id = ?";

	private static final String BASE_COMMENT_QUERY = "SELECT c.comment_id, c.description, c.created_at, c.edited_at, c.is_active, c.is_edited,c.is_trusted, "
			+ "u.user_id, u.user_name, u.email_id, u.profile_image, " + "b.book_id, b.title, b.cover_image "
			+ "FROM comments c " + "JOIN users u ON c.user_id = u.user_id " + "JOIN books b ON c.book_id = b.book_id ";

	private static final String LIST_COMMENTS_BY_BOOK_QUERY = BASE_COMMENT_QUERY
			+ "WHERE c.book_id = ? AND c.is_active = true";

	private static final String LIST_ALL_COMMENTS_QUERY = BASE_COMMENT_QUERY + "WHERE c.is_active = true";

	public Comment createComment(Comment comment) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(INSERT_COMMENT_QUERY,
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

	public boolean updateComment(Comment comment) throws DAOException {
		boolean hasUpdated = false;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(UPDATE_COMMENT_QUERY)) {

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

	public boolean deleteComment(int commentId) throws DAOException {
		boolean isDeleted = false;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(DELETE_COMMENT_QUERY)) {

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

	public List<Comment> listCommentByBook(int bookId) throws DAOException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(LIST_COMMENTS_BY_BOOK_QUERY)) {

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

	public List<Comment> listAllComments() throws DAOException {
		List<Comment> comments = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(LIST_ALL_COMMENTS_QUERY)) {

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
