package library_management.service;

import library_management.DAO.BookDao;
import library_management.exceptions.DAOException;
import library_management.exceptions.ServiceException;
import library_management.model.Book;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBookService {

    private BookService bookService;
    private Book book;

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        book = new Book();
        book.setAuthor("Kishor");
        book.setGenre("Action");
        book.setDescription("Description");
        book.setTitle("Title");
        book.setLanguage("Eng");
        book.setPublisher("Publisher");
        book.setTotalCopies(10);
        book.setAvailableCopies(5);
        book.setLoanedCopies(5);
        book.setCoverImage("image");
    }

    @Test
    @Order(1)
    void testValidAddBook() {
        try {
            Book existingBook = BookDao.getBookByTitle(book.getTitle());
            assertNull(existingBook, "Book should not exist");

            String result = bookService.addBook(book);
            assertEquals("Book added successfully", result);
        } catch (ServiceException | DAOException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(2)
    void testInvalidAddBook() {
        Book invalidBook = new Book();
        invalidBook.setTitle("");
        invalidBook.setTotalCopies(-1);
        invalidBook.setCoverImage("image");

        assertThrows(ServiceException.class, () -> bookService.addBook(invalidBook));
    }

    @Test
    @Order(3)
    void testValidGetBookByName() {
        try {
            Book bookFromDB = bookService.getBookByName(book.getTitle());
            assertNotNull(bookFromDB, "Book should exist");
            assertEquals(book.getTitle(), bookFromDB.getTitle());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(4)
    void testInvalidGetBookByName() {
        assertThrows(ServiceException.class, () -> bookService.getBookByName("No Title"));
    }

    @Test
    @Order(5)
    void testGetAllBooks() {
        try {
            List<Book> allBooks = bookService.getAllBooks();
            assertNotNull(allBooks, "List of books should not be null");
            for (Book b : allBooks) {
                System.out.println(b.getTitle());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(6)
    void testValidUpdateBook() {
        try {
            Book existingBook = bookService.getBookByName(book.getTitle());
            assertNotNull(existingBook, "Book should exist");

            existingBook.setDescription("Updated description");
            Book updatedBook = bookService.updateBook(existingBook);

            assertNotNull(updatedBook, "Updated book should not be null");
            assertEquals(existingBook.getDescription(), updatedBook.getDescription(), "Description should be updated");
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(7)
    void testInvalidUpdateBook() {
        Book book = new Book();
        book.setTitle("No Title");
        book.setDescription("Invalid description");

        assertThrows(ServiceException.class, () -> bookService.updateBook(book));
    }

    @Test
    @Order(8)
    void testValidDeleteBook() {
        try {
            Book existingBook = bookService.getBookByName(book.getTitle());
            assertNotNull(existingBook, "Book should exist");

            boolean isDeleted = bookService.deleteBook(book.getTitle());
            assertTrue(isDeleted, "Book should be deleted successfully");
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(9)
    void testInvalidDeleteBook() {
        assertThrows(ServiceException.class, () -> bookService.deleteBook("No Title"));
    }
}
