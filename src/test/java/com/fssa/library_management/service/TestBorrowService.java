package com.fssa.library_management.service;

import com.fssa.library_management.exceptions.ServiceException;
import com.fssa.library_management.model.Borrow;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBorrowService {

    private BorrowService borrowService;
    private Borrow borrow;

    @BeforeEach
    public void setUp() {
        borrowService = new BorrowService();
        borrow = new Borrow();
        borrow.setUserId(1);
        borrow.setBookId(1);
        borrow.setBorrowDate(LocalDate.now());
    }

    @Order(1)
    @Test
    void testValidBorrowBook() {
        try {
            String result = borrowService.borrowBook(borrow);
            assertEquals("Book borrowed successfully.", result);
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Order(2)
    @Test
    void testInvalidBorrowBook() {
        assertThrows(ServiceException.class, () -> borrowService.borrowBook(borrow));
    }

    @Order(3)
    @Test
    void testValidReturnBook() {
        try {
            borrow.setReturnDate(borrow.getDueDate().plusDays(3));
            String result = borrowService.returnBook(borrow);
            assertEquals("Added return date successfully.", result);
            assertEquals(30, borrow.getFine());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(4)
    void testInvalidReturnBook() {
        assertThrows(ServiceException.class, () -> borrowService.returnBook(borrow));
    }

    @Test
    @Order(5)
    void testValidGetBorrowsByUser() {
        try {
            List<Borrow> borrows = borrowService.getBorrowsByUser(borrow.getUserId());
            assertNotNull(borrows);
            assertFalse(borrows.isEmpty());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(6)
    void testInvalidGetBorrowsByUser() {
        try {
            List<Borrow> borrows = borrowService.getBorrowsByUser(114);
            assertNull(borrows);
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(7)
    void testGetBorrowsByBook() {
        try {
            List<Borrow> borrows = borrowService.getBorrowsByBook(borrow.getBookId());
            assertNotNull(borrows);
            assertFalse(borrows.isEmpty());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(8)
    void testInvalidGetBorrowsByBook() {
        try {
            List<Borrow> borrows = borrowService.getBorrowsByBook(54);
            assertNull(borrows);
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }

    @Test
    @Order(9)
    void testGetAllBorrows() {
        try {
            List<Borrow> borrows = borrowService.getAllBorrows();
            assertNotNull(borrows);
            assertFalse(borrows.isEmpty());
        } catch (ServiceException e) {
            e.printStackTrace();
            fail("Should not throw ServiceException");
        }
    }
}
