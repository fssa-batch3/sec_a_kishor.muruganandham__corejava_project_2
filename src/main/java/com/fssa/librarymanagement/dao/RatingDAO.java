package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.constants.RatingConstants;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Access Object (DAO) class for handling book ratings related database operations.
 */
public class RatingDAO {

	/**
	 * Constructs a new RatingDAO object for performing database operations related to book ratings.
	 */

	public RatingDAO() {
		// Default constructor
	}

	/**
	 * Submit a new rating for a book.
	 *
	 * @param rating The rating object to be submitted.
	 * @return true if the rating is successfully submitted, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public boolean submitRating(Rating rating) throws DAOException {
		boolean hasSubmitted = false;
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(RatingConstants.INSERT_OR_UPDATE_RATING_QUERY)) {

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

	/**
	 * Get the rating information for a specific book.
	 *
	 * @param bookId The ID of the book for which to retrieve ratings.
	 * @return A map containing rating information for the specified book.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public Map<String, Object> getRatingByBook(int bookId) throws DAOException {
		Map<String, Object> ratingInfo = null;

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(RatingConstants.GET_RATING_BY_BOOK_QUERY)) {

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

	/**
	 * Get the rating given by a specific user to a particular book.
	 *
	 * @param bookId The ID of the book.
	 * @param userId The ID of the user who submitted the rating.
	 * @return The rating value given by the user to the book.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public int getRatingByBookAndUser(int bookId, int userId) throws DAOException {
		int count = 0;
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(RatingConstants.GET_RATING_BY_BOOK_AND_USER_QUERY)) {

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
