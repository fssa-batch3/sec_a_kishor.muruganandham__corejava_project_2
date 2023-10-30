/**
 *
 */
package com.fssa.librarymanagement.constants;

/**
 *
 */
public class CommentConstants {

	public static final String INSERT_COMMENT_QUERY = "INSERT INTO comments (user_id, book_id, description, " +
			"created_at, is_trusted) "
			+ "VALUES (?, ?, ?, ?, ?)";

	public static final String UPDATE_COMMENT_QUERY = "UPDATE comments SET description = ?, edited_at = ?, is_edited " +
			"=" +
			" TRUE WHERE comment_id = ?";

	public static final String DELETE_COMMENT_QUERY = "UPDATE comments SET is_active = false WHERE comment_id = ?";

	public static final String BASE_COMMENT_QUERY = "SELECT c.comment_id, c.description, c.created_at, c.edited_at, " +
			"c" +
			".is_active, c.is_edited,c.is_trusted, "
			+ "u.user_id, u.user_name, u.email_id, u.profile_image, " + "b.book_id, b.title, b.cover_image "
			+ "FROM comments c " + "JOIN users u ON c.user_id = u.user_id " + "JOIN books b ON c.book_id = b.book_id ";

	public static final String LIST_COMMENTS_BY_BOOK_QUERY = BASE_COMMENT_QUERY
			+ "WHERE c.book_id = ? AND c.is_active = true";

	public static final String LIST_ALL_COMMENTS_QUERY = BASE_COMMENT_QUERY + "WHERE c.is_active = true";

	private CommentConstants() {
		// Private constructor to prevent instantiation
	}
}
