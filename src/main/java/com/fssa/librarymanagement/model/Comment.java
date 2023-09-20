/**
 * 
 */
package com.fssa.librarymanagement.model;

import java.time.LocalDate;

/**
 * 
 */
public class Comment {
	
	
	private int commentId;
    private User user;
    private Book book;
    private String description;
    private LocalDate createdAt;
    private LocalDate editedAt;
    private boolean isActive;
    private boolean isEdited;
    
    
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
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the editedAt
	 */
	public LocalDate getEditedAt() {
		return editedAt;
	}
	/**
	 * @param editedAt the editedAt to set
	 */
	public void setEditedAt(LocalDate editedAt) {
		this.editedAt = editedAt;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the isEdited
	 */
	public boolean isEdited() {
		return isEdited;
	}
	/**
	 * @param isEdited the isEdited to set
	 */
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [commentId=");
		builder.append(commentId);
		builder.append(", user=");
		builder.append(user);
		builder.append(", book=");
		builder.append(book);
		builder.append(", description=");
		builder.append(description);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", editedAt=");
		builder.append(editedAt);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", isEdited=");
		builder.append(isEdited);
		builder.append("]");
		return builder.toString();
	}


}
