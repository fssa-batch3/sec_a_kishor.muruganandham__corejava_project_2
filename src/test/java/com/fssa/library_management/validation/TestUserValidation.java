package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
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
        assertThrows(ValidationException.class, () -> userValidator.validateMobileNo(invalidMobileNo));
    }

    @Test
    void testValidEmail() throws ValidationException {
        String validEmail = "kishor@gmail.com";
        boolean result = UserValidator.validateEmail(validEmail);
        assertTrue(result);
    }

    @Test
    void testInvalidEmail() {
        assertThrows(ValidationException.class, () -> UserValidator.validateEmail(null));
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
        assertThrows(ValidationException.class, () -> userValidator.validatePassword(emptyPassword));
    }

    @Test
    void testInValidPasswordShortPassword() {
        String shortPassword = "invalid";
        assertThrows(ValidationException.class, () -> userValidator.validatePassword(shortPassword));
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
        assertThrows(ValidationException.class, () -> userValidator.validateGender(invalidGender));
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
        assertThrows(ValidationException.class, () -> userValidator.validateProfileImage(emptyProfileImage));
    }

    @Test
    void testInvalidUrl() {
        String invalidProfileImage = "kishor.com";
        assertThrows(ValidationException.class, () -> userValidator.validateProfileImage(invalidProfileImage));
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
        assertThrows(ValidationException.class, () -> userValidator.validateName(emptyName));
    }

    @Test
    void testInvalidName() {
        String invalidName = "Kishor123";
        assertThrows(ValidationException.class, () -> userValidator.validateName(invalidName));
    }

    @Test
    void testValidDateOfBirth_ValidDob() throws ValidationException {
        LocalDate validDob = LocalDate.of(2003, 1, 1);
        boolean result = userValidator.validateDateOfBirth(validDob);
        assertTrue(result);
    }

    @Test
    void testInValidDateOfBirth_EmptyDob() {
        assertThrows(ValidationException.class, () -> userValidator.validateDateOfBirth(null));
    }

    @Test
    void testInValidDateOfBirth_FutureDob() {
        LocalDate futureDob = LocalDate.now().plusYears(1);
        assertThrows(ValidationException.class, () -> userValidator.validateDateOfBirth(futureDob));
    }

    @Test
    void testInValidDateOfBirth_Young() {
        LocalDate minAge = LocalDate.of(2015, 1, 1);
        assertThrows(ValidationException.class, () -> userValidator.validateDateOfBirth(minAge));
    }
}

