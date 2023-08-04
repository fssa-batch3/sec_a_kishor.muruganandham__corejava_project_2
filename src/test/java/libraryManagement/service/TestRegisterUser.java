package libraryManagement.service;

import libraryManagement.DAO.UserDao;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestRegisterUser {

    private UserService userService;
    private User testUser;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        testUser = new User();
        testUser.setName("Kishor");
        testUser.setEmail("kishor@example.com");
        testUser.setMobileNo(1234567890);
        testUser.setPassword("password123");
        testUser.setGender('M');
        testUser.setDob(LocalDate.parse("2002-06-28"));
        testUser.setCreatedDate(new java.sql.Timestamp(System.currentTimeMillis()));
        testUser.setActive(true);
        testUser.setAdmin(false);
        testUser.setProfileImage("http://www.example.com/index.html");
    }


    @Test
    void testRegisterUser() {
        try {
            User existingUser = UserDao.getUser(testUser.getEmail());
            assertNull(existingUser, "User with email " + testUser.getEmail() + " should not exist");
            String result = userService.registerUser(testUser);
            assertEquals("Registration Successful", result);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException or DAOException");
        }

    }

    @Test
    void testInvalidRegisterUser() {
        try {
            User existingUser = UserDao.getUser(testUser.getEmail());
            assertNotNull(existingUser, "User with email " + testUser.getEmail() + " should not exist");

            String result = userService.registerUser(testUser);
            assertNotEquals("Registration Successful", result);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException or DAOException");
        }
    }


}
