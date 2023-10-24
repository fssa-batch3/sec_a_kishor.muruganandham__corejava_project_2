/**
 * 
 */
package com.fssa.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.model.User;
import com.fssa.librarymanagement.utils.ConnectionUtil;

/**
 * 
 */
class TestUserDAO {

	private UserDAO userDAO;

	@BeforeEach
	public void setUp() {
		userDAO = new UserDAO();
	}

	@Test
	void testCreateUser() {
		ConnectionUtil.setTestingMode(true);
		User user = new User();
		assertThrows(DAOException.class, () -> userDAO.createUser(user));

		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testDeleteUser() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.deleteUser(""));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testUpdateUser() {
		ConnectionUtil.setTestingMode(true);
		User user = new User();
		assertThrows(DAOException.class, () -> userDAO.updateUser(user));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testDoesUserExist() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.doesUserExist(""));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetAllUsers() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.getAllUsers());
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetUserByEmail() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.getUserByEmail(""));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testGetUserById() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.getUserById(0));
		ConnectionUtil.setTestingMode(false);
	}

	@Test
	void testUpdatePassword() {
		ConnectionUtil.setTestingMode(true);

		assertThrows(DAOException.class, () -> userDAO.updatePassword("", ""));
		ConnectionUtil.setTestingMode(false);
	}

}
