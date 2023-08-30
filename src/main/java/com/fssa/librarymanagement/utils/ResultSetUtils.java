package com.fssa.librarymanagement.utils;

import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class for building model objects from ResultSet data.
 */
public class ResultSetUtils {

	private static final String RETURN_DATE = "return_date";
	private static final String BORROW_DATE = "borrow_date";

	// Private constructor to prevent instantiation
	private ResultSetUtils() {
		// Do nothing (empty constructor)
	}

	/**
	 * Builds a User object from a ResultSet.
	 *
	 * @param rs The ResultSet containing user data
	 * @return A User object with data from the ResultSet
	 * @throws SQLException If a database error occurs
	 */
	public static User buildUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setName(rs.getString("user_name"));
		user.setEmail(rs.getString("email_id"));
		user.setMobileNo(rs.getLong("mobile_no"));
		user.setPassword(rs.getString("password"));
		user.setGender(rs.getString("gender").charAt(0));
		user.setDob(rs.getDate("dob").toLocalDate());
		user.setCreatedDate(rs.getTimestamp("created_date"));
		user.setActive(rs.getBoolean("isActive"));
		user.setAdmin(rs.getBoolean("isAdmin"));
		user.setProfileImage(rs.getString("profile_image"));
		return user;
	}

	/**
	 * Builds a Book object from a ResultSet.
	 *
	 * @param rs The ResultSet containing book data
	 * @return A Book object with data from the ResultSet
	 * @throws SQLException If a database error occurs
	 */
	public static Book buildBookFromResultSet(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setPublisher(rs.getString("publisher"));
		book.setGenre(rs.getString("genre"));
		book.setLanguage(rs.getString("language"));
		book.setDescription(rs.getString("description"));
		book.setTotalCopies(rs.getInt("total_copies"));
		book.setAvailableCopies(rs.getInt("available_copies"));
		book.setLoanedCopies(rs.getInt("loaned_copies"));
		book.setCoverImage(rs.getString("cover_image"));
		return book;
	}

	/**
	 * Builds a Borrow object from a ResultSet.
	 *
	 * @param rs The ResultSet containing borrow data
	 * @return A Borrow object with data from the ResultSet
	 * @throws SQLException If a database error occurs
	 */
	public static Borrow buildBorrowFromResultSet(ResultSet rs) throws SQLException {
		Borrow borrow = new Borrow();
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setName(rs.getString("user_name"));
		user.setEmail(rs.getString("email_id"));
		user.setProfileImage(rs.getString("profile_image"));
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setCoverImage(rs.getString("cover_image"));
		borrow.setUser(user);
		borrow.setBook(book);
		borrow.setBorrowDate(rs.getDate(BORROW_DATE).toLocalDate());
		Date returnDate = rs.getDate(RETURN_DATE);
		if (returnDate != null) {
			borrow.setReturnDate(returnDate.toLocalDate());
		}
		return borrow;
	}
}
