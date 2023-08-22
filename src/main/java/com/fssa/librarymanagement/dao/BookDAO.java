package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildBookFromResultSet;

public class BookDAO {

	public Book getBookByTitle(String bookName) throws DAOException {
		Book book = null;
		String query = "SELECT * FROM books WHERE title = ? AND isActive = true AND available_copies >= 1";

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, bookName);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					book = buildBookFromResultSet(rs);
				}
			}

		} catch (SQLException | NullPointerException e) {
			throw new DAOException(e);
		}
		return book;
	}

	public boolean createBook(Book book) throws DAOException {
		String query = "INSERT INTO books (title, author, publisher, genre, language, description, total_copies, " +
				"available_copies, loaned_copies, cover_image) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getPublisher());
			preparedStatement.setString(4, book.getGenre());
			preparedStatement.setString(5, book.getLanguage());
			preparedStatement.setString(6, book.getDescription());
			preparedStatement.setInt(7, book.getTotalCopies());
			preparedStatement.setInt(8, book.getAvailableCopies());
			preparedStatement.setInt(9, book.getLoanedCopies());
			preparedStatement.setString(10, book.getCoverImage());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Book> getAllBooks() throws DAOException {
		List<Book> bookList = new ArrayList<>();
		String query = "SELECT * FROM books WHERE isActive = true";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query);
		     ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				Book book = buildBookFromResultSet(rs);
				bookList.add(book);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return bookList;
	}

	public boolean updateBook(Book book) throws DAOException {
		String query = "UPDATE books SET " +
				"title = ?, description = ?, total_copies = ?, " +
				"cover_image = ?, genre = ?, language = ?, " +
				"author = ?, publisher = ? " +
				"WHERE book_id = ?";

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getDescription());
			preparedStatement.setInt(3, book.getTotalCopies());
			preparedStatement.setString(4, book.getCoverImage());
			preparedStatement.setString(5, book.getGenre());
			preparedStatement.setString(6, book.getLanguage());
			preparedStatement.setString(7, book.getAuthor());
			preparedStatement.setString(8, book.getPublisher());
			preparedStatement.setInt(9, book.getBookId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void updateBookCopies(int bookId, int loanedCopiesChange, int availableCopiesChange) throws DAOException {
		String query = "UPDATE books SET loaned_copies = loaned_copies + ?, available_copies = available_copies + ? " +
				"WHERE book_id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, loanedCopiesChange);
			preparedStatement.setInt(2, availableCopiesChange);
			preparedStatement.setInt(3, bookId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean deleteBook(String bookName) throws DAOException {
		String query = "UPDATE books SET isActive = false WHERE title = ?";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, bookName);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
