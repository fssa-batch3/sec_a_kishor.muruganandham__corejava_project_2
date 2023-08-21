package com.fssa.library_management.service;

import com.fssa.library_management.constants.BorrowConstants;
import com.fssa.library_management.constants.ErrorMessageConstants;
import com.fssa.library_management.constants.SuccessMessageConstants;
import com.fssa.library_management.dao.BookDAO;
import com.fssa.library_management.dao.BorrowDAO;
import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.Borrow;
import com.fssa.library_management.validation.BorrowValidator;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class BorrowService {

	private final BookDAO bookDAO = new BookDAO();

	public String borrowBook(Borrow borrow) throws ServiceException {
		BorrowValidator borrowValidator = new BorrowValidator(borrow);

		Borrow existingBorrow;
		int availableCopies;
		int borrowedBooksCount;
		boolean success;

		try {
			existingBorrow = BorrowDAO.getBorrowByUserAndBook(borrow.getUser().getUserId(),
			                                                  borrow.getBook().getBookId());
			availableCopies = BorrowDAO.getAvailableCopiesCount(borrow.getBook().getBookId());
			borrowedBooksCount = BorrowDAO.getBorrowedBooksCountByUser(borrow.getUser().getUserId());

			if (existingBorrow != null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_ALREADY_BORROWED);
			}
			borrowValidator.validateBorrowDate(borrow.getBorrowDate());

			if (availableCopies <= 0) {
				throw new ServiceException(ErrorMessageConstants.NO_AVAILABLE_COPIES);
			}
			if (borrowedBooksCount >= BorrowConstants.BORROW_LIMIT) {
				throw new ServiceException(ErrorMessageConstants.BORROW_LIMIT_EXCEEDED);
			}

			success = BorrowDAO.borrowBook(borrow);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(ErrorMessageConstants.INVALID_BORROW_DATE);
		}
		if (success) {
			try {
				bookDAO.updateBookCopies(borrow.getBook().getBookId(), 1, -1);
			} catch (DAOException e) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_COPIES);
			}
			return SuccessMessageConstants.BOOK_BORROWED_SUCCESSFULLY;
		} else {
			return ErrorMessageConstants.FAILED_TO_BORROW_BOOK;
		}
	}


	public String returnBook(Borrow borrow) throws ServiceException {
		BorrowValidator borrowValidator = new BorrowValidator(borrow);
		try {
			borrowValidator.validateReturnDate(borrow.getReturnDate());
		} catch (ValidationException e) {
			throw new ServiceException(ErrorMessageConstants.RETURN_DATE_INVALID);
		}
		double fine = 0;
		if (borrow.getReturnDate().isAfter(borrow.getDueDate())) {
			long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
			fine = daysLate * BorrowConstants.FINE_AMOUNT;
		}
		borrow.setFine(fine);
		boolean success;
		try {
			success = BorrowDAO.returnBook(borrow);
			if (!success) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETURN_BOOK);
			}
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETURN_BOOK);
		}
		return SuccessMessageConstants.ADDED_RETURN_DATE_SUCCESSFULLY;
	}


	public List<Borrow> getBorrowsByUser(int userId) throws ServiceException {
		try {
			return BorrowDAO.getBorrowsByUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_DETAILS);
		}
	}

	public List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
		try {
			return BorrowDAO.getBorrowsByBook(bookId);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_DETAILS);
		}
	}

	public List<Borrow> getAllBorrows() throws ServiceException {
		try {
			return BorrowDAO.getAllBorrows();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_LIST);
		}
	}

}
