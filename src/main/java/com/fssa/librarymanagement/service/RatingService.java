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
 * 
 */
public class RatingService {

	private RatingDAO ratingDAO = new RatingDAO();

	public boolean submitRating(Rating rating) throws ServiceException {
		try {
			RatingValidator ratingValidator = new RatingValidator();
			ratingValidator.validateAll();

			return ratingDAO.submitRating(rating);

		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
    public Map<String, Object> getAverageRatingAndCountByBook(int bookId) throws ServiceException {
        try {
            return ratingDAO.getRatingByBook(bookId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int getRatingByBookAndUser(int bookId, int userId) throws ServiceException {
        try {
            return ratingDAO.getRatingByBookAndUser(bookId, userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
