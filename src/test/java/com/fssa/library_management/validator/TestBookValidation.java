package com.fssa.library_management.validator;

import com.fssa.library_management.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestBookValidation {
    private final BookValidator bookValidator = new BookValidator();

    @Test
    void testValidTitle() throws ValidationException {
        String validTitle = "Sample Title";
        boolean result = bookValidator.validateTitle(validTitle);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidTitle() {
        String invalidTitle = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateTitle(invalidTitle));
    }

    @Test
    void testValidAuthor() throws ValidationException {
        String validAuthor = "Sample Author";
        boolean result = bookValidator.validateAuthor(validAuthor);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidAuthor() {
        String invalidAuthor = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateAuthor(invalidAuthor));
    }

    @Test
    void testValidPublisher() throws ValidationException {
        String validPublisher = "Sample Publisher";
        boolean result = bookValidator.validatePublisher(validPublisher);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidPublisher() {
        String invalidPublisher = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validatePublisher(invalidPublisher));
    }

    @Test
    void testValidGenre() throws ValidationException {
        String validGenre = "Fiction";
        boolean result = bookValidator.validateGenre(validGenre);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidGenre() {
        String invalidGenre = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateGenre(invalidGenre));
    }

    @Test
    void testValidLanguage() throws ValidationException {
        String validLanguage = "English";
        boolean result = bookValidator.validateLanguage(validLanguage);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidLanguage() {
        String invalidLanguage = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateLanguage(invalidLanguage));
    }

    @Test
    void testValidDescription() throws ValidationException {
        String validDescription = "Sample book description.";
        boolean result = bookValidator.validateDescription(validDescription);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidDescription() {
        String invalidDescription = "";
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateDescription(invalidDescription));
    }

    @Test
    void testValidTotalCopies() throws ValidationException {
        int validTotalCopies = 5;
        boolean result = bookValidator.validateTotalCopies(validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidTotalCopies() {
        int invalidTotalCopies = -1;
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateTotalCopies(invalidTotalCopies));
    }

    @Test
    void testValidAvailableCopies() throws ValidationException {
        int validAvailableCopies = 3;
        int validTotalCopies = 5;
        boolean result = bookValidator.validateAvailableCopies(validAvailableCopies, validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidAvailableCopies() {
        int invalidAvailableCopies = 6;
        int validTotalCopies = 5;
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateAvailableCopies(invalidAvailableCopies, validTotalCopies));
    }

    @Test
    void testValidLoanedCopies() throws ValidationException {
        int validLoanedCopies = 2;
        int validTotalCopies = 5;
        boolean result = bookValidator.validateLoanedCopies(validLoanedCopies, validTotalCopies);
        Assertions.assertTrue(result);
    }

    @Test
    void testInvalidLoanedCopies() {
        int invalidLoanedCopies = -1;
        int validTotalCopies = 5;
        Assertions.assertThrows(ValidationException.class, () -> bookValidator.validateLoanedCopies(invalidLoanedCopies, validTotalCopies));
    }
}
