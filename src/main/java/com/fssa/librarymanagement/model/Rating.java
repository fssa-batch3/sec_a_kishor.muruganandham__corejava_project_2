package com.fssa.librarymanagement.model;


/**
 * Represents a user's rating for a book in a library system.
 *
 * @author KishorMuruganandham
 */
public class Rating {

	private int userId; // The ID of the user who rated the book.
	private int bookId; // The ID of the rated book.
	private int stars;

	/**
	 * Get the ID of the user who rated the book.
	 *
	 * @return The ID of the user who rated the book.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set the ID of the user who rated the book.
	 *
	 * @param userId The ID of the user who rated the book.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Get the ID of the rated book.
	 *
	 * @return The ID of the rated book.
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * Set the ID of the rated book.
	 *
	 * @param bookId The ID of the rated book.
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Get the user's rating in stars.
	 *
	 * @return The user's rating in stars (e.g., 1 to 5).
	 */
	public int getRating() {
		return stars;
	}

	/**
	 * Set the user's rating in stars.
	 *
	 * @param rating The user's rating in stars (e.g., 1 to 5).
	 */
	public void setRating(int rating) {
		this.stars = rating;
	}
}
