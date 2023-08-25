package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.constants.BorrowConstants;
import com.fssa.librarymanagement.constants.ErrorMessageConstants;
import com.fssa.librarymanagement.constants.SuccessMessageConstants;
import com.fssa.librarymanagement.dao.BookDAO;
import com.fssa.librarymanagement.dao.BorrowDAO;
import com.fssa.librarymanagement.exceptions.DAOException;
import com.fssa.librarymanagement.exceptions.ServiceException;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.Borrow;
import com.fssa.librarymanagement.validation.BorrowValidator;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * This class provides services related to borrowing and returning books.
 *
 * @author KishorMuruganandham
 */
public class BorrowService {

	private final BookDAO bookDAO = new BookDAO();
	private final BorrowDAO borrowDAO = new BorrowDAO();

	/**
	 * Borrow a book for a user.
	 *
	 * @param borrow The borrow object containing user and book information.
	 * @return A success message if the book is successfully borrowed, or an error message if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	protected String borrowBook(Borrow borrow) throws ServiceException {
		BorrowValidator borrowValidator = new BorrowValidator(borrow);

		Borrow existingBorrow;
		int availableCopies;
		int borrowedBooksCount;
		boolean success;

		try {
			// Check if the user has already borrowed the book
			existingBorrow = borrowDAO.getBorrowByUserAndBook(borrow.getUser().getUserId(),
			                                                  borrow.getBook().getBookId());
			availableCopies = borrowDAO.getAvailableCopiesCount(borrow.getBook().getBookId());
			borrowedBooksCount = borrowDAO.getBorrowedBooksCountByUser(borrow.getUser().getUserId());

			if (existingBorrow != null) {
				throw new ServiceException(ErrorMessageConstants.BOOK_ALREADY_BORROWED);
			}
			borrowValidator.validateBorrowDate(borrow.getBorrowDate());

			// Check if there are available copies of the book
			if (availableCopies <= 0) {
				throw new ServiceException(ErrorMessageConstants.NO_AVAILABLE_COPIES);
			}
			// Check if the user has reached the borrow limit
			if (borrowedBooksCount >= BorrowConstants.BORROW_LIMIT) {
				throw new ServiceException(ErrorMessageConstants.BORROW_LIMIT_EXCEEDED);
			}

			success = borrowDAO.borrowBook(borrow);

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(ErrorMessageConstants.INVALID_BORROW_DATE);
		}
		if (success) {
			try {
				// Update book copies after successfully borrowing
				bookDAO.updateBookCopies(borrow.getBook().getBookId(), 1, -1);
			} catch (DAOException e) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_UPDATE_COPIES);
			}
			return SuccessMessageConstants.BOOK_BORROWED_SUCCESSFULLY;
		} else {
			return ErrorMessageConstants.FAILED_TO_BORROW_BOOK;
		}
	}

	/**
	 * Return the book borrowed by User.
	 *
	 * @param borrow The borrow object containing user and book information.
	 * @return A success message if the book is successfully returned, or an error message if not.
	 * @throws ServiceException If there is a problem with the service.
	 */
	protected String returnBook(Borrow borrow) throws ServiceException {
		BorrowValidator borrowValidator = new BorrowValidator(borrow);
		try {
			// Validate the return date
			borrowValidator.validateReturnDate(borrow.getReturnDate());
		} catch (ValidationException e) {
			throw new ServiceException(ErrorMessageConstants.RETURN_DATE_INVALID);
		}
		double fine = 0;
		if (borrow.getReturnDate().isAfter(borrow.getDueDate())) {
			// Calculate fine for late returns
			long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
			fine = daysLate * BorrowConstants.FINE_AMOUNT;
		}
		borrow.setFine(fine);
		boolean success;
		try {
			// Mark the book as returned
			success = borrowDAO.returnBook(borrow);
			if (!success) {
				throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETURN_BOOK);
			}
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_RETURN_BOOK);
		}
		return SuccessMessageConstants.ADDED_RETURN_DATE_SUCCESSFULLY;
	}

	/**
	 * Get a list of borrowed books by a user.
	 *
	 * @param userId The ID of the user.
	 * @return A list of borrow objects for the given user.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public List<Borrow> getBorrowsByUser(int userId) throws ServiceException {
		try {
			// Retrieve borrowed books for a specific user
			return borrowDAO.getBorrowsByUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_DETAILS);
		}
	}

	/**
	 * Get a list of borrowed books for a specific book.
	 *
	 * @param bookId The ID of the book.
	 * @return A list of borrow objects for the given book.
	 * @throws ServiceException If there's a problem with the service.
	 */
	protected List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
		try {
			// Retrieve borrowed books for a specific book
			return borrowDAO.getBorrowsByBook(bookId);
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_DETAILS);
		}
	}

	/**
	 * Get a list of all borrowed books.
	 *
	 * @return A list of all borrow objects.
	 * @throws ServiceException If there's a problem with the service.
	 */
	protected List<Borrow> getAllBorrows() throws ServiceException {
		try {
			// Retrieve all borrowed books
			return borrowDAO.getAllBorrows();
		} catch (DAOException e) {
			throw new ServiceException(ErrorMessageConstants.FAILED_TO_GET_BORROW_LIST);
		}
	}
}
