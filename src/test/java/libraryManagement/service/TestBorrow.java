package libraryManagement.service;

import libraryManagement.exceptions.ServiceException;
import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.Borrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestBorrow {
    private BorrowService borrowService;
    private Borrow borrow;

    @BeforeEach
    public void setUp() {
        borrowService = new BorrowService();
        borrow = new Borrow();
        borrow.setBorrowDate(LocalDate.now());
        borrow.setBookId(1);
        borrow.setUserId(6);
    }

    @Test
    void testBorrowBook() {
        try {
            String result = borrowService.borrowBook(borrow);
            assertEquals("Book borrowed successfully.", result);
        } catch (ValidationException | ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReturnBook() {
        try {
            borrow.setReturnDate(LocalDate.parse("2023-08-03"));
            String result = borrowService.returnBook(borrow);
            assertEquals("Added return date successfully.", result);
        } catch (ServiceException | ValidationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetBookByBook() {
        try {
            List<Borrow> result = borrowService.getBorrowsByBook(borrow.getBookId());
            System.out.println(result.get(0).getBookId());
            System.out.println(result.get(0).getBorrowId());
            System.out.println(result.get(0).getUserId());
            System.out.println(result.get(0).getBorrowDate());
            System.out.println(result.get(0).getUserId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetBookByUser() {
        try {
            List<Borrow> result = borrowService.getBorrowsByUser(borrow.getUserId());
            System.out.println(result.get(0).getBookId());
            System.out.println(result.get(0).getBorrowId());
            System.out.println(result.get(0).getUserId());
            System.out.println(result.get(0).getBorrowDate());
            System.out.println(result.get(0).getUserId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
