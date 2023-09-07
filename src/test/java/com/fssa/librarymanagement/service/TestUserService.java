package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.model.User;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
		user.setEmail("kishor.muruganandham@gmail.com");
		user.setMobileNo(8925603157L);
		user.setPassword("12345Kishor");
		user.setGender('M');
		user.setDob(LocalDate.parse("2002-06-28"));
		user.setProfileImage("https://ca.slack-edge.com/T032648LE-U042JUJR1SL-232f124dd448-512");
	}

	@Test
	@Order(1)
	void testValidRegisterUser() {
		assertDoesNotThrow(() -> userService.registerUser(user));
	}

	@Test
	@Order(2)
	void testInvalidRegisterUser() {
		User existentUser = new User();
		existentUser.setEmail("kishor.muruganandham@gmail.com");
		existentUser.setName("Kishor");
		existentUser.setMobileNo(8925603157L);
		existentUser.setPassword("12345Kishor");
		existentUser.setGender('M');
		existentUser.setDob(LocalDate.parse("2002-06-28"));
		existentUser.setProfileImage("https://ca.slack-edge.com/T032648LE-U042JUJR1SL-232f124dd448-512");
		ServiceException result = assertThrows(ServiceException.class, () -> userService.registerUser(existentUser));
		assertEquals("User already exists.", result.getMessage());
	}

	@Test
	@Order(3)
	void testValidLogin() {
		assertDoesNotThrow(() -> userService.loginUser(user));
	}

	@Test
	@Order(4)
	void testInvalidLogin() {
		User nonExistentUser = new User();
		nonExistentUser.setEmail("nonexistent@gmail.com");
		nonExistentUser.setPassword("12345Kishor");
		ServiceException result = assertThrows(ServiceException.class, () -> userService.loginUser(nonExistentUser));
		assertEquals("User does not exist with the given email", result.getMessage());
	}

	@Test
	@Order(5)
	void testInvalidLoginMismatchPassword() {
		user.setPassword("12345Kishore");
		ServiceException result = assertThrows(ServiceException.class, () -> userService.loginUser(user));
		assertEquals("Password mismatch.", result.getMessage());
	}

	@Test
	@Order(6)
	void testValidGetUserById() {
		assertDoesNotThrow(() -> userService.getUserById(1));
	}

	@Test
	@Order(7)
	void testInvalidGetUserById() {
		ServiceException result = assertThrows(ServiceException.class, () -> userService.getUserById(304));
		assertEquals("User not found", result.getMessage());
	}

	@Test
	@Order(8)
	void testValidGetUserByEmail() {
		assertDoesNotThrow(() -> userService.getUserByEmail(user.getEmail()));
	}

	@Test
	@Order(9)
	void testInvalidGetUserByEmail() {
		ServiceException result = assertThrows(ServiceException.class,
		                                       () -> userService.getUserByEmail("kishor@yahoo.com"));
		assertEquals("User does not exist with the given email", result.getMessage());
	}

	@Test
	@Order(10)
	void testGetAllUsers() {
		assertDoesNotThrow(() -> userService.listAllUser());
	}

	@Test
	@Order(11)
	void testValidUpdateUser() {
		try {
			user.setName("Kishore");
			boolean result = userService.editUser(user);
			User updatedUser = userService.getUserByEmail(user.getEmail());
			System.out.println(updatedUser);
			assertTrue(result);
			assertNotEquals("Kishor", updatedUser.getName());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Order(12)
	void testInvalidUpdateUser() {
		User nonExistentUser = new User();
		nonExistentUser.setEmail("nonExistentUser@gmail.com");
		nonExistentUser.setName("Kishor");
		nonExistentUser.setMobileNo(8925603157L);
		nonExistentUser.setGender('M');
		nonExistentUser.setDob(LocalDate.parse("2002-06-28"));
		nonExistentUser.setProfileImage("https://ca.slack-edge.com/T032648LE-U042JUJR1SL-232f124dd448-512");
		ServiceException result = assertThrows(ServiceException.class, () -> userService.editUser(nonExistentUser));
		assertEquals("User does not exist with the given email", result.getMessage());
	}


	@Test
	@Order(13)
	void testValidUpdatePassword() {
		String newPassword = "12345Kishore";
		assertDoesNotThrow(() -> userService.updatePassword(user.getEmail(), user.getPassword(), newPassword));
	}

	@Test
	@Order(14)
	void testInvalidUpdatePassword_SamePassword() {
		String newPassword = "12345Kishor";
		ServiceException result = assertThrows(ServiceException.class,
		                                       () -> userService.updatePassword(user.getEmail(), user.getPassword(),
		                                                                        newPassword));
		assertEquals("Old Password and New Password Cannot be same", result.getMessage());
	}


	@Test
	@Order(15)
	void testInvalidUpdatePassword_wrongOldPassword() {
		String newPassword = "12345Kishore";
		user.setPassword("123456Kishor");
		ServiceException result = assertThrows(ServiceException.class,
		                                       () -> userService.updatePassword(user.getEmail(), user.getPassword(),
		                                                                        newPassword));
		assertEquals("Incorrect old password.", result.getMessage());
	}

	@Test
	@Order(16)
	void testInvalidUpdatePassword_nonExistentUser() {
		String newPassword = "12345Kishore";
		user.setEmail("nonExistentUser@gmail.com");
		ServiceException result = assertThrows(ServiceException.class,
		                                       () -> userService.updatePassword(user.getEmail(), user.getPassword(),
		                                                                        newPassword));
		assertEquals("User not found", result.getMessage());
	}

	@Test
	@Order(17)
	void testValidDeleteUser() {
		assertDoesNotThrow(() -> userService.deleteUser(user.getEmail()));
	}

	@Test
	@Order(18)
	void testInvalidDeleteUser() {
		User nonExistentUser = new User();
		nonExistentUser.setEmail("nonExistentUser@gmail.com");
		ServiceException result = assertThrows(ServiceException.class,
		                                       () -> userService.deleteUser(nonExistentUser.getEmail()));
		assertEquals("User does not exist with the given email", result.getMessage());
	}
}
