package com.fssa.library_management.service;

import com.fssa.library_management.dao.BookDAO;
import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.Book;
import com.fssa.library_management.validation.BookValidator;

import java.util.List;

public class BookService {
	private static final String BOOK_NOT_FOUND = "Book not found";
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
				return "Book already exists";
			} else {
				if (bookDAO.addBook(book)) {
					return "Book added successfully";
				} else {
					return "Failed to add book";
				}
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e);
		}
	}


	public Book getBookByName(String bookName) throws ServiceException {
		try {
			Book book = bookDAO.getBookByTitle(bookName);
			if (book == null) {
				throw new ServiceException(BOOK_NOT_FOUND);
			}
			return book;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	public List<Book> getAllBooks() throws ServiceException {
		try {
			return bookDAO.getAllBooks();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public Book updateBook(Book book) throws ServiceException {
		try {
			Book existingObject = bookDAO.getBookByTitle(book.getTitle());
			if (existingObject == null) {
				throw new ServiceException(BOOK_NOT_FOUND);
			}
			if (!bookDAO.updateBook(book)) {
				throw new ServiceException("Book Update Failed");
			}
			return bookDAO.getBookByTitle(book.getTitle());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public boolean deleteBook(String bookName) throws ServiceException {
		try {
			Book existingObject = bookDAO.getBookByTitle(bookName);
			if (existingObject == null) {
				throw new ServiceException(BOOK_NOT_FOUND);
			}
			return bookDAO.deleteBook(bookName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
