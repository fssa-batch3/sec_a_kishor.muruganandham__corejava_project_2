package com.fssa.librarymanagement.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fssa.librarymanagement.exceptions.ValidationException;


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
	void testValidEmail() throws ValidationException {
		String validEmail = "kishor@gmail.com";
		boolean result = userValidator.validateEmail(validEmail);
		assertTrue(result);
	}

	@Test
	void testInvalidEmail() {
		ValidationException result = assertThrows(ValidationException.class, () -> userValidator.validateEmail(""));
		assertEquals("Email cannot be empty", result.getMessage());
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
		String shortPassword = "invalid";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(shortPassword));
		assertEquals("Password is less than the expected length of 8 characters", result.getMessage());
	}

	@Test
	void testValidGender() throws ValidationException {
		char validGender = 'M';
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
	void testInValidUrl_EmptyUrl() {
		String emptyProfileImage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(emptyProfileImage));
		assertEquals("Profile Image cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidUrl() {
		String invalidProfileImage = "kishor.com";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(invalidProfileImage));
		assertEquals("Invalid profile image URL", result.getMessage());
	}

	@Test
	void testValidName() throws ValidationException {
		String validName = "Kishor M";
		boolean result = userValidator.validateName(validName);
		assertTrue(result);
	}

	@Test
	void testInValidName_EmptyName() {
		String emptyName = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(emptyName));
		assertEquals("Name cannot be Empty", result.getMessage());
	}

	@Test
	void testInvalidName() {
		String invalidName = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(invalidName));
		assertEquals("Invalid Name Format", result.getMessage());
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
		assertEquals("Date of birth cannot be in future", result.getMessage());
	}

	@Test
	void testInValidDateOfBirth_Young() {
		LocalDate minAge = LocalDate.of(2015, 1, 1);
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateDateOfBirth(minAge));
		assertEquals("Invalid date of birth. Must be at least 10 years old.", result.getMessage());
	}
}

