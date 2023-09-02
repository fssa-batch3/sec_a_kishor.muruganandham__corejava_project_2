package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class TestUserValidation {
	private final UserValidator userValidator = new UserValidator();


	@Test
	void testValidMobileNo() {
		long validMobileNo = 8925603157L;
		boolean result = false;
		try {
			result = userValidator.validateMobileNo(validMobileNo);
		} catch (ValidationException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(result);
	}

	@Test
	void testInValidMobileNo() {
		long invalidMobileNo = 0;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateMobileNo(invalidMobileNo));
		assertEquals("Mobile number cannot be empty", result.getMessage());
	}

	@Test
	void testInValidMobileNo_ShortNumber() {

		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateMobileNo(892560315));
		assertEquals("Mobile number should be be 10 digits", result.getMessage());
	}


	@Test
	void testValidEmail() throws ValidationException {
		String validEmail = "kishor@gmail.com";
		boolean result = userValidator.validateEmail(validEmail);
		assertTrue(result);
	}

	@Test
	void testInvalidEmail_Empty() {
		ValidationException result = assertThrows(ValidationException.class, () -> userValidator.validateEmail(""));
		assertEquals("Email cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidEmail_Null() {
		ValidationException result = assertThrows(ValidationException.class, () -> userValidator.validateEmail(null));
		assertEquals("Email cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidEmail_InvalidFormat() {
		ValidationException result = assertThrows(ValidationException.class, () -> userValidator.validateEmail(
				"kishor" +
						"%gmail.com"));
		assertEquals("Invalid email format. Email should be in the format 'user@example.com'", result.getMessage());
	}

	@Test
	void testValidPassword() throws ValidationException {
		String validPassword = "Kishor123";
		boolean result = userValidator.validatePassword(validPassword);
		assertTrue(result);
	}

	@Test
	void testInValidPasswordEmptyPassword() {
		String emptyPassword = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(emptyPassword));
		assertEquals("Password cannot be empty", result.getMessage());
	}

	@Test
	void testInValidPasswordShortPassword() {
		String shortPassword = "123Kis";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(shortPassword));
		assertEquals("Password is less than the expected length of 8 characters", result.getMessage());
	}

	@Test
	void testInValidPasswordFormat() {
		String shortPassword = "12345kishor";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(shortPassword));
		assertEquals("Password must contain at least one uppercase letter, one lowercase letter, and one digit",
		             result.getMessage());
	}

	@Test
	void testInValidPasswordNullPassword() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(null));
		assertEquals("Password cannot be empty", result.getMessage());
	}

	@Test
	void testValidGenderMale() throws ValidationException {
		char validGender = 'M';
		boolean result = userValidator.validateGender(validGender);
		assertTrue(result);
	}

	@Test
	void testValidGenderFemale() throws ValidationException {
		char validGender = 'F';
		boolean result = userValidator.validateGender(validGender);
		assertTrue(result);
	}

	@Test
	void testInvalidGender() {
		char invalidGender = 'z';
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateGender(invalidGender));
		assertEquals("Invalid gender. Gender must be 'M' or 'F'.", result.getMessage());
	}

	@Test
	void testValidUrl() throws ValidationException {
		String validProfileImage = "https://example.com/";
		boolean result = userValidator.validateProfileImage(validProfileImage);
		assertTrue(result);
	}

	@Test
	void testInValidUrlEmptyUrl() {
		String emptyProfileImage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(emptyProfileImage));
		assertEquals("Profile image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInValidUrlNUllUrl() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(null));
		assertEquals("Profile image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidUrl() {
		String invalidProfileImage = "kishor.com";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(invalidProfileImage));
		assertEquals("Invalid profile image URL. URL should be in the format 'http://www.example.com/index.html'",
		             result.getMessage());
	}

	@Test
	void testValidName() throws ValidationException {
		String validName = "Kishor M";
		boolean result = userValidator.validateName(validName);
		assertTrue(result);
	}

	@Test
	void testInValidNameEmptyName() {
		String emptyName = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(emptyName));
		assertEquals("Name cannot be empty", result.getMessage());
	}

	@Test
	void testInValidNameNullName() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(null));
		assertEquals("Name cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidName() {
		String invalidName = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(invalidName));
		assertEquals("Invalid name format. Name should start with a capital letter and may contain letters, spaces, " +
				             "hyphens, and apostrophes.", result.getMessage());
	}

	@Test
	void testValidDateOfBirth_ValidDob() throws ValidationException {
		LocalDate validDob = LocalDate.of(2003, 1, 1);
		boolean result = userValidator.validateDateOfBirth(validDob);
		assertTrue(result);
	}

	@Test
	void testInValidDateOfBirth_EmptyDob() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateDateOfBirth(null));
		assertEquals("Date of birth cannot be empty", result.getMessage());
	}

	@Test
	void testInValidDateOfBirth_FutureDob() {
		LocalDate futureDob = LocalDate.now().plusYears(5);
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateDateOfBirth(futureDob));
		assertEquals("Invalid date of birth. Must be at least 10 years old.", result.getMessage());
	}

	@Test
	void testInValidDateOfBirth_Young() {
		LocalDate minAge = LocalDate.of(1915, 1, 1);
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateDateOfBirth(minAge));
		assertEquals("Invalid date of birth. Cannot exceed 100 years", result.getMessage());
	}
}

