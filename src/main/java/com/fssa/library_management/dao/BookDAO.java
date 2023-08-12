package com.fssa.library_management.dao;

import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.model.Book;
import com.fssa.library_management.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.fssa.library_management.utils.ResultSetUtils.buildBookFromResultSet;

public class BookDAO {

    private BookDAO() {

    }
    public static Book getBookByTitle(String bookName) throws DAOException {
        Book book = null;
        String query = "SELECT * FROM books WHERE title = ? AND isActive = true AND available_copies >= 1";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, bookName);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    book = buildBookFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return book;
    }

    public static boolean addBook(Book book) throws DAOException {
        String query = "INSERT INTO books (title, author, publisher, genre, language, description, total_copies, available_copies, loaned_copies, cover_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublisher());
            pst.setString(4, book.getGenre());
            pst.setString(5, book.getLanguage());
            pst.setString(6, book.getDescription());
            pst.setInt(7, book.getTotalCopies());
            pst.setInt(8, book.getAvailableCopies());
            pst.setInt(9, book.getLoanedCopies());
            pst.setString(10, book.getCoverImage());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public static List<Book> getAllBooks() throws DAOException {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM books WHERE isActive = true";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Book book = buildBookFromResultSet(rs);
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bookList;
    }

    public static boolean updateBook(Book book) throws DAOException {
        String query = "UPDATE books SET " +
                "title = ?, description = ?, total_copies = ?, " +
                "cover_image = ?, genre = ?, language = ?, " +
                "author = ?, publisher = ? " +
                "WHERE book_id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getDescription());
            pst.setInt(3, book.getTotalCopies());
            pst.setString(4, book.getCoverImage());
            pst.setString(5, book.getGenre());
            pst.setString(6, book.getLanguage());
            pst.setString(7, book.getAuthor());
            pst.setString(8, book.getPublisher());
            pst.setInt(9, book.getBookId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public static void updateBookCopies(int bookId, int loanedCopiesChange, int availableCopiesChange) throws DAOException {
        String query = "UPDATE books SET loaned_copies = loaned_copies + ?, available_copies = available_copies + ? " +
                "WHERE book_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, loanedCopiesChange);
            pst.setInt(2, availableCopiesChange);
            pst.setInt(3, bookId);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public static boolean deleteBook(String bookName) throws DAOException {
        String query = "UPDATE books SET isActive = false WHERE title = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, bookName);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
