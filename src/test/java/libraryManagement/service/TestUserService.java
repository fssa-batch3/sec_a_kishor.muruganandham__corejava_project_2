package libraryManagement.service;

import libraryManagement.DAO.UserDao;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.model.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

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

    @Order(1)
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

    @Order(2)
    @Test
    void testInvalidRegisterUser() {
        User nonExistentUser = new User();
        nonExistentUser.setEmail("kishor@example.com");
        nonExistentUser.setPassword("invalid password");
        assertThrows(ServiceException.class, () -> userService.registerUser(nonExistentUser));
    }

    @Test
    @Order(3)
    void testValidLogin() {
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
    @Order(4)
    void testInvalidLogin() {
        User nonExistentUser = new User();
        nonExistentUser.setEmail("nonexistent@example.com");
        nonExistentUser.setPassword("invalid password");
        assertThrows(ServiceException.class, () -> userService.login(nonExistentUser));
    }

    @Test
    @Order(5)
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
    @Order(6)
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
    @Order(7)
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

