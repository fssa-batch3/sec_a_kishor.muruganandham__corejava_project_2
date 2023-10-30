package com.fssa.librarymanagement.model;

import com.fssa.librarymanagement.enums.BorrowingDuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a borrowing transaction in a library system.
 *
 * @author KishorMuruganandham
 */

public class Borrow {
	private User user; // The user who borrowed the book
	private Book book; // The borrowed book
	private LocalDateTime borrowDate;
	private LocalDate returnDate;
	private LocalDate dueDate;
	private double fine;
	private int borrowId;
	private boolean isReturned;

	/**
	 * Constructs a new Borrow object with default values.
	 */
	public Borrow() {
		// Default constructor
	}

	/**
	 * Get the borrow's ID.
	 *
	 * @return The ID of the borrow.
	 */
	public int getBorrowId() {
		return borrowId;
	}

	/**
	 * Set the borrow's ID.
	 *
	 * @param borrowId The ID of the borrow.
	 */
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * Check if the borrow has been returned.
	 *
	 * @return true if the borrow has been returned, false otherwise.
	 */
	public boolean isReturned() {
		return isReturned;
	}

	/**
	 * Set the borrow's return status.
	 *
	 * @param isReturned true if the borrow's return status should be active, false otherwise.
	 */
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	/**
	 * Get the user who borrowed the book.
	 *
	 * @return The user who borrowed the book.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set the user who borrowed the book.
	 *
	 * @param user The user who borrowed the book.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the borrowed book.
	 *
	 * @return The borrowed book.
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Set the borrowed book.
	 *
	 * @param book The borrowed book.
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Get the date when the book was borrowed.
	 *
	 * @return The date when the book was borrowed.
	 */
	public LocalDateTime getBorrowDate() {
		return borrowDate;
	}

	/**
	 * Set the date when the book was borrowed and calculate the due date.
	 *
	 * @param borrowDate The date when the book was borrowed.
	 */
	public void setBorrowDate(LocalDateTime borrowDate) {
		this.borrowDate = borrowDate;
	}

	/**
	 * Get the date when the book was returned.
	 *
	 * @return The date when the book was returned.
	 */
	public LocalDate getReturnDate() {
		return returnDate;
	}

	/**
	 * Set the date when the book was returned.
	 *
	 * @param returnDate The date when the book was returned.
	 */
	public void setReturnDate(LocalDate returnDate) {
		if (returnDate != null) {
			this.returnDate = returnDate;
		}
	}

	/**
	 * Get any fine associated with the return of the book.
	 *
	 * @return The fine associated with the return of the book.
	 */
	public double getFine() {
		return fine;
	}

	/**
	 * Set the fine associated with the return of the book.
	 *
	 * @param fine The fine associated with the return of the book.
	 */
	public void setFine(double fine) {
		this.fine = fine;
	}

	/**
	 * Get the due date for returning the book.
	 *
	 * @return The due date for returning the book.
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * Set the due date for returning the book based on the selected borrowing duration.
	 *
	 * @param borrowingDuration The borrowing duration selected from the BorrowingDuration enum.
	 **/
	public void setDueDate(BorrowingDuration borrowingDuration) {
		this.dueDate = borrowDate.plusDays(borrowingDuration.getValue()).toLocalDate();
	}

	/**
	 * Set the due date for returning the book.
	 *
	 * @param dueDate The due date for returning the book.
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
