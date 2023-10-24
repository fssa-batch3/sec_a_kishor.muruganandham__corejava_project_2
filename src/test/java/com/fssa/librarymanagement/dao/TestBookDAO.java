package com.fssa.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.utils.ConnectionUtil;

class TestBookDAO {

	private BookDAO bookDAO;

	@BeforeEach
	public void setUp() {
		bookDAO = new BookDAO();
	}

	@Test
	void testSearchBooksByTitle() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.searchBooksByTitle("InvalidTitle"));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testDoesBookExist() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.doesBookExist(0));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testCreateBook() {
		ConnectionUtil.setTestingMode(true);
		Book book = new Book();
		assertThrows(DAOException.class, () -> bookDAO.createBook(book));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetBookById() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.getBookById(0));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetAllBooks() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.getAllBooks());
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testUpdateBook() {
		ConnectionUtil.setTestingMode(true);
		Book book = new Book();
		assertThrows(DAOException.class, () -> bookDAO.updateBook(book));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testDeleteBook() {
		ConnectionUtil.setTestingMode(true);
		Book book = new Book();
		assertThrows(DAOException.class, () -> bookDAO.deleteBook(book.getBookId()));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetAllGenres() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.getAllGenres());
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testUpdateBookCopies() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> bookDAO.updateBookCopies(0, 0, 0));
		ConnectionUtil.setTestingMode(false);
	}

}
