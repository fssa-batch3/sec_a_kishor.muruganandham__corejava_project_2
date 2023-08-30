package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.constants.ErrorMessageConstants;
import com.fssa.librarymanagement.constants.SuccessMessageConstants;
import com.fssa.librarymanagement.dao.UserDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.User;
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
	 * Register a new user.
	 *
	 * @param user The user object to be registered.
	 * @return A success message if registration is successful, or an error message
	 * if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public String registerUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator(user);
			userValidator.validateAll();

			// Check if the user already exists and is Active
			User existingUser = userDAO.getUser(user.getEmail());
			if (existingUser != null && existingUser.isActive()) {
				throw new ServiceException(ErrorMessageConstants.USER_ALREADY_EXISTS);
			}
			// Create the user in Database
			if (userDAO.createUser(user)) {
				return SuccessMessageConstants.REGISTRATION_SUCCESSFUL;
			} else {
				return ErrorMessageConstants.REGISTRATION_FAILED;
			}
		} catch (ValidationException | DAOException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessageConstants.INVALID_USER);
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
			User fromDb = userDAO.getUser(user.getEmail());
			if (fromDb == null) {
				throw new ServiceException(ErrorMessageConstants.USER_NOT_EXISTS);
			}
			// Check if the password matches
			if (user.getPassword().equals(fromDb.getPassword())) {
				return fromDb;
			} else {
				throw new ServiceException(ErrorMessageConstants.PASSWORD_MISMATCH);
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(ErrorMessageConstants.LOGIN_FAILED);
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
			return userDAO.getUserById(userId);
		} catch (DAOException e) {
			throw new ServiceException("Error retrieving user by ID", e);
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
			// Retrieve all users from the database
			return userDAO.getAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETRIEVE_USER_LIST);
		}
	}

	/**
	 * Edit a user's information.
	 *
	 * @param user The user object containing updated information.
	 * @return The updated user object.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public User editUser(User user) throws ServiceException {
		try {

			// Update the user in Database
			if (!userDAO.updateUser(user)) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_USER);
			}
			// Return the updated user object
			return userDAO.getUser(user.getEmail());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_USER);
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
			// Check if the user exists
			User existingUser = userDAO.getUser(email);
			if (existingUser == null) {
				throw new ServiceException(ErrorMessageConstants.USER_NOT_EXISTS);
			}
			// Delete the user and return true if successful
			return userDAO.deleteUser(email);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_DELETE_USER);
		}
	}
}
