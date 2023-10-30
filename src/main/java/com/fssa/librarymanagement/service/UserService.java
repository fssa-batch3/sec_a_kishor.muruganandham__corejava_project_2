package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.constants.BookConstants;
import com.fssa.librarymanagement.constants.UserConstants;
import com.fssa.librarymanagement.dao.UserDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.User;
import com.fssa.librarymanagement.utils.PasswordUtil;
import com.fssa.librarymanagement.validation.UserValidator;

import java.util.List;

/**
 * This class provides services related to user management, such as register,
 * login, list, update, and delete.
 *
 * @author KishorMuruganandham
 */
public class UserService {

	private final UserDAO userDAO = new UserDAO();

	/**
	 * Constructs a new UserService object for handling user-related business logic
	 * and interactions.
	 */
	public UserService() {
		// Default constructor
	}

	/**
	 * Register a new user.
	 *
	 * @param user The user object to be registered.
	 * @return True if the user is registered successfully, false otherwise.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public boolean registerUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator(user);
			userValidator.validateAll();

			// Check if the user already exists and is Active
			boolean existingUser = userDAO.doesUserExist(user.getEmail());
			if (existingUser) {
				throw new ServiceException(UserConstants.USER_ALREADY_EXISTS);
			}
			user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
			return userDAO.createUser(user); // Create the user in Database
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Log in a user.
	 *
	 * @param user The user object containing login information.
	 * @return The user object if login is successful.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public User loginUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator();
			userValidator.validateEmail(user.getEmail());
			userValidator.validatePassword(user.getPassword());

			// Retrieve the user from the database
			User fromDb = userDAO.getUserByEmail(user.getEmail());
			if (fromDb == null) {
				throw new ServiceException(UserConstants.USER_DOES_NOT_EXIST_WITH_THE_GIVEN_EMAIL);
			}
			// Check if the password matches
			if (PasswordUtil.checkPassword(user.getPassword(), fromDb.getPassword())) {
				return fromDb;
			} else {
				throw new ServiceException(UserConstants.INVALID_LOGIN_CREDENTIALS);
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates the password for a user if the old password is correct.
	 *
	 * @param email       The email of the user whose password needs to be updated
	 * @param oldPassword The old password to verify before updating
	 * @param newPassword The new password to set for the user
	 * @return True if the password was successfully updated, false otherwise
	 * @throws ServiceException If there's a problem with the service, including
	 *                          incorrect old password or database errors
	 */
	public boolean updatePassword(String email, String oldPassword, String newPassword) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator();
			userValidator.validateEmail(email);
			userValidator.validatePassword(oldPassword);
			userValidator.validatePassword(newPassword);

			if (oldPassword.equals(newPassword)) {
				throw new ServiceException(UserConstants.PASSWORDS_CANNOT_BE_SAME);
			}
			User fromDb = userDAO.getUserByEmail(email); // Retrieve the user from the database by email

			if (fromDb == null) {
				throw new ServiceException(UserConstants.USER_NOT_FOUND);
			}

			// Check if the provided old password matches the user's current password
			if (PasswordUtil.checkPassword(oldPassword, fromDb.getPassword())) {
				return userDAO.updatePassword(email, newPassword);
			} else {
				throw new ServiceException(UserConstants.INCORRECT_OLD_PASSWORD);
			}
		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieve a user.
	 *
	 * @param userId The user id of the user to be Retrieved.
	 * @return The user object if login is successful.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public User getUserById(int userId) throws ServiceException {
		try {
			User user = userDAO.getUserById(userId);
			if (user == null) {
				throw new ServiceException(UserConstants.USER_NOT_FOUND);
			}
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieve a user by their email.
	 *
	 * @param email The email of the user to be retrieved.
	 * @return The user object if found, or null if not found.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public User getUserByEmail(String email) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator();
			userValidator.validateEmail(email);
			User user = userDAO.getUserByEmail(email);
			if (user == null) {
				throw new ServiceException(UserConstants.USER_DOES_NOT_EXIST_WITH_THE_GIVEN_EMAIL);
			}
			return user;
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Get a list of all users.
	 *
	 * @return A list of user objects.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public List<User> listAllUser() throws ServiceException {
		try {
			List<User> users = userDAO.getAllUsers(); // Retrieve all users from the database

			// Check if the list is not null and has elements
			if (users != null && !users.isEmpty()) {
				return users;
			} else {
				throw new ServiceException(BookConstants.BOOK_NOT_FOUND);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Edit a user's information.
	 *
	 * @param user The user object containing updated information.
	 * @return True if the user is updated successfully, false otherwise.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public boolean editUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator(user);
			userValidator.validateName(user.getName());
			userValidator.validateProfileImage(user.getProfileImage());
			userValidator.validateDateOfBirth(user.getDob());
			userValidator.validateGender(user.getGender());
			userValidator.validateMobileNo(user.getMobileNo());

			boolean userExist = userDAO.doesUserExist(user.getEmail()); // Check if the user exists
			if (!userExist) {
				throw new ServiceException(UserConstants.USER_DOES_NOT_EXIST_WITH_THE_GIVEN_EMAIL);
			}

			return userDAO.updateUser(user); // Update the user and return true if successful
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Delete a user.
	 *
	 * @param email The email of the user to be deleted.
	 * @return True if the user is deleted successfully, false otherwise.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public boolean deleteUser(String email) throws ServiceException {
		try {

			boolean userExist = userDAO.doesUserExist(email); // Check if the user exists
			if (!userExist) {
				throw new ServiceException(UserConstants.USER_DOES_NOT_EXIST_WITH_THE_GIVEN_EMAIL);
			}

			return userDAO.deleteUser(email); // Delete the user and return true if successful
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
