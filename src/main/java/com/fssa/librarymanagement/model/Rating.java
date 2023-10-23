/**
 * 
 */
package com.fssa.librarymanagement.model;

/**
 * 
 */
public class Rating {

	
	private int userId;
	private int bookId;
	private int stars;

	

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return stars;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.stars = rating;
	}


}
