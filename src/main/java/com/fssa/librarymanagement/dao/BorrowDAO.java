package com.fssa.librarymanagement.dao;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildBorrowFromResultSet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.utils.ConnectionUtil;

public class BorrowDAO {

	static final String SELECT_QUERY_PREFIX = "SELECT user_id, book_id, borrow_date, return_date FROM borrows ";
	static final String JOIN_QUERY = "SELECT b.*, u.user_name, u.user_id, u.email_id, u.profile_image, bk.book_id, " +
			"bk" +
			".title, bk.cover_image FROM borrows b " +
			"JOIN users u ON b.user_id = u.user_id " +
			"JOIN books bk ON b.book_id = bk.book_id ";

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

		} catch (SQLException e) {
			throw new DAOException("Error fetching borrow data: " + e.getMessage(), e);
		}
	}

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
		} catch (SQLException e) {
			throw new DAOException("Error fetching return data: " + e.getMessage(), e);
		}
	}


	public List<Borrow> getBorrowsByUser(int userId) throws DAOException {
		List<Borrow> borrowList = null;

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(JOIN_QUERY + "WHERE b.user_id = ?")) {

			pst.setInt(1, userId);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					borrowList = new ArrayList<>();
					Borrow borrow = buildBorrowFromResultSet(rs);
					borrowList.add(borrow);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error fetching borrow data of the user: " + e.getMessage(), e);
		}
		return borrowList;
	}

	public List<Borrow> getBorrowsByBook(int bookId) throws DAOException {
		List<Borrow> borrowList = null;


		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(JOIN_QUERY + "WHERE b.book_id = ?")) {
			pst.setInt(1, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					borrowList = new ArrayList<>();
					Borrow borrow = buildBorrowFromResultSet(rs);
					borrowList.add(borrow);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error fetching borrow data of the book: " + e.getMessage(), e);
		}
		return borrowList;
	}

	public Borrow getBorrowByUserAndBook(int userId, int bookId) throws DAOException {
		String query = SELECT_QUERY_PREFIX + "WHERE user_id = ? AND book_id = ? AND isReturned = FALSE";

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, userId);
			pst.setInt(2, bookId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return buildBorrowFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error fetching borrow data of user and book: " + e.getMessage(), e);
		}
		return null;
	}

	public List<Borrow> getAllBorrows() throws DAOException {
		List<Borrow> borrowList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection();
		     PreparedStatement pst = connection.prepareStatement(JOIN_QUERY);
		     ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				Borrow borrow = buildBorrowFromResultSet(rs);
				borrowList.add(borrow);
			}
		} catch (SQLException e) {
			throw new DAOException("Error fetching all borrow data's: " + e.getMessage(), e);
		}
		return borrowList;
	}

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
		} catch (SQLException e) {
			throw new DAOException("Error fetching available copies count: " + e.getMessage(), e);
		}
		return 0;
	}

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
		} catch (SQLException e) {
			throw new DAOException("Error fetching borrowed books count: " + e.getMessage(), e);
		}
		return 0;
	}
}
