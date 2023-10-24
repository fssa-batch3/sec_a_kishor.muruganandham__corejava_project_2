/**
 * 
 */
package com.fssa.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
class TestRatingDAO {
	private RatingDAO ratingDAO;

	@BeforeEach
	public void setUp() {
		ratingDAO = new RatingDAO();
	}

	@Test
	void testSubmitRating() {
		ConnectionUtil.setTestingMode(true);
		Rating rating = new Rating();
		assertThrows(DAOException.class, () -> ratingDAO.submitRating(rating));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetRatingByBook() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> ratingDAO.getRatingByBook(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetRatingByBookAndUser() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> ratingDAO.getRatingByBookAndUser(0, 0));

		ConnectionUtil.setTestingMode(false);
	}
}
