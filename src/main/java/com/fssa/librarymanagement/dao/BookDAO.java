package com.fssa.librarymanagement.dao;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildBookFromResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * Data Access Object (DAO) class for handling Book-related database operations.
 */
public class BookDAO {
	/**
	 * Constructs a new BookDAO object for performing database operations related to
	 * books.
	 */
	public BookDAO() {
		// Default constructor
	}

	/**
	 * Retrieves books by a title match.
	 *
	 * @param title The title of the book
	 * @return A list of Book objects matching the title, an empty list if none
	 *         found
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<Book> searchBooksByTitle(String title) throws DAOException {
		List<Book> books = new ArrayList<>();
		String query = "SELECT book_id,title,cover_image FROM books WHERE title LIKE ? AND isActive = true";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, "%" + title + "%");

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("book_id"));
					book.setTitle(rs.getString("title"));
					book.setCoverImage(rs.getString("cover_image"));
					books.add(book);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return books;
	}

	/**
	 * Checks if a book with the given id exists in the database.
	 *
	 * @param bookId The id of the book to check.
	 * @return {@code true} if a book with the specified id exists and meets certain
	 *         criteria, {@code false} otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */

	public boolean doesBookExist(int bookId) throws DAOException {
		boolean bookExists = false;
		String query = "SELECT COUNT(*) FROM books WHERE book_id = ? AND isActive = true";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, bookId);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					bookExists = (count > 0);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return bookExists;
	}

	/**
	 * Creates a new book.
	 *
	 * @param book The Book object to be created
	 * @return true if the book is successfully created, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean createBook(Book book) throws DAOException {
		boolean hasCreated = false;
		String query = "INSERT INTO books (title, author, publisher, genre, language, description, total_copies, "
				+ "available_copies, loaned_copies, pages, cover_image) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			preparedStatement.setInt(10, book.getPages());
			preparedStatement.setString(11, book.getCoverImage());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				hasCreated = true;
			}
			return hasCreated;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retrieves a book by its ID.
	 *
	 * @param bookId The ID of the book
	 * @return The Book object if found, otherwise null
	 * @throws DAOException If an error occurs during database operation
	 */
	public Book getBookById(int bookId) throws DAOException {
		Book book = null;
		String query = "SELECT * FROM books WHERE book_id = ? AND isActive = true";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, bookId);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				if (rs.next()) {
					book = buildBookFromResultSet(rs);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return book;
	}

	/**
	 * Retrieves a list of all active books.
	 *
	 * @return A list of Book objects
	 * @throws DAOException If an error occurs during database operation
	 */
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
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return bookList;
	}

	/**
	 * Updates an existing book's information.
	 *
	 * @param book The Book object with updated information
	 * @return true if the book is successfully updated, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean updateBook(Book book) throws DAOException {
		boolean hasUpdated = false;
		String query = "UPDATE books SET " + "title = ?, description = ?, total_copies = ?, "
				+ "cover_image = ?, genre = ?, language = ?, " + "author = ?, publisher = ? " + "WHERE book_id = ?";

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
			if (rowsAffected > 0) {
				hasUpdated = true;
			}
			return hasUpdated;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Updates the loaned and available copies of a book.
	 *
	 * @param bookId              The ID of the book
	 * @param loanedCopyChange    The change in loaned copies counts (positive for
	 *                            increase, negative for decrease)
	 * @param availableCopyChange The change in available copies counts (positive
	 *                            for increase, negative for decrease)
	 * @return true if the update was successful, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean updateBookCopies(int bookId, int loanedCopyChange, int availableCopyChange) throws DAOException {
		boolean hasUpdated = false;
		String query = "UPDATE books SET loaned_copies = loaned_copies + ?, available_copies = available_copies + ? "
				+ "WHERE book_id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, loanedCopyChange);
			preparedStatement.setInt(2, availableCopyChange);
			preparedStatement.setInt(3, bookId);

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				hasUpdated = true;
			}
			return hasUpdated;
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Marks a book as inactive (soft delete) by its title.
	 *
	 * @param bookId The title of the book to be marked as inactive
	 * @return true if the book is successfully marked as inactive, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean deleteBook(int bookId) throws DAOException {
		boolean isDeleted = false;
		String query = "UPDATE books SET isActive = false WHERE book_id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, bookId);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				isDeleted = true;
			}
			return isDeleted;
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	private static final String SELECT_GENRES_QUERY = "SELECT DISTINCT genre FROM books WHERE isActive = TRUE ORDER BY genre";

	/**
	 * Retrieves a list of all distinct genres from the database.
	 *
	 * @return A list of distinct genres
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<String> getAllGenres() throws DAOException {
		List<String> genresList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(SELECT_GENRES_QUERY);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				String genre = rs.getString("genre");
				genresList.add(genre);
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}

		return genresList;
	}

}
