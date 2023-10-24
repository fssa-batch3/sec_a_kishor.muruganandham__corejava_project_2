/**
 * 
 */
package com.fssa.librarymanagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.utils.ConnectionUtil;

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
	void testInValidAverageRating() {
		ServiceException result = assertThrows(ServiceException.class,
				() -> ratingService.getAverageRatingAndCountByBook(1000));
		assertEquals("No ratings found", result.getMessage());

	}

	@Test
	@Order(5)
	void testValidUpdateComment() {
		assertDoesNotThrow(() -> ratingService.getRatingByBookAndUser(2, 1));
	}

	@Test
	@Order(6)
	void testInValidUpdateComment() {
		try {
			assertEquals(0, ratingService.getRatingByBookAndUser(5, 39));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not throw Service Exception");
		}
	}

	@Test
	@Order(7)
	void testGetRatingByBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> ratingService.getAverageRatingAndCountByBook(0));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(8)
	void testGetRatingByBookAndUser_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> ratingService.getRatingByBookAndUser(0, 0));

		ConnectionUtil.setTestingMode(false);
	}
}
