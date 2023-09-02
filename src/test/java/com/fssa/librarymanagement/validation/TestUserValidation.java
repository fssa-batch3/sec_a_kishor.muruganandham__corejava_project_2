package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class TestUserValidation {
	private final UserValidator userValidator = new UserValidator();


	@Test
	void testValidMobileNumber() {
		long validMobileNo = 8925603157L;
		assertDoesNotThrow(() -> userValidator.validateMobileNo(validMobileNo));
	}

	@Test
	void testInvalidMobileNumber_Empty() {
		long invalidMobileNo = 0;
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateMobileNo(invalidMobileNo));
		assertEquals("Mobile number cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidMobileNumber_ShortNumber() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateMobileNo(892560315));
		assertEquals("Mobile number should be be 10 digits", result.getMessage());
	}


	@Test
	void testValidEmail() {
		String validEmail = "kishor@gmail.com";
		assertDoesNotThrow(() -> userValidator.validateEmail(validEmail));
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
				"kishor%gmail.com"));
		assertEquals("Invalid email format. Email should be in the format 'user@example.com'", result.getMessage());
	}

	@Test
	void testValidPassword() {
		String validPassword = "Kishor123";
		assertDoesNotThrow(() -> userValidator.validatePassword(validPassword));
	}

	@Test
	void testInvalidPassword_EmptyPassword() {
		String emptyPassword = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(emptyPassword));
		assertEquals("Password cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidPassword_ShortPassword() {
		String shortPassword = "123Kis";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(shortPassword));
		assertEquals("Password is less than the expected length of 8 characters", result.getMessage());
	}

	@Test
	void testInvalidPassword_InvalidFormat() {
		String shortPassword = "12345kishor";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(shortPassword));
		assertEquals("Password must contain at least one uppercase letter, one lowercase letter, and one digit",
		             result.getMessage());
	}

	@Test
	void testInvalidPassword_NullPassword() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validatePassword(null));
		assertEquals("Password cannot be empty", result.getMessage());
	}

	@Test
	void testValidGenderMale() {
		char validGender = 'M';
		assertDoesNotThrow(() -> userValidator.validateGender(validGender));
	}

	@Test
	void testValidGenderFemale() {
		char validGender = 'F';
		assertDoesNotThrow(() -> userValidator.validateGender(validGender));
	}

	@Test
	void testInvalidGender() {
		char invalidGender = 'z';
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateGender(invalidGender));
		assertEquals("Invalid gender. Gender must be 'M' or 'F'.", result.getMessage());
	}

	@Test
	void testValidUrl() {
		String validProfileImage = "https://example.com/";
		assertDoesNotThrow(() -> userValidator.validateProfileImage(validProfileImage));
	}

	@Test
	void testInvalidProfileImageURL_Empty() {
		String emptyProfileImage = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(emptyProfileImage));
		assertEquals("Profile image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidProfileImageURL_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(null));
		assertEquals("Profile image URL cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidProfileImageURL_InvalidFormat() {
		String invalidProfileImage = "kishor.com";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateProfileImage(invalidProfileImage));
		assertEquals("Invalid profile image URL. URL should be in the format 'http://www.example.com/index.html'",
		             result.getMessage());
	}

	@Test
	void testValidName() {
		String validName = "Kishor M";
		assertDoesNotThrow(() -> userValidator.validateName(validName));
	}

	@Test
	void testInvalidName_Empty() {
		String emptyName = "";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(emptyName));
		assertEquals("Name cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidName_Null() {
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(null));
		assertEquals("Name cannot be empty", result.getMessage());
	}

	@Test
	void testInvalidName_InvalidFromat() {
		String invalidName = "Kishor123";
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateName(invalidName));
		assertEquals("Invalid name format. Name should start with a capital letter and may contain letters, spaces, " +
				             "hyphens, and apostrophes.", result.getMessage());
	}

	@Test
	void testValidDateOfBirth() {
		LocalDate validDob = LocalDate.of(2003, 1, 1);
		assertDoesNotThrow(() -> userValidator.validateDateOfBirth(validDob));
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
	void testInvalidDateOfBirth_Exceeds100Years() {
		LocalDate minAge = LocalDate.of(1915, 1, 1);
		ValidationException result = assertThrows(ValidationException.class,
		                                          () -> userValidator.validateDateOfBirth(minAge));
		assertEquals("Invalid date of birth. Cannot exceed 100 years", result.getMessage());
	}
}

