package com.fssa.librarymanagement.utils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.fssa.librarymanagement.model.Book;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.model.Comment;
import com.fssa.librarymanagement.model.User;

/**
 * Utility class for building model objects from ResultSet data.
 */
public class ResultSetUtils {

	/**
	 * 
	 */
	private static final String PROFILE_IMAGE = "profile_image";
	/**
	 * 
	 */
	private static final String EMAIL_ID = "email_id";
	/**
	 * 
	 */
	private static final String USER_NAME = "user_name";
	/**
	 * 
	 */
	private static final String USER_ID = "user_id";
	/**
	 * 
	 */
	private static final String COVER_IMAGE = "cover_image";
	/**
	 * 
	 */
	private static final String TITLE = "title";
	/**
	 * 
	 */
	private static final String BOOK_ID = "book_id";
	private static final String RETURN_DATE = "return_date";
	private static final String BORROW_DATE = "borrow_date";
	private static final String DUE_DATE = "due_date";

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
		user.setUserId(rs.getInt(USER_ID));
		user.setName(rs.getString(USER_NAME));
		user.setEmail(rs.getString(EMAIL_ID));
		user.setMobileNo(rs.getLong("mobile_no"));
		user.setPassword(rs.getString("password"));
		user.setGender(rs.getString("gender").charAt(0));
		user.setDob(rs.getDate("dob").toLocalDate());
		user.setCreatedDate(rs.getTimestamp("created_date"));
		user.setActive(rs.getBoolean("isActive"));
		user.setAdmin(rs.getBoolean("isAdmin"));
		user.setProfileImage(rs.getString(PROFILE_IMAGE));
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
		book.setBookId(rs.getInt(BOOK_ID));
		book.setTitle(rs.getString(TITLE));
		book.setAuthor(rs.getString("author"));
		book.setPublisher(rs.getString("publisher"));
		book.setGenre(rs.getString("genre"));
		book.setLanguage(rs.getString("language"));
		book.setDescription(rs.getString("description"));
		book.setTotalCopies(rs.getInt("total_copies"));
		book.setAvailableCopies(rs.getInt("available_copies"));
		book.setLoanedCopies(rs.getInt("loaned_copies"));
		book.setPages(rs.getInt("pages"));
		book.setCoverImage(rs.getString(COVER_IMAGE));
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
		user.setUserId(rs.getInt(USER_ID));
		user.setName(rs.getString(USER_NAME));
		user.setEmail(rs.getString(EMAIL_ID));
		user.setProfileImage(rs.getString(PROFILE_IMAGE));
		Book book = new Book();
		book.setBookId(rs.getInt(BOOK_ID));
		book.setTitle(rs.getString(TITLE));
		book.setCoverImage(rs.getString(COVER_IMAGE));
		borrow.setBorrowId(rs.getInt("borrow_id"));
		borrow.setReturned(rs.getBoolean("isReturned"));
		borrow.setUser(user);
		borrow.setBook(book);
		borrow.setBorrowDate(rs.getTimestamp(BORROW_DATE).toLocalDateTime());
		borrow.setFine(rs.getDouble("fine"));
		borrow.setDueDate(rs.getDate(DUE_DATE).toLocalDate());
		Date returnDate = rs.getDate(RETURN_DATE);
		if (returnDate != null) {
			borrow.setReturnDate(returnDate.toLocalDate());
		}
		return borrow;
	}

	public static Comment buildCommentFromResultSet(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setCommentId(rs.getInt("comment_id"));

		User user = new User();
		user.setUserId(rs.getInt(USER_ID));
		user.setName(rs.getString(USER_NAME));
		user.setEmail(rs.getString(EMAIL_ID));
		user.setProfileImage(rs.getString(PROFILE_IMAGE));

		Book book = new Book();
		book.setBookId(rs.getInt(BOOK_ID));
		book.setTitle(rs.getString(TITLE));
		book.setCoverImage(rs.getString(COVER_IMAGE));

		comment.setUser(user);
		comment.setBook(book);
		comment.setDescription(rs.getString("description"));

		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		comment.setCreatedAt(createdAt);

		if (rs.getDate("edited_at") != null) {
			LocalDateTime editedAt = rs.getTimestamp("edited_at").toLocalDateTime();
			comment.setEditedAt(editedAt);
		}

		comment.setActive(rs.getBoolean("is_active"));
		comment.setEdited(rs.getBoolean("is_edited"));
		comment.setTrusted(rs.getBoolean("is_trusted"));

		return comment;
	}

}
