package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TestUserValidation {
    private final ValidateUser validateUser = new ValidateUser();

    @Test
    void testValidMobileNo() throws ValidationException {
        long validMobileNo = 8925603157L;
        boolean result = validateUser.validateMobileNo(validMobileNo);
        Assertions.assertTrue(result);
    }

    @Test
    void testInValidMobileNo() {
        long invalidMobileNo = 0;
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateMobileNo(invalidMobileNo));
    }

    @Test
    void testValidEmail() throws ValidationException {
        String validEmail = "kishor@gmail.com";
        boolean result = ValidateUser.validateEmail(validEmail);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidEmail() {
        Assertions.assertThrows(ValidationException.class, () -> ValidateUser.validateEmail(null));
    }

    @Test
    void testValidPassword() throws ValidationException {
        String validPassword = "Kishor123";
        boolean result = validateUser.validatePassword(validPassword);
        Assertions.assertTrue(result);
    }

    @Test
    void testInValidPasswordEmptyPassword() {
        String emptyPassword = "";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validatePassword(emptyPassword));
    }

    @Test
    void testInValidPasswordShortPassword() {
        String shortPassword = "invalid";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validatePassword(shortPassword));
    }

    @Test
    void testValidGender() throws ValidationException {
        char validGender = 'M';
        boolean result = validateUser.validateGender(validGender);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidGender() {
        char invalidGender = 'z';
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateGender(invalidGender));
    }

    @Test
    void testValidUrl() throws ValidationException {
        String validProfileImage = "https://example.com/";
        boolean result = validateUser.validateProfileImage(validProfileImage);
        Assertions.assertTrue(result);
    }

    @Test
    void testInValidUrl_EmptyUrl() {
        String emptyProfileImage = "";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateProfileImage(emptyProfileImage));
    }

    @Test
    void testInvalidUrl() {
        String invalidProfileImage = "kishor.com";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateProfileImage(invalidProfileImage));
    }

    @Test
    void testValidName() throws ValidationException {
        String validName = "Kishor M";
        boolean result = validateUser.validateName(validName);
        Assertions.assertTrue(result);
    }

    @Test
    void testInValidName_EmptyName() {
        String emptyName = "";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateName(emptyName));
    }

    @Test
    void testInvalidName() {
        String invalidName = "Kishor123";
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateName(invalidName));
    }

    @Test
    void testValidDateOfBirth_ValidDob() throws ValidationException {
        LocalDate validDob = LocalDate.of(2003, 1, 1);
        boolean result = validateUser.validateDateOfBirth(validDob);
        Assertions.assertTrue(result);
    }

    @Test
    void testInValidDateOfBirth_EmptyDob() {
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateDateOfBirth(null));
    }

    @Test
    void testInValidDateOfBirth_FutureDob() {
        LocalDate futureDob = LocalDate.now().plusYears(1);
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateDateOfBirth(futureDob));
    }

    @Test
    void testInValidDateOfBirth_Young() {
        LocalDate minAge = LocalDate.of(2015, 1, 1);
        Assertions.assertThrows(ValidationException.class, () -> validateUser.validateDateOfBirth(minAge));
    }
}

