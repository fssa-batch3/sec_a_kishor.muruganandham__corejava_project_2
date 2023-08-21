package com.fssa.library_management.service;

import com.fssa.library_management.constants.ErrorMessageConstants;
import com.fssa.library_management.constants.SuccessMessageConstants;
import com.fssa.library_management.dao.BookDAO;
import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.Book;
import com.fssa.library_management.validation.BookValidator;

import java.util.List;

public class BookService {

	private final BookDAO bookDAO;

	protected BookService() {
		this.bookDAO = new BookDAO();
	}

	public String addBook(Book book) throws ServiceException {
		try {
			BookValidator bookValidator = new BookValidator(book);
			bookValidator.validateAll();
			Book existingObject = bookDAO.getBookByTitle(book.getTitle());
			if (existingObject != null) {
				return ErrorMessageConstants.BOOK_ALREADY_EXISTS;
			} else {
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


	public Book getBookByName(String bookName) throws ServiceException {
		try {
			Book book = bookDAO.getBookByTitle(bookName);
			if (book == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			return book;
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BOOK_DETAILS);
		}
	}


	public List<Book> listAllBooks() throws ServiceException {
		try {
			return bookDAO.getAllBooks();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BOOK_LIST);
		}
	}

	public Book updateBook(Book book) throws ServiceException {
		try {
			Book existingBook = bookDAO.getBookByTitle(book.getTitle());
			if (existingBook == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			if (!bookDAO.updateBook(book)) {
				throw new ServiceException(ErrorMessageConstants.BOOK_UPDATE_FAILED);
			}
			return bookDAO.getBookByTitle(book.getTitle());
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_BOOK);
		}
	}

	public boolean deleteBook(String bookName) throws ServiceException {
		try {
			Book existingObject = bookDAO.getBookByTitle(bookName);
			if (existingObject == null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_NOT_FOUND);
			}
			return bookDAO.deleteBook(bookName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
