package com.fssa.librarymanagement.dao;

import static com.fssa.librarymanagement.utils.ResultSetUtils.buildUserFromResultSet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.librarymanagement.constants.UserConstants;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;
import com.fssa.librarymanagement.model.User;
import com.fssa.librarymanagement.utils.ConnectionUtil;
import com.fssa.librarymanagement.utils.PasswordUtil;

/**
 * Data Access Object (DAO) class for handling User-related database operations.
 */
public class UserDAO {

	/**
	 * Constructs a new UserDAO object for performing database operations related to
	 * users.
	 */
	public UserDAO() {
		// Default constructor
	}

	/**
	 * Creates a new user.
	 *
	 * @param user The User object representing the user to be created
	 * @return true if the user is successfully created, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean createUser(User user) throws DAOException {
		boolean hasCreated = false;
		String query = "INSERT INTO users (user_name, email_id, mobile_no, password, gender, dob, created_date, "
				+ "isActive, isAdmin, profile_image) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, user.getName());
			pst.setString(2, user.getEmail());
			pst.setLong(3, user.getMobileNo());
			pst.setString(4, user.getPassword());
			pst.setString(5, String.valueOf(user.getGender()));
			pst.setDate(6, Date.valueOf(user.getDob()));
			pst.setTimestamp(7, user.getCreatedDate());
			pst.setBoolean(8, true);
			pst.setBoolean(9, user.isAdmin());
			pst.setString(10, user.getProfileImage());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				hasCreated = true;
			}
			return hasCreated;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Checks if a user with the given email exists.
	 *
	 * @param email The email of the user to check for existence.
	 * @return True if the user with the given email exists and is active, otherwise
	 *         false.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean doesUserExist(String email) throws DAOException {
		String query = "SELECT 1 FROM users WHERE email_id = ? AND isActive = true";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				return rs.next(); // If a row is found, the user exists
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param userId The ID of the user to retrieve
	 * @return The User object if found, otherwise null
	 * @throws DAOException If an error occurs during database operation
	 */
	public User getUserById(int userId) throws DAOException {
		User user = null;
		String query = UserConstants.SELECT_QUERY + " WHERE user_id = ? AND isActive = true";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, userId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					user = buildUserFromResultSet(rs);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return user;
	}

	/**
	 * Retrieves a user by their email.
	 *
	 * @param email The email of the user to retrieve
	 * @return The User object if found, otherwise null
	 * @throws DAOException If an error occurs during database operation
	 */
	public User getUserByEmail(String email) throws DAOException {
		User user = null;
		String query = UserConstants.SELECT_QUERY + " WHERE email_id = ? AND isActive = true";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					user = buildUserFromResultSet(rs);
				}
			}

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return user;
	}

	/**
	 * Updates the password of a user with the given email.
	 *
	 * @param email       The email of the user whose password needs to be updated
	 * @param newPassword The new password to set for the user
	 * @return True if the password was successfully updated, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean updatePassword(String email, String newPassword) throws DAOException {
		boolean hasUpdated = false;
		String updateQuery = "UPDATE users SET password = ? WHERE email_id = ?";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			String hashedPassword = PasswordUtil.hashPassword(newPassword);
			preparedStatement.setString(1, hashedPassword);
			preparedStatement.setString(2, email);

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
	 * Retrieves a list of all users.
	 *
	 * @return A list of all User objects
	 * @throws DAOException If an error occurs during database operation
	 */
	public List<User> getAllUsers() throws DAOException {
		List<User> userList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(UserConstants.SELECT_QUERY);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				User user = buildUserFromResultSet(rs);
				userList.add(user);
			}
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
		return userList;
	}

	/**
	 * Updates user information.
	 *
	 * @param user The User object representing the updated user information
	 * @return true if the user information is successfully updated, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean updateUser(User user) throws DAOException {
		String query = "UPDATE users SET " + "user_name = ?, profile_image = ?, mobile_no = ?, gender = ?, dob = ? "
				+ "WHERE email_id = ?";
		boolean hasUpdated = false;
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, user.getName());
			pst.setString(2, user.getProfileImage());
			pst.setLong(3, user.getMobileNo());
			pst.setString(4, String.valueOf(user.getGender()));
			pst.setDate(5, Date.valueOf(user.getDob()));
			pst.setString(6, user.getEmail());

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				hasUpdated = true;
			}
			return hasUpdated;

		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Deletes a user by their email.
	 *
	 * @param stringValue The email of the user to be deleted
	 * @return true if the user is successfully deleted, false otherwise
	 * @throws DAOException If an error occurs during database operation
	 */
	public boolean deleteUser(String stringValue) throws DAOException {
		boolean isDeleted = false;
		String query = "UPDATE users SET isActive = false WHERE email_id = ?";
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, stringValue);

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				isDeleted = true;
			}
			return isDeleted;
		} catch (SQLException | DatabaseConnectionException e) {
			throw new DAOException(e);
		}
	}

}
