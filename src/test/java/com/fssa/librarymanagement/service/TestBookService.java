package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.Book;

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
	@Order(6)
	void testInvalidGetBookById() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.getBookById(4321));
		assertEquals("Book not found.", result.getMessage());
	}

	@Test
	@Order(7)
	void testListAllBooks() {
		assertDoesNotThrow(() -> bookService.listAllBooks());
	}



	@Test
	@Order(9)
	void testInvalidUpdateBook() {
		Book book = new Book();
		book.setTitle("No Title");
		book.setDescription("Invalid description");

		ServiceException result = assertThrows(ServiceException.class, () -> bookService.updateBook(book));
		assertEquals("Book not found.", result.getMessage());
	}



	@Test
	@Order(11)
	void testInvalidDeleteBook() {
		ServiceException result = assertThrows(ServiceException.class, () -> bookService.deleteBook(0));
		assertEquals("Book not found.", result.getMessage());
	}


}
