package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestBookValidation {
    private final ValidateBook validateBook = new ValidateBook();

    @Test
    void testValidTitle() throws ValidationException {
        String validTitle = "Sample Title";
        boolean result = validateBook.validateTitle(validTitle);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidTitle() {
        String invalidTitle = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateTitle(invalidTitle));
    }

    @Test
    void testValidAuthor() throws ValidationException {
        String validAuthor = "Sample Author";
        boolean result = validateBook.validateAuthor(validAuthor);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidAuthor() {
        String invalidAuthor = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateAuthor(invalidAuthor));
    }

    @Test
    void testValidPublisher() throws ValidationException {
        String validPublisher = "Sample Publisher";
        boolean result = validateBook.validatePublisher(validPublisher);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidPublisher() {
        String invalidPublisher = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validatePublisher(invalidPublisher));
    }

    @Test
    void testValidGenre() throws ValidationException {
        String validGenre = "Fiction";
        boolean result = validateBook.validateGenre(validGenre);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidGenre() {
        String invalidGenre = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateGenre(invalidGenre));
    }

    @Test
    void testValidLanguage() throws ValidationException {
        String validLanguage = "English";
        boolean result = validateBook.validateLanguage(validLanguage);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidLanguage() {
        String invalidLanguage = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateLanguage(invalidLanguage));
    }

    @Test
    void testValidDescription() throws ValidationException {
        String validDescription = "Sample book description.";
        boolean result = validateBook.validateDescription(validDescription);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidDescription() {
        String invalidDescription = "";
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateDescription(invalidDescription));
    }

    @Test
    void testValidTotalCopies() throws ValidationException {
        int validTotalCopies = 5;
        boolean result = validateBook.validateTotalCopies(validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidTotalCopies() {
        int invalidTotalCopies = -1;
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateTotalCopies(invalidTotalCopies));
    }

    @Test
    void testValidAvailableCopies() throws ValidationException {
        int validAvailableCopies = 3;
        int validTotalCopies = 5;
        boolean result = validateBook.validateAvailableCopies(validAvailableCopies, validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidAvailableCopies() {
        int invalidAvailableCopies = 6;
        int validTotalCopies = 5;
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateAvailableCopies(invalidAvailableCopies, validTotalCopies));
    }

    @Test
    void testValidLoanedCopies() throws ValidationException {
        int validLoanedCopies = 2;
        int validTotalCopies = 5;
        boolean result = validateBook.validateLoanedCopies(validLoanedCopies, validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidLoanedCopies() {
        int invalidLoanedCopies = -1;
        int validTotalCopies = 5;
        Assertions.assertThrows(ValidationException.class, () -> validateBook.validateLoanedCopies(invalidLoanedCopies, validTotalCopies));
    }
}
