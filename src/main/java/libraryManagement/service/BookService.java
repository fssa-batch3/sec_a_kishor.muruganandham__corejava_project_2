package libraryManagement.service;

import libraryManagement.DAO.BookDao;
import libraryManagement.exceptions.DAOException;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Book;
import libraryManagement.validation.ValidateBook;

import java.util.List;

public class BookService {

    private static final String BOOK_NOT_FOUND = "Book not found";

    public String addBook(Book book) throws ValidationException, DAOException {
        ValidateBook validateBook = new ValidateBook(book);
        validateBook.validateAll();
        Book existingBook = BookDao.getBookByTitle(book.getTitle());
        if (existingBook != null) {
            return "Book already exists";
        } else {
            if (BookDao.addBook(book)) {
                return "Book added successfully";
            } else {
                return "Failed to add book";
            }
        }
    }

    public Book getBookByName(String bookName) throws ServiceException, DAOException {
        Book book = BookDao.getBookByTitle(bookName);
        if (book == null) {
            throw new ServiceException(BOOK_NOT_FOUND);
        }
        return book;
    }

    public List<Book> getAllBooks() throws DAOException {
        return BookDao.getAllBooks();
    }

    public Book updateBook(Book book) throws ServiceException, DAOException {
        Book existingBook = BookDao.getBookByTitle(book.getTitle());
        if (existingBook == null) {
            throw new ServiceException(BOOK_NOT_FOUND);
        }
        return BookDao.updateBook(book);
    }

    public boolean deleteBook(String bookName) throws ServiceException, DAOException {
        Book existingBook = BookDao.getBookByTitle(bookName);
        if (existingBook == null) {
            throw new ServiceException(BOOK_NOT_FOUND);
        }
        return BookDao.deleteBook(bookName);
    }
}
