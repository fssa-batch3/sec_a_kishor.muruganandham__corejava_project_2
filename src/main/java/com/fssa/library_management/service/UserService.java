package com.fssa.library_management.service;

import com.fssa.library_management.dao.UserDao;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.User;
import com.fssa.library_management.validation.ValidateUser;

import java.util.List;

public class UserService {
    private static final String USER_WITH_EMAIL = "User with email ";

    public String registerUser(User user) throws ServiceException {
        try {
            ValidateUser validateUser = new ValidateUser(user);
            validateUser.validateAll();
        } catch (ValidationException e) {
            throw new ServiceException("Invalid User", e);
        }

        User existingUser = UserDao.getUser(user.getEmail());
        if (existingUser != null && existingUser.isActive()) {
            return "Email id " + user.getEmail() + " is already registered";
        } else {
            if (UserDao.createUser(user)) {
                return "Registration Successful";
            } else {
                return "Registration Failed";
            }
        }

    }

    public User login(User user) throws ServiceException {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                throw new ServiceException("Invalid User Credentials");
            }
            if (!ValidateUser.validateEmail(user.getEmail())) {
                throw new ValidationException("Invalid Email");
            }
            User fromDb = UserDao.getUser(user.getEmail());
            if (fromDb != null && user.getPassword().equals(fromDb.getPassword())) {
                return fromDb;
            } else {
                throw new ServiceException("User Not Found");
            }
        } catch (ServiceException | ValidationException e) {
            throw new ServiceException(e);
        }

    }

    public List<User> getAllUsers() throws ServiceException {
        return UserDao.getAllUsers();
    }

    public User updateUser(User user) throws ServiceException {
        User existingUser = UserDao.getUser(String.valueOf(user.getEmail()));
        if (existingUser == null) {
            throw new ServiceException(USER_WITH_EMAIL + user.getEmail() + " does not exist.");
        }
        return UserDao.updateUser(user);
    }

    public boolean deleteUser(String stringValue) throws ServiceException {
        User existingUser = UserDao.getUser(stringValue);
        if (existingUser == null) {
            throw new ServiceException(USER_WITH_EMAIL + stringValue + " does not exist.");
        }
        if (!existingUser.isActive()) {
            throw new ServiceException(USER_WITH_EMAIL + stringValue + " is already inactive.");
        }
        return UserDao.deleteUser(stringValue);
    }
}
