package libraryManagement.service;

import libraryManagement.DAO.BookDao;
import libraryManagement.DAO.BorrowDao;
import libraryManagement.exceptions.DAOException;
import libraryManagement.exceptions.ServiceException;
import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Borrow;
import libraryManagement.validation.ValidateBorrow;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class BorrowService {
    public static final double FINE_AMOUNT = 10.0;
    public static final int BORROW_LIMIT = 5;

    public String borrowBook(Borrow borrow) throws ServiceException {
        ValidateBorrow validateBorrow = new ValidateBorrow(borrow);
        Borrow existingBorrow = BorrowDao.getBorrowByUserAndBook(borrow.getUserId(), borrow.getBookId());
        if (existingBorrow != null) {
            throw new ServiceException("This Book has been already borrowed by you");
        }
        try {
            validateBorrow.validateBorrowDate(borrow.getBorrowDate());
        } catch (ValidationException e) {
            throw new ServiceException("Borrow Details are not Valid");
        }

        int availableCopies = BorrowDao.getAvailableCopiesCount(borrow.getBookId());
        if (availableCopies <= 0) {
            throw new ServiceException("No available copies of the book.");
        }

        int borrowedBooksCount = BorrowDao.getBorrowedBooksCountByUser(borrow.getUserId());
        if (borrowedBooksCount >= BORROW_LIMIT) {
            throw new ServiceException("Borrow limit exceeded for the user.");
        }

        boolean success = BorrowDao.borrowBook(borrow);
        if (success) {
            try {
                BookDao.updateBookCopies(borrow.getBookId(), 1, -1);
            } catch (DAOException e) {
                throw new ServiceException("Failed to Update Number of copies in book");
            }
            return "Book borrowed successfully.";
        } else {
            return "Failed to borrow book.";
        }
    }


    public String returnBook(Borrow borrow) throws ServiceException {
        ValidateBorrow validateBorrow = new ValidateBorrow(borrow);
        try {
            validateBorrow.validateReturnDate(borrow.getReturnDate());
        } catch (ValidationException e) {
            throw new ServiceException("Return date is not Valid");
        }
        double fine = 0;
        if (borrow.getReturnDate().isAfter(borrow.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
            fine = daysLate * FINE_AMOUNT;
        }
        borrow.setFine(fine);
        boolean success = BorrowDao.returnBook(borrow);
        return success ? "Added return date successfully." : "Failed to return book.";
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
