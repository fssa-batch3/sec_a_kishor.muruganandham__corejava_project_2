package com.fssa.librarymanagement.service;

import java.util.List;

import com.fssa.librarymanagement.constants.ErrorMessageConstants;
import com.fssa.librarymanagement.constants.SuccessMessageConstants;
import com.fssa.librarymanagement.dao.BookDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.validation.BookValidator;

/**
 * This class provides services related to book management, such as adding, retrieving, updating, and deleting books.
 *
 * @author KishorMuruganandham
 */
public class BookService {

	private final BookDAO bookDAO = new BookDAO();


	/**
	 * Add a new book to the library.
	 *
	 * @param book The book object to be added.
	 * @return A success message if the book is added successfully, or an error message if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	protected String addBook(Book book) throws ServiceException {
		try {
			BookValidator bookValidator = new BookValidator(book);
			bookValidator.validateAll();

			// Check if a book with the same title already exists
			Book existingObject = bookDAO.getBookByTitle(book.getTitle());
			if (existingObject != null) {
				return ErrorMessageConstants.BOOK_ALREADY_EXISTS;
			} else {
				// Create the book in Database
				if (bookDAO.createBook(book)) {
					return SuccessMessageConstants.BOOK_ADDED_SUCCESSFULLY;
				} else {
					throw new ServiceException(ErrorMessageConstants.FAILED_TO_CREATE_BOOK);
				}
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_ADD_BOOK);
		}
	}

	/**
	 * Get a book by its name (title).
	 *
	 * @param bookName The name (title) of the book.
	 * @return The book object if found.
	 * @throws ServiceException If the book is not found or if there's a problem with the service.
	 */
	protected Book getBookByName(String bookName) throws ServiceException {
		try {
			// Retrieve the book by its title
			Book book = bookDAO.getBookByTitle(bookName);
			if (book == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			return book;
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BOOK_DETAILS);
		}
	}

	/**
	 * Get a list of all books in the library.
	 *
	 * @return A list of book objects.
	 * @throws ServiceException If there's a problem with the service.
	 */
	protected List<Book> listAllBooks() throws ServiceException {
		try {
			// Retrieve all books from the database
			return bookDAO.getAllBooks();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BOOK_LIST);
		}
	}

	/**
	 * Update a book's information.
	 *
	 * @param book The book object containing updated information.
	 * @return The updated book object.
	 * @throws ServiceException If the book is not found or if there's a problem with the service.
	 */
	protected Book updateBook(Book book) throws ServiceException {
		try {
			// Check if the book exists
			Book existingBook = bookDAO.getBookByTitle(book.getTitle());
			if (existingBook == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			// Update the book in Database
			if (!bookDAO.updateBook(book)) {
				throw new ServiceException(ErrorMessageConstants.BOOK_UPDATE_FAILED);
			}
			// Return the updated book object
			return bookDAO.getBookByTitle(book.getTitle());
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_BOOK);
		}
	}

	/**
	 * Delete a book by its name (title).
	 *
	 * @param bookName The name (title) of the book to be deleted.
	 * @return True if the book is deleted successfully, false otherwise.
	 * @throws ServiceException If the book is not found or if there's a problem with the service.
	 */
	protected boolean deleteBook(String bookName) throws ServiceException {
		try {
			// Check if the book exists
			Book existingObject = bookDAO.getBookByTitle(bookName);
			if (existingObject == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			// Delete the book and return true if successful
			return bookDAO.deleteBook(bookName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
