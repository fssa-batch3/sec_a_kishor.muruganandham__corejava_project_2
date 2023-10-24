/**
 * 
 */
package com.fssa.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
public class RatingDAO {

	private static final String INSERT_OR_UPDATE_RATING_QUERY = "INSERT INTO ratings (user_id, book_id, rating) VALUES (?, ?, ?) "
			+ "ON DUPLICATE KEY UPDATE rating = VALUES(rating)";

	public boolean submitRating(Rating rating) throws DAOException {
		boolean hasSubmitted = false;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(INSERT_OR_UPDATE_RATING_QUERY)) {

			pst.setInt(1, rating.getUserId());
			pst.setInt(2, rating.getBookId());
			pst.setInt(3, rating.getRating());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				hasSubmitted = true;
			}
			return hasSubmitted;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	private static final String GET_RATING_BY_BOOK_QUERY = "SELECT AVG(rating) AS average_rating, COUNT(*) AS rating_count "
			+ "FROM ratings WHERE book_id = ?";

	public Map<String, Object> getRatingByBook(int bookId) throws DAOException {
		Map<String, Object> ratingInfo = null;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(GET_RATING_BY_BOOK_QUERY)) {

			pst.setInt(1, bookId);
			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next()) {
				ratingInfo = new HashMap<>();
				ratingInfo.put("average_rating", resultSet.getDouble("average_rating"));
				ratingInfo.put("rating_count", resultSet.getInt("rating_count"));
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}

		return ratingInfo;
	}

	private static final String GET_RATING_BY_BOOK_AND_USER_QUERY = "SELECT rating FROM ratings WHERE book_id = ? AND user_id = ?";

	public int getRatingByBookAndUser(int bookId, int userId) throws DAOException {
		int count = 0;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(GET_RATING_BY_BOOK_AND_USER_QUERY)) {

			pst.setInt(1, bookId);
			pst.setInt(2, userId);
			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next()) {
				count = resultSet.getInt("rating");
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}

		return count;
	}

}
