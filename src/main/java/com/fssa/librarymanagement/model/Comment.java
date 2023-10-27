/**
 * 
 */
package com.fssa.librarymanagement.model;

import java.time.LocalDateTime;

/**
 * Represents a comment associated with a book in a library system.
 * 
 * @author KishorMuruganandham
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
     * Get the comment's ID.
     *
     * @return The ID of the comment.
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * Set the comment's ID.
     *
     * @param commentId The ID of the comment.
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * Get the user who posted the comment.
     *
     * @return The user who posted the comment.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user who posted the comment.
     *
     * @param user The user who posted the comment.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the book associated with the comment.
     *
     * @return The book associated with the comment.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set the book associated with the comment.
     *
     * @param book The book associated with the comment.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get the description of the comment.
     *
     * @return The description of the comment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the comment.
     *
     * @param description The description of the comment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the date and time when the comment was created.
     *
     * @return The date and time when the comment was created.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the date and time when the comment was created.
     *
     * @param createdAt The date and time when the comment was created.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the date and time when the comment was last edited.
     *
     * @return The date and time when the comment was last edited.
     */
    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    /**
     * Set the date and time when the comment was last edited.
     *
     * @param editedAt The date and time when the comment was last edited.
     */
    public void setEditedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
    }

    /**
     * Check if the comment is currently active.
     *
     * @return true if the comment is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Set the comment's active status.
     *
     * @param isActive true if the comment should be active, false otherwise.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Check if the comment has been edited.
     *
     * @return true if the comment has been edited, false otherwise.
     */
    public boolean isEdited() {
        return isEdited;
    }

    /**
     * Set the comment's edited status.
     *
     * @param isEdited true if the comment has been edited, false otherwise.
     */
    public void setEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }

    /**
     * Check if the comment is trusted.
     *
     * @return true if the comment is trusted, false otherwise.
     */
    public boolean isTrusted() {
        return isTrusted;
    }

    /**
     * Set the comment's trusted status.
     *
     * @param isTrusted true if the comment is trusted, false otherwise.
     */
    public void setTrusted(boolean isTrusted) {
        this.isTrusted = isTrusted;
    }
}
