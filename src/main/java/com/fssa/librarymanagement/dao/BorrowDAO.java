package com.fssa.librarymanagement.dao;

import com.fssa.librarymanagement.constants.BorrowConstants;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.User;
import com.fssa.librarymanagement.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildBorrowFromResultSet;

/**
 * Data Access Object (DAO) class for handling Borrow-related database operations.
 */
public class BorrowDAO {


	/**
	 * Constructs a new BorrowDAO object for performing database operations related to borrow transactions.
	 */
	public BorrowDAO() {
		// Default constructor
	}

	/**
	 * Borrows a book for a user.
	 *
	 * @param borrow The Borrow object representing the book borrow
	 * @return true if the book is successfully borrowed, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean borrowBook(Borrow borrow) throws DAOException {
		String query = "INSERT INTO borrows (user_id, book_id, borrow_date, due_date) " +
				"VALUES (?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, borrow.getUser().getUserId());
			pst.setInt(2, borrow.getBook().getBookId());
			pst.setDate(3, Date.valueOf(borrow.getBorrowDate()));
			pst.setDate(4, Date.valueOf(borrow.getDueDate()));

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Returns a borrowed book.
	 *
	 * @param borrow The Borrow object representing the book return
	 * @return true if the book is successfully returned, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean returnBook(Borrow borrow) throws DAOException {
		String query = "UPDATE borrows SET isReturned = true, return_date = ?, fine = ? WHERE user_id = ? AND " +
				"book_id" +
				" " +
				"= ? AND isReturned = false";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setDate(1, Date.valueOf(borrow.getReturnDate()));
			pst.setDouble(2, borrow.getFine());
			pst.setInt(3, borrow.getUser().getUserId());
			pst.setInt(4, borrow.getBook().getBookId());

			int rowsAffected = pst.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retrieves a list of borrows by a user.
	 *
	 * @param userId The ID of the user
	 * @return A list of Borrow objects for the user
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<Borrow> getBorrowsByUser(int userId) throws DAOException {
		List<Borrow> borrowList = null;

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(BorrowConstants.JOIN_QUERY + "WHERE b.user_id = ?")) {

			pst.setInt(1, userId);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					borrowList = new ArrayList<>();
					Borrow borrow = buildBorrowFromResultSet(rs);
					borrowList.add(borrow);
				}
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return borrowList;
	}

	/**
	 * Retrieves a list of borrows for a book.
	 *
	 * @param bookId The ID of the book
	 * @return A list of Borrow objects for the book
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<Borrow> getBorrowsByBook(int bookId) throws DAOException {
		List<Borrow> borrowList = null;


		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(BorrowConstants.JOIN_QUERY + "WHERE b.book_id = ?")) {
			pst.setInt(1, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					borrowList = new ArrayList<>();
					Borrow borrow = buildBorrowFromResultSet(rs);
					borrowList.add(borrow);
				}
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return borrowList;
	}

	/**
	 * Retrieves a specific borrow by user ID and book ID.
	 *
	 * @param userId The ID of the user
	 * @param bookId The ID of the book
	 * @return The Borrow object if found, otherwise null
	 * @throws DAOException If an error occurs during database operation
	 */
	public Borrow getBorrowByUserAndBook(int userId, int bookId) throws DAOException {
		String query = BorrowConstants.SELECT_QUERY_PREFIX + "WHERE user_id = ? AND book_id = ? AND isReturned = " +
				"FALSE";

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, userId);
			pst.setInt(2, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					Borrow borrow = new Borrow();
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					Book book = new Book();
					book.setBookId(rs.getInt("book_id"));
					borrow.setUser(user);
					borrow.setBook(book);
					borrow.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
					Date returnDate = rs.getDate("return_date");
					if (returnDate != null) {
						borrow.setReturnDate(returnDate.toLocalDate());
					}
					return borrow;
				}
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return null;
	}

	/**
	 * Retrieves a list of all borrows.
	 *
	 * @return A list of all Borrow objects
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<Borrow> getAllBorrows() throws DAOException {
		List<Borrow> borrowList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(BorrowConstants.JOIN_QUERY);
		     ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				Borrow borrow = buildBorrowFromResultSet(rs);
				borrowList.add(borrow);
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return borrowList;
	}

	/**
	 * Retrieves the count of available copies for a book.
	 *
	 * @param bookId The ID of the book
	 * @return The count of available copies for the book
	 * @throws DAOException If an error occurs during database operation
	 */
	public int getAvailableCopiesCount(int bookId) throws DAOException {
		String query = "SELECT available_copies FROM books WHERE book_id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return 0;
	}

	/**
	 * Retrieves the count of borrowed books by a user.
	 *
	 * @param userId The ID of the user
	 * @return The count of borrowed books for the user
	 * @throws DAOException If an error occurs during database operation
	 */
	public int getBorrowedBooksCountByUser(int userId) throws DAOException {
		String query = "SELECT COUNT(*) FROM borrows WHERE user_id = ? AND isReturned = false";
		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {
			pst.setInt(1, userId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return 0;
	}
}
