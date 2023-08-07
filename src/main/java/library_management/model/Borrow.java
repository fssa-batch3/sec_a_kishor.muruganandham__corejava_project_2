package library_management.model;

import java.time.LocalDate;

public class Borrow {
    private int userId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private double fine;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userName) {
        this.userId = userName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookName) {
        this.bookId = bookName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(10);
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

}
