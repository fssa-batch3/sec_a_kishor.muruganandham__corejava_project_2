package libraryManagement.service;

import libraryManagement.DAO.UserDao;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginUser {

    private UserService userService;
    private User testUser;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        testUser = new User();
        testUser.setEmail("kishor@example.com");
        testUser.setPassword("password123");
    }

    @Test
    void testLogin() {
        try {
            User loggedInUser = userService.login(testUser);
            assertNotNull(loggedInUser);
            assertEquals(testUser.getEmail(), loggedInUser.getEmail());
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException");
        }
    }

    @Test
    void testGetAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            assertNotNull(users);
            assertFalse(users.isEmpty());
        } catch (ServiceException e) {
            Assertions.fail("Should not throw ServiceException");
        }
    }

    @Test
    void testUpdateUser() {
        try {
            testUser.setName("Updated Name");
            User updatedUser = userService.updateUser(testUser);
            assertNotEquals(testUser, updatedUser);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException");
        }
    }

    @Test
    void testDeleteUser() {
        try {
            boolean isDeleted = userService.deleteUser(testUser.getEmail());
            assertTrue(isDeleted);
            User deletedUser = UserDao.getUser(testUser.getEmail());
            assertNull(deletedUser);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException");
        }
    }
}
