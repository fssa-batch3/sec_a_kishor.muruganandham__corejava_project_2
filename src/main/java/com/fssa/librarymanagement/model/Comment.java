/**
 * 
 */
package com.fssa.librarymanagement.model;

import java.time.LocalDateTime;

/**
 * 
 */
public class Comment {

	private int commentId;
	private User user;
	private Book book;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime editedAt;
	private boolean isActive;
	private boolean isEdited;
	private boolean isTrusted;

	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
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

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the editedAt
	 */
	public LocalDateTime getEditedAt() {
		return editedAt;
	}

	/**
	 * @param editedAt the editedAt to set
	 */
	public void setEditedAt(LocalDateTime editedAt) {
		this.editedAt = editedAt;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @param isEdited the isEdited to set
	 */
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	/**
	 * @return the isTrusted
	 */
	public boolean isTrusted() {
		return isTrusted;
	}

	/**
	 * @param isTrusted the isTrusted to set
	 */
	public void setTrusted(boolean isTrusted) {
		this.isTrusted = isTrusted;
	}

}
