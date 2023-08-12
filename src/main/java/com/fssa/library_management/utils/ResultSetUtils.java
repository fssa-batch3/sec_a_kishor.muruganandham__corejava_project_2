package com.fssa.library_management.utils;

import com.fssa.library_management.model.Book;
import com.fssa.library_management.model.Borrow;
import com.fssa.library_management.model.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResultSetUtils {

	private static final String RETURN_DATE = "return_date";
	private static final String BORROW_DATE = "borrow_date";

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

	public static Borrow buildBorrowFromResultSet(ResultSet rs) throws SQLException {
		Borrow borrow = new Borrow();
		User user = buildUserFromResultSet(rs);
		Book book = buildBookFromResultSet(rs);
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
