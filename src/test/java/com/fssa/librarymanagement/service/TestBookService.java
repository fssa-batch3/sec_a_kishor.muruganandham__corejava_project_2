package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
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
		book.setLoanedCopies(2);
		book.setCoverImage("image");
	}

	@Test
	@Order(1)
	void testValidAddBook() {
		try {
			boolean result = bookService.addBook(book);
			assertTrue(result);
		} catch (ServiceException e) {
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
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.getBookByName("No Title"));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(5)
	void testListAllBooks() {
		try {
			List<Book> allBooks = bookService.listAllBooks();
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
			boolean bookUpdate = bookService.updateBook(existingBook);
			Book updatedBook = bookService.getBookByName(book.getTitle());

			assertTrue(bookUpdate);
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

		ServiceException result = assertThrows(ServiceException.class, () -> bookService.updateBook(book));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(8)
	void testValidDeleteBook() {
		try {
			Book existingBook = bookService.getBookByName(book.getTitle());
			boolean isDeleted = bookService.deleteBook(existingBook.getBookId());
			assertTrue(isDeleted);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should not throw ServiceException");
		}
	}

	@Test
	@Order(9)
	void testInvalidDeleteBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.deleteBook(0));
		assertEquals("Book not found.", result.getMessage());
	}
}
