package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBookService {

	private BookService bookService;
	private Book book;

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
		book.setCoverImage("https://books.google.co.in/books/content?id=LOmrO03ioesC&pg=PP1&img=1&zoom=3&hl=en&bul=1" +
				                   "&sig=ACfU3U3aZTlrPszR_20legpDQ24tf3ThiQ&w=1280");
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
	void testValidGetBookByName() {
		assertDoesNotThrow(() -> bookService.getBookByName(book.getTitle()));
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
		assertDoesNotThrow(() -> bookService.listAllBooks());
	}

	@Test
	@Order(6)
	void testValidUpdateBook() {
		try {
			Book beforeUpdate = bookService.getBookByName(book.getTitle());
			book.setBookId(beforeUpdate.getBookId());
			book.setDescription("Updated description");
			boolean bookUpdate = bookService.updateBook(book);
			Book updatedBook = bookService.getBookByName(book.getTitle());

			assertTrue(bookUpdate);
			assertEquals(book.getDescription(), updatedBook.getDescription(), "Description should be updated");
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
			System.out.println(existingBook);
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
