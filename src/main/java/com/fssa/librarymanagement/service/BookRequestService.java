/**
 *
 */
package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.dao.BookRequestDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.BookRequest;
import com.fssa.librarymanagement.validation.BookRequestValidator;

import java.util.List;

/**
 * A service class for managing book request-related operations.
 *
 * @author KishorMuruganandham
 *
 */

public class BookRequestService {

	private final BookRequestDAO bookRequestDAO = new BookRequestDAO();

	/**
	 * Constructs a new BookRequestService object.
	 */

	public BookRequestService() {
		// Default constructor
	}

	/**
	 * Creates a new book request.
	 *
	 * @param bookRequestData The book request data to create
	 * @return true if the book request is created successfully, false otherwise
	 * @throws ServiceException If there is an issue during the creation process
	 */

	public boolean createBookRequest(BookRequest bookRequestData) throws ServiceException {
		try {
			BookRequestValidator bookRequestValidator = new BookRequestValidator(bookRequestData);
			bookRequestValidator.validateAll();
			return bookRequestDAO.createBookRequest(bookRequestData);
		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a list of all book requests.
	 *
	 * @return A list of book requests
	 * @throws ServiceException If there is an issue during the retrieval process
	 */

	public List<BookRequest> getAllBookRequests() throws ServiceException {
		try {
			return bookRequestDAO.getAllBookRequests();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
