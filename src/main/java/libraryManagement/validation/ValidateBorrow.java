package libraryManagement.validation;

import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Borrow;

import java.time.LocalDate;

public class ValidateBorrow {

    private Borrow borrow;

    public ValidateBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public ValidateBorrow() {

    }
    public boolean validateBorrowDate(LocalDate borrowDate) throws ValidationException {
        if (borrowDate == null) {
            throw new ValidationException("Borrow date cannot be empty");
        }
        return true;
    }

    public boolean validateReturnDate(LocalDate returnDate) throws ValidationException {
        LocalDate borrowDate = borrow.getBorrowDate();
        if (returnDate == null) {
            throw new ValidationException("Return date cannot be empty");
        }
        if (!borrow.isReturned() && returnDate.isBefore(borrowDate)) {
            throw new ValidationException("Return date should be after the borrow date");
        }
        return true;
    }
}
