package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.constants.BookConstants;
import com.fssa.librarymanagement.dao.BookDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.validation.BookValidator;

import java.util.List;

/**
 * This class provides services related to book management, such as adding, retrieving, updating, and deleting books.
 *
 * @author KishorMuruganandham
 */
public class BookService {

	private final BookDAO bookDAO = new BookDAO();

	/**
	 * Constructs a new BookService object for handling book-related business logic and interactions.
	 */
	public BookService() {
	}

	/**
	 * Add a new book to the library.
	 *
	 * @param book The book object to be added.
	 * @return A success message if the book is added successfully, or an error message if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public boolean addBook(Book book) throws ServiceException {
		try {
			BookValidator bookValidator = new BookValidator(book);
			bookValidator.validateAll();

			return bookDAO.createBook(book);    // Create the book in Database
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Get a book by its name (title).
	 *
	 * @param bookName The name (title) of the book.
	 * @return The book object if found.
	 * @throws ServiceException If the book is not found, or if there's a problem with the service.
	 */
	public Book getBookByName(String bookName) throws ServiceException {
		try {

			Book book = bookDAO.getBookByTitle(bookName); // Retrieve the book by its title
			if (book == null) {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}
			return book;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieve a book by its ID from the library.
	 *
	 * @param bookId The ID of the book to retrieve.
	 * @return The retrieved book if found, or an error message if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public Book getBookById(int bookId) throws ServiceException {
		try {

			Book book = bookDAO.getBookById(bookId);    // Retrieve the book from Database
			if (book == null) {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}
			return book;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}


	/**
	 * Get a list of all books in the library.
	 *
	 * @return A list of book objects.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public List<Book> listAllBooks() throws ServiceException {
		try {

			List<Book> books = bookDAO.getAllBooks();   // Retrieve all books from the database

			// Check if the list is not null and has elements
			if (books != null && !books.isEmpty()) {
				return books;
			} else {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}


	/**
	 * Update a book's information.
	 *
	 * @param book The book object containing updated information.
	 * @return True if the book is updated successfully, false otherwise.
	 * @throws ServiceException If the book is not found, or if there's a problem with the service.
	 */
	public boolean updateBook(Book book) throws ServiceException {
		try {

			boolean bookExist = bookDAO.doesBookExist(book.getBookId());     // Check if the book exists
			if (!bookExist) {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}
			return bookDAO.updateBook(book);    // Update the book and return true if successful
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Delete a book by its id.
	 *
	 * @param bookId The id of the book to be deleted.
	 * @return True if the book is deleted successfully, false otherwise.
	 * @throws ServiceException If the book is not found, or if there's a problem with the service.
	 */
	public boolean deleteBook(int bookId) throws ServiceException {
		try {

			boolean bookExist = bookDAO.doesBookExist(bookId);    // Check if the book exists
			if (!bookExist) {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}

			return bookDAO.deleteBook(bookId);    // Delete the book and return true if successful
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
