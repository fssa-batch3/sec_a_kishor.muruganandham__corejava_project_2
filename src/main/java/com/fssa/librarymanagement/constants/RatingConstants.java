/**
 *
 */
package com.fssa.librarymanagement.constants;

/**
 *
 */
public class RatingConstants {

	public static final String INSERT_OR_UPDATE_RATING_QUERY = "INSERT INTO ratings (user_id, book_id, rating) VALUES" +
			" " +
			"(?, ?, ?) "
			+ "ON DUPLICATE KEY UPDATE rating = VALUES(rating)";

	public static final String GET_RATING_BY_BOOK_QUERY = "SELECT AVG(rating) AS average_rating, COUNT(*) AS " +
			"rating_count "
			+ "FROM ratings WHERE book_id = ?";


	public static final String GET_RATING_BY_BOOK_AND_USER_QUERY = "SELECT rating FROM ratings WHERE book_id = ? AND" +
			" " +
			"user_id = ?";

	private RatingConstants() {
		// Private constructor to prevent instantiation
	}
}
