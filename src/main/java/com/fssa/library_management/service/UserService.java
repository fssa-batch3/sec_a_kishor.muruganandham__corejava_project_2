package com.fssa.library_management.service;

import com.fssa.library_management.dao.UserDAO;
import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.User;
import com.fssa.library_management.validation.UserValidator;

import java.util.List;

public class UserService {
    private static final String USER_NOT_EXISTS = "User not exists";

    public String registerUser(User user) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator(user);
            userValidator.validateAll();

            User existingUser = UserDAO.getUser(user.getEmail());
            if (existingUser != null && existingUser.isActive()) {
                throw new ServiceException("Email id " + user.getEmail() + " is already registered");
            }
            if (UserDAO.createUser(user)) {
                return "Registration Successful";
            } else {
                return "Registration Failed";
            }
        } catch (ValidationException | DAOException e) {
            throw new ServiceException("Invalid User", e);
        }

    }

    public User loginUser(User user) throws ServiceException {
        try {
            UserValidator userValidator = new UserValidator();
            userValidator.validateEmail(user.getEmail());
            userValidator.validatePassword(user.getPassword());

            User fromDb = UserDAO.getUser(user.getEmail());
            if (fromDb == null) {
                throw new ServiceException("User Not Found");
            }
            if (user.getPassword().equals(fromDb.getPassword())) {
                return fromDb;
            } else {
                throw new ServiceException("Password Mismatch");
            }
        } catch (ValidationException | DAOException e) {
            throw new ServiceException("Login Failed");
        }

    }

    public List<User> listAllUser() throws ServiceException {
        try {
            return UserDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException("Failed to Retrieve User List.");
        }
    }

    public User editUser(User user) throws ServiceException {
        try {
            User existingUser = UserDAO.getUser(String.valueOf(user.getEmail()));
            if (existingUser == null) {
                throw new ServiceException(USER_NOT_EXISTS);
            }
            if (!UserDAO.updateUser(user)) {
                throw new ServiceException("User Update Failed");
            }
            return UserDAO.getUser(String.valueOf(user.getEmail()));
        } catch (DAOException e) {
            throw new ServiceException("Failed to Update User");
        }
    }

    public boolean deleteUser(String email) throws ServiceException {
        try {
            User existingUser = UserDAO.getUser(email);
            if (existingUser == null || !existingUser.isActive()) {
                throw new ServiceException(USER_NOT_EXISTS);
            }
            return UserDAO.deleteUser(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
