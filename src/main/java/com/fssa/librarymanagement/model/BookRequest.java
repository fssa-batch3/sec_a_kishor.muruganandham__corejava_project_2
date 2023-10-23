/**
 * 
 */
package com.fssa.librarymanagement.model;

/**
 * 
 */
public class BookRequest {
	String bookName;
	String authorName;
	String sourceLink;
	String description;

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the sourceLink
	 */
	public String getSourceLink() {
		return sourceLink;
	}

	/**
	 * @param sourceLink the sourceLink to set
	 */
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
