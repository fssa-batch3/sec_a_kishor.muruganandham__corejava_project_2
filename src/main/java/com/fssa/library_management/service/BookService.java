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

    public String addBook(Book book) throws ServiceException {
        try {
            BookValidator bookValidator = new BookValidator(book);
            bookValidator.validateAll();
            Book existingObject = BookDAO.getBookByTitle(book.getTitle());
            if (existingObject != null) {
                return "Book already exists";
            } else {
                if (BookDAO.addBook(book)) {
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
            Book book = BookDAO.getBookByTitle(bookName);
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
            return BookDAO.getAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Book updateBook(Book book) throws ServiceException {
        try {
            Book existingObject = BookDAO.getBookByTitle(book.getTitle());
            if (existingObject == null) {
                throw new ServiceException(BOOK_NOT_FOUND);
            }
            if (!BookDAO.updateBook(book)) {
                throw new ServiceException("Book Update Failed");
            }
            return BookDAO.getBookByTitle(book.getTitle());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteBook(String bookName) throws ServiceException {
        try {
            Book existingObject = BookDAO.getBookByTitle(bookName);
            if (existingObject == null) {
                throw new ServiceException(BOOK_NOT_FOUND);
            }
            return BookDAO.deleteBook(bookName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
