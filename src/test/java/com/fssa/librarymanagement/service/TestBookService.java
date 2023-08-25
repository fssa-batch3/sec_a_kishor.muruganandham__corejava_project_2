package com.fssa.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
			String result = bookService.addBook(book);
			assertEquals("Book added successfully", result);
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
		assertThrows(ServiceException.class, () -> bookService.getBookByName("No Title"));
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
			Object existingObject = bookService.getBookByName(book.getTitle());
			assertNotNull(existingObject, "Book should exist");

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
