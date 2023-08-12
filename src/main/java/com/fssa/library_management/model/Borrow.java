package com.fssa.library_management.model;

import java.time.LocalDate;

public class Borrow {
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private double fine;

    public User getUser() {
        return user;
    }

    public void setUser(User userName) {
        this.user = userName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book bookName) {
        this.book = bookName;
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

    @Override
    public String toString() {
        return "Borrow{" +
                "user=" + user +
                ", book=" + book +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", dueDate=" + dueDate +
                ", fine=" + fine +
                '}';
    }
}
