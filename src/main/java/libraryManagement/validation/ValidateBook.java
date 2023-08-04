package libraryManagement.validation;

import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Book;

public class ValidateBook {

    private final Book book;

    public ValidateBook(Book book) throws ValidationException {
        this.book = book;
        validateAll();
    }

    public boolean validateAll() throws ValidationException {
        return validateTitle() &&
                validateAuthor() &&
                validatePublisher() &&
                validateGenre() &&
                validateLanguage() &&
                validateDescription() &&
                validateTotalCopies() &&
                validateAvailableCopies() &&
                validateLoanedCopies();
    }

    public boolean validateTitle() throws ValidationException {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new ValidationException("Book title cannot be empty");
        }
        return true;
    }

    public boolean validateAuthor() throws ValidationException {
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new ValidationException("Book author cannot be empty");
        }
        return true;
    }

    public boolean validatePublisher() throws ValidationException {
        if (book.getPublisher() == null || book.getPublisher().isEmpty()) {
            throw new ValidationException("Book publisher cannot be empty");
        }
        return true;
    }

    public boolean validateGenre() throws ValidationException {
        if (book.getGenre() == null || book.getGenre().isEmpty()) {
            throw new ValidationException("Book genre cannot be empty");
        }
        return true;
    }

    public boolean validateLanguage() throws ValidationException {
        if (book.getLanguage() == null || book.getLanguage().isEmpty()) {
            throw new ValidationException("Book language cannot be empty");
        }
        return true;
    }

    public boolean validateDescription() throws ValidationException {
        if (book.getDescription() == null || book.getDescription().isEmpty()) {
            throw new ValidationException("Book description cannot be empty");
        }
        return true;
    }

    public boolean validateTotalCopies() throws ValidationException {
        if (book.getTotalCopies() <= 0) {
            throw new ValidationException("Total copies should be greater than zero");
        }
        return true;
    }

    public boolean validateAvailableCopies() throws ValidationException {
        if (book.getAvailableCopies() < 0 || book.getAvailableCopies() > book.getTotalCopies()) {
            throw new ValidationException("Invalid number of available copies");
        }
        return true;
    }

    public boolean validateLoanedCopies() throws ValidationException {
        if (book.getLoanedCopies() < 0 || book.getLoanedCopies() > book.getTotalCopies()) {
            throw new ValidationException("Invalid number of loaned copies");
        }
        return true;
    }
}
