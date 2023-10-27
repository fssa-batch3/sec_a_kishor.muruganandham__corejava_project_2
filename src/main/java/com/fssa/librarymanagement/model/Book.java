package com.fssa.librarymanagement.model;
/**
 * Represents a book in the library management system.
 * 
 * @author KishorMuruganandham
 * 
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
    private int pages;
    private String coverImage = "https://dummyimage.com/600x800/000/ffffff.png&text=Failed+to+Load+Image";

    /**
     * Constructs a new Book object with default values.
     */
    public Book() {
        // Default constructor
    }

    /**
     * Get the book's ID.
     *
     * @return The ID of the book.
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Set the book's ID.
     *
     * @param bookId The ID of the book.
     */

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    /**
     * Get the book's title.
     *
     * @return The title of the book.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Set the book's title.
     *
     * @param title The title of the book.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the book's author.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the book's author.
     *
     * @param author The author of the book.
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get the book's publisher.
     *
     * @return The publisher of the book.
     */

    public String getPublisher() {
        return publisher;
    }

    /**
     * Set the book's publisher.
     *
     * @param publisher The publisher of the book.
     */

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the book's genre.
     *
     * @return The genre of the book.
     */

    public String getGenre() {
        return genre;
    }

    /**
     * Set the book's genre.
     *
     * @param genre The genre of the book.
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the book's language.
     *
     * @return The language of the book.
     */

    public String getLanguage() {
        return language;
    }

    /**
     * Set the book's language.
     *
     * @param language The language of the book.
     */

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get the book's description.
     *
     * @return The description of the book.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Set the book's description.
     *
     * @param description The description of the book.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the total number of copies of the book.
     *
     * @return The total number of copies of the book.
     */

    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * Set the total number of copies of the book.
     *
     * @param totalCopies The total number of copies of the book.
     */

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    /**
     * Get the number of available copies of the book.
     *
     * @return The number of available copies of the book.
     */

    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Set the number of available copies of the book.
     *
     * @param availableCopies The number of available copies of the book.
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * Get the number of loaned copies of the book.
     *
     * @return The number of loaned copies of the book.
     */

    public int getLoanedCopies() {
        return loanedCopies;
    }

    /**
     * Set the number of loaned copies of the book.
     *
     * @param loanedCopies The number of loaned copies of the book.
     */

    public void setLoanedCopies(int loanedCopies) {
        this.loanedCopies = loanedCopies;
    }

    /**
     * Get the book's cover image.
     *
     * @return The cover image of the book.
     */

    public String getCoverImage() {
        return coverImage;
    }

    /**
     * Set the book's cover image.
     *
     * @param coverImage The cover image of the book.
     */

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
    
    /**
     * Get the number of pages in the book.
     *
     * @return The number of pages in the book.
     */
    public int getPages() {
        return pages;
    }
    
    /**
     * Set the number of pages in the book.
     *
     * @param pages The number of pages in the book.
     */
    public void setPages(int pages) {
        this.pages = pages;
    }
}
