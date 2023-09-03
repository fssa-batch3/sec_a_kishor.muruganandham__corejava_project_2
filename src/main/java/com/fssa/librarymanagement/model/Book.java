package com.fssa.librarymanagement.model;

/**
 * Represents a book in library management system
 *
 * @author KishorMuruganandham
 */
public class Book {
	private int bookId;
	private String title;
	private String author;
	private String publisher;
	private String genre;
	private String language;
	private String description;
	private int totalCopies;
	private int availableCopies;
	private int loanedCopies;
	private String coverImage = "https://dummyimage.com/600x800/000/ffffff.png&text=Failed+to+Load+Image";

	/**
	 * Constructs a new Book object with default values.
	 */
	public Book() {
		// Default constructor
	}

	/**
	 * Get the book's id.
	 *
	 * @return The id of the book.
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * Set the book's id.
	 *
	 * @param bookId The id of the book.
	 */

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Get the book's Title.
	 *
	 * @return The Title of the book.
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * Set the book's Title.
	 *
	 * @param title The Title of the book.
	 */

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the book's Author.
	 *
	 * @return The Author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the book's Author.
	 *
	 * @param author The Author of the book.
	 */

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the book's Publisher.
	 *
	 * @return The Publisher of the book.
	 */

	public String getPublisher() {
		return publisher;
	}

	/**
	 * Set the book's Publisher.
	 *
	 * @param publisher The Publisher of the book.
	 */

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Get the book's Genre.
	 *
	 * @return The Genre of the book.
	 */

	public String getGenre() {
		return genre;
	}

	/**
	 * Set the book's Genre.
	 *
	 * @param genre The Genre of the book.
	 */

	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Get the book's Language.
	 *
	 * @return The Language of the book.
	 */

	public String getLanguage() {
		return language;
	}

	/**
	 * Set the book's Language.
	 *
	 * @param language The Language of the book.
	 */

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Get the book's Description.
	 *
	 * @return The Description of the book.
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Set the book's Description.
	 *
	 * @param description The Description of the book.
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the book's Total Copies.
	 *
	 * @return The Total Copies of the book.
	 */

	public int getTotalCopies() {
		return totalCopies;
	}

	/**
	 * Set the book's Total Copies.
	 *
	 * @param totalCopies The Total Copies of the book.
	 */

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	/**
	 * Get the book's Available Copies.
	 *
	 * @return The Available Copies of the book.
	 */

	public int getAvailableCopies() {
		return availableCopies;
	}

	/**
	 * Set the book's Available Copies.
	 *
	 * @param availableCopies The Available Copies of the book.
	 */
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	/**
	 * Get the book's Loaned Copies.
	 *
	 * @return The Loaned Copies of the book.
	 */

	public int getLoanedCopies() {
		return loanedCopies;
	}

	/**
	 * Set the book's Loaned Copies.
	 *
	 * @param loanedCopies The Loaned Copies of the book.
	 */

	public void setLoanedCopies(int loanedCopies) {
		this.loanedCopies = loanedCopies;
	}

	/**
	 * Get the book's Cover Image.
	 *
	 * @return The Cover Image of the book.
	 */

	public String getCoverImage() {
		return coverImage;
	}

	/**
	 * Set the book's Cover Image.
	 *
	 * @param coverImage The Cover Image of the book.
	 */

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return A string representation of the User object.
	 */
	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", publisher='" + publisher + '\'' +
				", genre='" + genre + '\'' +
				", language='" + language + '\'' +
				", description='" + description + '\'' +
				", totalCopies=" + totalCopies +
				", availableCopies=" + availableCopies +
				", loanedCopies=" + loanedCopies +
				", coverImage='" + coverImage + '\'' +
				'}';
	}
}
