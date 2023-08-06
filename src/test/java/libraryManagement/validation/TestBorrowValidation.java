package libraryManagement.validation;

import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Borrow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

class TestBorrowValidation {

    private ValidateBorrow validateBorrow;
    private Borrow borrow;

    @BeforeEach
    void setUp() {
        borrow = new Borrow();
        validateBorrow = new ValidateBorrow(borrow);
    }

    @Test
    void validateValidBorrowDate() throws ValidationException {
        LocalDate validBorrowDate = LocalDate.now();
        boolean result = validateBorrow.validateBorrowDate(validBorrowDate);
        Assertions.assertTrue(result);
    }

    @Test
    void validateInvalidBorrowDate() {
        Assertions.assertThrows(ValidationException.class, () -> validateBorrow.validateBorrowDate(null));
    }

    @Test
    void validateValidReturnDate() throws ValidationException {
        LocalDate borrowDate = LocalDate.now().minus(Period.ofDays(7));
        LocalDate validReturnDate = LocalDate.now();
        borrow.setBorrowDate(borrowDate);

        boolean result = validateBorrow.validateReturnDate(validReturnDate);
        Assertions.assertTrue(result);
    }

    @Test
    void validateInvalidReturnDate() {
        LocalDate borrowDate = LocalDate.now();
        LocalDate invalidReturnDate = borrowDate.minus(Period.ofDays(1));
        borrow.setBorrowDate(borrowDate);

        Assertions.assertThrows(ValidationException.class, () -> validateBorrow.validateReturnDate(invalidReturnDate));
    }

    @Test
    void validateValidReturnDateForAlreadyReturnedBorrow() throws ValidationException {
        LocalDate borrowDate = LocalDate.now().minus(Period.ofDays(10));
        LocalDate returnDate = LocalDate.now().minus(Period.ofDays(5));
        borrow.setBorrowDate(borrowDate);

        boolean result = validateBorrow.validateReturnDate(returnDate);
        Assertions.assertTrue(result);
    }
}
