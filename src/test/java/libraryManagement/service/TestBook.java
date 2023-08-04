package libraryManagement.service;

import libraryManagement.DAO.BookDao;
import libraryManagement.exceptions.DAOException;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBook {

    private BookService bookService;
    private Book book;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        book = new Book();
        book.setAuthor("Kishor");
        book.setGenre("Action");
        book.setDescription("Description");
        book.setTitle("Title2");
        book.setLanguage("Eng");
        book.setPublisher("Publisher");
        book.setTotalCopies(10);
        book.setAvailableCopies(5);
        book.setLoanedCopies(5);
        book.setCoverImage("image");
    }

    @Test
    void testAddBook() {
        try {
            Book existingBook = BookDao.getBookByTitle(book.getTitle());
            assertNull(existingBook, "Book should not exist");

            String result = bookService.addBook(book);
            assertEquals("Book added successfully", result);
        } catch (DAOException | ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Test
    void testGetBookByName() {
        try {
            Book bookFromDB = bookService.getBookByName(book.getTitle());
            assertNotNull(bookFromDB, "Book should exist");
            assertEquals(book.getTitle(), bookFromDB.getTitle());
        } catch (ServiceException | DAOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetAllBooks() {
        try {
            List<Book> allBooks = bookService.getAllBooks();
            assertNotNull(allBooks, "List of books should not be null");
            for (Book b : allBooks) {
                System.out.println(b.getTitle());
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUpdateBook() {
        try {
            Book existingBook = bookService.getBookByName(book.getTitle());
            assertNotNull(existingBook, "Book should exist");

            existingBook.setDescription("Updated description");
            Book updatedBook = bookService.updateBook(existingBook);

            assertNotNull(updatedBook, "Updated book should not be null");
            assertEquals(existingBook.getDescription(), updatedBook.getDescription(), "Description should be updated");
        } catch (ServiceException | DAOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteBook() {
        try {
            Book existingBook = bookService.getBookByName(book.getTitle());
            assertNotNull(existingBook, "Book should exist");

            boolean isDeleted = bookService.deleteBook(book.getTitle());
            assertTrue(isDeleted, "Book should be deleted successfully");

        } catch (ServiceException | DAOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
