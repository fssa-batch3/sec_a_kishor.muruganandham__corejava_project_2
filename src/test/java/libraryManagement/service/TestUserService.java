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
    private User user;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        user = new User();
        user.setName("Kishor");
        user.setEmail("kishor@example.com");
        user.setMobileNo(1234567890);
        user.setPassword("password123");
        user.setGender('M');
        user.setDob(LocalDate.parse("2002-06-28"));
        user.setCreatedDate(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setActive(true);
        user.setAdmin(false);
        user.setProfileImage("http://www.example.com/index.html");
    }

    @Order(1)
    @Test
    void testRegisterUser() {
        try {
            User existingUser = UserDao.getUser(user.getEmail());
            assertNull(existingUser, "User with email " + user.getEmail() + " should not exist");
            String result = userService.registerUser(user);
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
            User loggedInUser = userService.login(user);
            assertNotNull(loggedInUser);
            assertEquals(user.getEmail(), loggedInUser.getEmail());
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
            user.setName("Updated Name");
            User updatedUser = userService.updateUser(user);
            assertNotEquals(user, updatedUser);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(7)
    void testInvalidUpdateUser() {
        User nonExistentUser = new User();
        nonExistentUser.setEmail("example@example.com");
        nonExistentUser.setName("Invalid Update Name");
        assertThrows(ServiceException.class, () -> userService.updateUser(nonExistentUser));
    }

    @Test
    @Order(8)
    void testDeleteUser() {
        try {
            boolean isDeleted = userService.deleteUser(user.getEmail());
            assertTrue(isDeleted);
            User deletedUser = UserDao.getUser(user.getEmail());
            assertNull(deletedUser);
        } catch (ServiceException e) {
            e.printStackTrace();
            Assertions.fail("Should not throw ServiceException");
        }
    }


    @Test
    @Order(9)
    void testInvalidDeleteUser() {
        User nonExistentUser = new User();
        nonExistentUser.setEmail("example@example.com");
        assertThrows(ServiceException.class, () -> userService.deleteUser(nonExistentUser.getEmail()));
    }
}

