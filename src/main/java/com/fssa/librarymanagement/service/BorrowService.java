package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.constants.BorrowConstants;
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
	 * Constructs a new BorrowService object for handling borrow-related business logic and interactions.
	 */
	public BorrowService() {
		// Default constructor
	}

	/**
	 * Borrow a book for a user.
	 *
	 * @param borrow The borrow object containing user and book information.
	 * @return A success message if the book is successfully borrowed, or an error message if not.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public boolean borrowBook(Borrow borrow) throws ServiceException {
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
				throw new ServiceException(BorrowConstants.THIS_BOOK_HAS_ALREADY_BEEN_BORROWED_BY_YOU);
			}
			borrowValidator.validateBorrowDate(borrow.getBorrowDate());

			// Check if there are available copies of the book
			if (availableCopies <= 0) {
				throw new ServiceException(BorrowConstants.NO_AVAILABLE_COPIES_OF_THE_BOOK);
			}
			// Check if the user has reached the borrow limit
			if (borrowedBooksCount >= BorrowConstants.BORROW_LIMIT) {
				throw new ServiceException(BorrowConstants.BORROW_LIMIT_EXCEEDED_FOR_THE_USER);
			}

			success = borrowDAO.borrowBook(borrow);
			if (success) {
				// Update book copies after successful borrowing
				return bookDAO.updateBookCopies(borrow.getBook().getBookId(), 1, -1);
			} else {
				return false;
			}

		} catch (DAOException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Return the book borrowed by User.
	 *
	 * @param borrow The borrow object containing user and book information.
	 * @return A success message if the book is successfully returned, or an error message if not.
	 * @throws ServiceException If there is a problem with the service.
	 */
	public boolean returnBook(Borrow borrow) throws ServiceException {
		BorrowValidator borrowValidator = new BorrowValidator(borrow);
		try {
			borrowValidator.validateAll();  // Validate the return date

			double fine = calculateFine(borrow);    // Calculate fine for late returns
			borrow.setFine(fine);

			return borrowDAO.returnBook(borrow);
		} catch (ValidationException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Calculates the fine for a late book return.
	 *
	 * @param borrow The borrow object containing user and book information.
	 * @return The calculated fine amount, which is 0 if the book is returned on time.
	 */
	private int calculateFine(Borrow borrow) {
		int fine = 0;
		if (borrow.getReturnDate().isAfter(borrow.getDueDate())) {
			// Calculate fine for late returns
			long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
			fine = (int) (daysLate * BorrowConstants.FINE_AMOUNT);
		}
		return fine;
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
			List<Borrow> borrows = borrowDAO.getBorrowsByUser(userId);
			if (borrows == null || borrows.isEmpty()) {
				throw new ServiceException(BorrowConstants.BORROWS_NOT_FOUND);
			}
			return borrows;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Get a list of borrowed books for a specific book.
	 *
	 * @param bookId The ID of the book.
	 * @return A list of borrow objects for the given book.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
		try {
			// Retrieve borrowed books for a specific book
			List<Borrow> borrows = borrowDAO.getBorrowsByBook(bookId);
			if (borrows == null || borrows.isEmpty()) {
				throw new ServiceException(BorrowConstants.BORROWS_NOT_FOUND);
			}
			return borrows;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a specific Borrow by its ID.
	 *
	 * @param borrowId The ID of the Borrow to retrieve.
	 * @return The Borrow object if found, or null if not found.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public Borrow getBorrowById(int borrowId) throws ServiceException {
		try {
			// Retrieve a specific Borrow by ID
			Borrow borrow = borrowDAO.getBorrowById(borrowId);
			if (borrow == null) {
				throw new ServiceException(BorrowConstants.BORROWS_NOT_FOUND);
			}
			return borrow;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Get a list of all borrowed books.
	 *
	 * @return A list of all borrow objects.
	 * @throws ServiceException If there's a problem with the service.
	 */
	public List<Borrow> getAllBorrows() throws ServiceException {
		try {
			// Retrieve all borrowed books
			List<Borrow> borrows = borrowDAO.getAllBorrows();
			if (borrows == null || borrows.isEmpty()) {
				throw new ServiceException(BorrowConstants.BORROWS_NOT_FOUND);
			}
			return borrows;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
