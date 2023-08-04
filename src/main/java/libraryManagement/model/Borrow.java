package libraryManagement.model;

import java.time.LocalDate;

public class Borrow {
    private int borrowId;
    private int userId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public int getBorrowId() {
        return borrowId;
    }

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
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

}
