package com.fssa.librarymanagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.utils.ConnectionUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBookService {

	private BookService bookService;
	private Book book;
	private int bookId;

	@BeforeEach
	public void setUp() {
		bookService = new BookService();
		book = new Book();
		book.setTitle("The Firm");
		book.setDescription("A thrilling legal thriller involving murder and courtroom drama.");
		book.setAuthor("John Grisham");
		book.setPublisher("Doubleday");
		book.setLanguage("English");
		book.setGenre("Mystery");
		book.setCoverImage("https://books.google.co.in/books/content?id=LOmrO03ioesC&pg=PP1&img=1&zoom=3&hl=en&bul=1"
				+ "&sig=ACfU3U3aZTlrPszR_20legpDQ24tf3ThiQ&w=1280");
		book.setTotalCopies(13);
		book.setAvailableCopies(10);
		book.setLoanedCopies(3);
	}

	@Test
	@Order(1)
	void testValidAddBook() {
		assertDoesNotThrow(() -> bookService.addBook(book));
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
	void testValidSearchBooksByTitle() {
		assertDoesNotThrow(() -> bookService.searchBooksByTitle("Th"));
	}

	@Test
	@Order(4)
	void testInvalidSearchBooksByTitle() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.searchBooksByTitle("zxcvb"));
		assertEquals("No Books Found", result.getMessage());
	}

	@Test
	@Order(5)
	void testValidGetBookById() {
		try {
			List<Book> books = bookService.listAllBooks();
			bookId = books.get(books.size() - 1).getBookId();
			assertNotNull(bookService.getBookById(bookId));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}
	}

	@Test
	@Order(6)
	void testInvalidGetBookById() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.getBookById(4321));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(7)
	void testValidUpdateBook() {
		try {
			List<Book> books = bookService.listAllBooks();
			bookId = books.get(books.size() - 1).getBookId();
			book.setPages(20);
			book.setBookId(bookId);
			assertTrue(bookService.updateBook(book));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}

	}

	@Test
	@Order(8)
	void testInvalidUpdateBook() {
		Book book = new Book();
		book.setTitle("No Title");
		book.setDescription("Invalid description");

		ServiceException result = assertThrows(ServiceException.class, () -> bookService.updateBook(book));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(9)
	void testValidDeleteBook() {
		try {
			List<Book> books = bookService.listAllBooks();
			bookId = books.get(books.size() - 1).getBookId();
			assertTrue(bookService.deleteBook(bookId));
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("Should Not Throw Service Exception");
		}
	}

	@Test
	@Order(10)
	void testInvalidDeleteBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.deleteBook(0));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(11)
	void testValidListAllGenres() {
		assertDoesNotThrow(() -> bookService.listAllGenres());
	}

	@Test
	@Order(12)
	void testGetBookById_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> bookService.getBookById(0));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(13)
	void testGetAllBooks_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> bookService.listAllBooks());
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(14)
	void testUpdateBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);
		Book book = new Book();
		assertThrows(ServiceException.class, () -> bookService.updateBook(book));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(15)
	void testDeleteBook_ServiceException() {
		ConnectionUtil.setTestingMode(true);
		Book book = new Book();
		assertThrows(ServiceException.class, () -> bookService.deleteBook(book.getBookId()));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(16)
	void testGetAllGenres_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> bookService.listAllGenres());
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	@Order(17)
	void testSearchBooksByTitle_ServiceException() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(ServiceException.class, () -> bookService.searchBooksByTitle("InvalidTitle"));

		ConnectionUtil.setTestingMode(false);
	}
}
