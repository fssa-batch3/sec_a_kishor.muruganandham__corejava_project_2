/**
 * 
 */
package com.fssa.librarymanagement.service;

import java.util.List;

import com.fssa.librarymanagement.dao.BookRequestDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.BookRequest;
import com.fssa.librarymanagement.validation.BookRequestValidator;

/**
 * 
 */
public class BookRequestService {

	private final BookRequestDAO bookRequestDAO = new BookRequestDAO();

	/**
	 * Constructs a new BookService object for handling book-related business logic
	 * and interactions.
	 */
	public BookRequestService() {
		// Default constructor
	}

	public boolean createBookRequest(BookRequest bookRequestData) throws ServiceException {
		try {
			BookRequestValidator bookRequestValidator = new BookRequestValidator(bookRequestData);
			bookRequestValidator.validateAll();
			return bookRequestDAO.createBookRequest(bookRequestData);
		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<BookRequest> getAllBookRequests() throws ServiceException {
		try {
			return bookRequestDAO.getAllBookRequests();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
