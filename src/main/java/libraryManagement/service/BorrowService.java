package libraryManagement.service;

import libraryManagement.DAO.BorrowDao;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Borrow;
import libraryManagement.validation.ValidateBorrow;

import java.util.List;

public class BorrowService {
    public String borrowBook(Borrow borrow) throws ServiceException, ValidationException {
        ValidateBorrow validateBorrow = new ValidateBorrow(borrow);
        List<Borrow> borrowedBooks = BorrowDao.getBorrowsByBook(borrow.getBookId());
        validateBorrow.validateBorrowDate(borrow.getBorrowDate());
        if (borrowedBooks != null) {
            for (Borrow borrowedBook : borrowedBooks) {
                if (borrowedBook.isReturned() && borrowedBook.getBookId() == borrow.getBookId()) {
                    throw new ServiceException("The book is already borrowed by you.");
                }
            }
        }
        boolean success = BorrowDao.borrowBook(borrow);
        if (success) {
            return "Book borrowed successfully.";
        } else {
            return "Failed to borrow book '" + borrow.getBookId() + "'.";
        }
    }

    public String returnBook(Borrow borrow) throws ValidationException, ServiceException {
        ValidateBorrow validateBorrow = new ValidateBorrow(borrow);
        boolean isValidReturnDate = validateBorrow.validateReturnDate(borrow.getReturnDate());
        if (borrow.isReturned()) {
            throw new ServiceException("Book has been already return");
        }
        if (isValidReturnDate) {
            boolean success = BorrowDao.returnBook(borrow);
            if (success) {
                return "Added return date successfully.";
            } else {
                return "Failed to return book.";
            }
        } else {
            throw new ServiceException("Invalid return date.");
        }
    }

    public List<Borrow> getBorrowsByUser(int userId) throws ServiceException {
        return BorrowDao.getBorrowsByUser(userId);
    }

    public List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
        return BorrowDao.getBorrowsByBook(bookId);
    }

    public List<Borrow> getAllBorrows() throws ServiceException {
        return BorrowDao.getAllBorrows();
    }
}
