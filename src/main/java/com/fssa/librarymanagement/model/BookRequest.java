package com.fssa.librarymanagement.model;

/**
 * Represents a book request in the library management system.
 *
 * @author KishorMuruganandham
 */
public class BookRequest {
	String bookName;
	String authorName;
	String sourceLink;
	String description;

	/**
	 * Get the name of the requested book.
	 *
	 * @return The name of the requested book.
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * Set the name of the requested book.
	 *
	 * @param bookName The name of the requested book.
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * Get the name of the book's author.
	 *
	 * @return The name of the book's author.
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Set the name of the book's author.
	 *
	 * @param authorName The name of the book's author.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * Get the source link of the requested book.
	 *
	 * @return The source link of the requested book.
	 */
	public String getSourceLink() {
		return sourceLink;
	}

	/**
	 * Set the source link of the requested book.
	 *
	 * @param sourceLink The source link of the requested book.
	 */
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	/**
	 * Get the description of the book request.
	 *
	 * @return The description of the book request.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the book request.
	 *
	 * @param description The description of the book request.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
