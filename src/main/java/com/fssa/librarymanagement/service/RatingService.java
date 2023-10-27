/**
 * 
 */
package com.fssa.librarymanagement.service;

import java.util.Map;

import com.fssa.librarymanagement.dao.RatingDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Rating;
import com.fssa.librarymanagement.validation.RatingValidator;

/**
 * The RatingService class manages ratings and reviews for books in a library management system.
 * It provides methods to submit ratings, get average ratings and counts by book, and retrieve a user's rating for a book.
 * 
 * @author KishorMuruganandham
 * 
 */

public class RatingService {

	private RatingDAO ratingDAO = new RatingDAO();
	
    /**
     * Constructs a new RatingService instance.
     */
    public RatingService() {
        // Default Constructor
    }
    
    /**
     * Submits a rating for a book, validating the rating value.
     *
     * @param rating The rating to be submitted.
     * @return true if the rating was successfully submitted; false otherwise.
     * @throws ServiceException If an error occurs during the service operation.
     */
	public boolean submitRating(Rating rating) throws ServiceException {
		try {
			RatingValidator ratingValidator = new RatingValidator();
			ratingValidator.validateRange(rating.getRating());

			return ratingDAO.submitRating(rating);

		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

    /**
     * Retrieves the average rating and count of ratings for a specific book.
     *
     * @param bookId The unique identifier of the book.
     * @return A map containing the average rating and count of ratings for the book.
     * @throws ServiceException If an error occurs during the service operation.
     */
	
	public Map<String, Object> getAverageRatingAndCountByBook(int bookId) throws ServiceException {
		try {
			
			return ratingDAO.getRatingByBook(bookId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
    /**
     * Retrieves a user's rating for a specific book.
     *
     * @param bookId The unique identifier of the book.
     * @param userId The unique identifier of the user who submitted the rating.
     * @return The user's rating for the book.
     * @throws ServiceException If an error occurs during the service operation.
     */

	public int getRatingByBookAndUser(int bookId, int userId) throws ServiceException {
		try {
			return ratingDAO.getRatingByBookAndUser(bookId, userId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
