package com.fssa.library_management.service;

import com.fssa.library_management.constants.ErrorMessageConstants;
import com.fssa.library_management.constants.SuccessMessageConstants;
import com.fssa.library_management.dao.UserDAO;
import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.User;
import com.fssa.library_management.validation.UserValidator;

import java.util.List;

public class UserService {


	public String registerUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator(user);
			userValidator.validateAll();

			User existingUser = UserDAO.getUser(user.getEmail());
			if (existingUser != null && existingUser.isActive()) {
				throw new ServiceException(ErrorMessageConstants.USER_ALREADY_EXISTS);
			}
			if (UserDAO.createUser(user)) {
				return SuccessMessageConstants.REGISTRATION_SUCCESSFUL;
			} else {
				return ErrorMessageConstants.REGISTRATION_FAILED;
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(ErrorMessageConstants.INVALID_USER);
		}

	}

	public User loginUser(User user) throws ServiceException {
		try {
			UserValidator userValidator = new UserValidator();
			userValidator.validateEmail(user.getEmail());
			userValidator.validatePassword(user.getPassword());

			User fromDb = UserDAO.getUser(user.getEmail());
			if (fromDb == null) {
				throw new ServiceException(ErrorMessageConstants.USER_NOT_EXISTS);
			}
			if (user.getPassword().equals(fromDb.getPassword())) {
				return fromDb;
			} else {
				throw new ServiceException(ErrorMessageConstants.PASSWORD_MISMATCH);
			}
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(ErrorMessageConstants.LOGIN_FAILED);
		}

	}

	public List<User> listAllUser() throws ServiceException {
		try {
			return UserDAO.getAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETRIEVE_USER_LIST);
		}
	}

	public User editUser(User user) throws ServiceException {
		try {
			User existingUser = UserDAO.getUser(String.valueOf(user.getEmail()));
			if (existingUser == null) {
				throw new ServiceException(ErrorMessageConstants.USER_NOT_EXISTS);
			}
			if (!UserDAO.updateUser(user)) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_USER);
			}
			return UserDAO.getUser(String.valueOf(user.getEmail()));
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_USER);
		}
	}

	public boolean deleteUser(String email) throws ServiceException {
		try {
			User existingUser = UserDAO.getUser(email);
			if (existingUser == null) {
				throw new ServiceException(ErrorMessageConstants.USER_NOT_EXISTS);
			}
			return UserDAO.deleteUser(email);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_DELETE_USER);
		}
	}
}
