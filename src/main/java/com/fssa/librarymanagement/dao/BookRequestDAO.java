/**
 * 
 */
package com.fssa.librarymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.BookRequest;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * Data Access Object (DAO) class for handling book request-related database operations.
 */
public class BookRequestDAO {

	/**
	 * Constructs a new BookRequestDAO object for performing database operations
	 * related to book requests.
	 */
	public BookRequestDAO() {
		// Default constructor
	}

	/**
	 * Create a new book request.
	 *
	 * @param bookRequestData The data for the book request to be created.
	 * @return true if the book request is successfully created, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	
	public boolean createBookRequest(BookRequest bookRequestData) throws DAOException {
		boolean hasCreated = false;
		String query = "INSERT INTO book_requests (book_name, author_name, source_link, description) "
				+ "VALUES (?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, bookRequestData.getBookName());
			preparedStatement.setString(2, bookRequestData.getAuthorName());
			preparedStatement.setString(3, bookRequestData.getSourceLink());
			preparedStatement.setString(4, bookRequestData.getDescription());

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
	 * Retrieve a list of all book requests.
	 *
	 * @return A list of all book requests in the database.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	
	public List<BookRequest> getAllBookRequests() throws DAOException {
		List<BookRequest> bookRequestList = new ArrayList<>();
		String query = "SELECT * FROM book_requests";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery()) {

			while (rs.next()) {
				BookRequest bookRequestData = new BookRequest();
				bookRequestData.setBookName(rs.getString("book_name"));
				bookRequestData.setAuthorName(rs.getString("author_name"));
				bookRequestData.setSourceLink(rs.getString("source_link"));
				bookRequestData.setDescription(rs.getString("description"));

				bookRequestList.add(bookRequestData);
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return bookRequestList;
	}
}
