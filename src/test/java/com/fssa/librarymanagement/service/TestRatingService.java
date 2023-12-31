/**
 *
 */
package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.utils.ConnectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class TestRatingService {
	private RatingService ratingService;
	private Rating rating;

	@BeforeEach
	public void setUp() {
		ratingService = new RatingService();
		rating = new Rating();
		rating.setBookId(2);
		rating.setUserId(1);
	}

	@Test
	@Order(1)
	void testValidSubmitRating() {
		rating.setRating(3);
		assertDoesNotThrow(() -> ratingService.submitRating(rating));
	}

	@Test
	@Order(2)
	void testInValidSubmitRating() {
		rating.setRating(6);
		ServiceException result = assertThrows(ServiceException.class, () -> ratingService.submitRating(rating));
		assertEquals("Rating must be between 0 and 5.", result.getMessage());
	}

	@Test
	@Order(3)
	void testValidAverageRating() {
		Map<String, Object> result = assertDoesNotThrow(() -> ratingService.getAverageRatingAndCountByBook(2));
		assertNotNull(result);
	}


	@Test
	@Order(4)
	void testValidUpdateComment() {
		assertDoesNotThrow(() -> ratingService.getRatingByBookAndUser(2, 1));
	}

	@Test
	@Order(5)
	void testInValidUpdateComment() {
		try {
			assertEquals(0, ratingService.getRatingByBookAndUser(5, 39));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not throw Service Exception");
		}
	}

	@Test
	@Order(6)
	void testGetRatingByBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> ratingService.getAverageRatingAndCountByBook(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(7)
	void testGetRatingByBookAndUser_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> ratingService.getRatingByBookAndUser(0, 0));

		ConnectionUtil.setTestingMode(false);
	}
}
