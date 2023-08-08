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

public class BookDao {

    private BookDao() {
        throw new IllegalCallerException("Class Utility");
    }

    public static Book getBookByTitle(String bookName) throws DAOException {
        Book book = null;
        String query = "SELECT * FROM books WHERE title = ? AND isActive = true AND available_copies >= 1;";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, bookName);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    book = new Book();
                    book.setBookId(rs.getInt("book_id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setGenre(rs.getString("genre"));
                    book.setLanguage(rs.getString("language"));
                    book.setDescription(rs.getString("description"));
                    book.setTotalCopies(rs.getInt("total_copies"));
                    book.setAvailableCopies(rs.getInt("available_copies"));
                    book.setLoanedCopies(rs.getInt("loaned_copies"));
                    book.setCoverImage(rs.getString("cover_image"));
                    book.setActive(rs.getBoolean("isActive"));
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return book;
    }

    public static boolean addBook(Book book) throws DAOException {
        String query = "INSERT INTO books (title, author, publisher, genre, language, description, total_copies, available_copies, loaned_copies, cover_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
        String query = "SELECT * FROM books WHERE isActive = true;;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setGenre(rs.getString("genre"));
                book.setLanguage(rs.getString("language"));
                book.setDescription(rs.getString("description"));
                book.setTotalCopies(rs.getInt("total_copies"));
                book.setAvailableCopies(rs.getInt("available_copies"));
                book.setLoanedCopies(rs.getInt("loaned_copies"));
                book.setCoverImage(rs.getString("cover_image"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return bookList;
    }

    public static Book updateBook(Book book) throws DAOException {
        StringBuilder queryBuilder = new StringBuilder("UPDATE books SET ");
        List<String> setColumns = new ArrayList<>();
        List<Object> setValues = new ArrayList<>();

        if (book.getTitle() != null) {
            setColumns.add("title = ?");
            setValues.add(book.getTitle());
        }
        if (book.getDescription() != null) {
            setColumns.add("description = ?");
            setValues.add(book.getDescription());
        }
        if (book.getTotalCopies() >= 0) {
            setColumns.add("total_copies = ?");
            setValues.add(book.getTotalCopies());
        }
        if (book.getAvailableCopies() >= 0) {
            setColumns.add("available_copies = ?");
            setValues.add(book.getAvailableCopies());
        }
        if (book.getLoanedCopies() >= 0) {
            setColumns.add("loaned_copies = ?");
            setValues.add(book.getLoanedCopies());
        }
        if (book.getCoverImage() != null) {
            setColumns.add("cover_image = ?");
            setValues.add(book.getCoverImage());
        }
        if (book.getGenre() != null) {
            setColumns.add("genre = ?");
            setValues.add(book.getGenre());
        }
        if (book.getLanguage() != null) {
            setColumns.add("language = ?");
            setValues.add(book.getLanguage());
        }
        if (book.getAuthor() != null) {
            setColumns.add("author = ?");
            setValues.add(book.getAuthor());
        }
        if (book.getPublisher() != null) {
            setColumns.add("publisher = ?");
            setValues.add(book.getPublisher());
        }

        if (setColumns.isEmpty()) {
            return book;
        }
        queryBuilder.append(String.join(", ", setColumns));
        queryBuilder.append(" WHERE book_id = ?;");
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(queryBuilder.toString())) {

            int index = 1;
            for (Object value : setValues) {
                pst.setObject(index++, value);
            }
            pst.setInt(index, book.getBookId());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                return getBookByTitle(book.getTitle());
            } else {
                return book;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public static void updateBookCopies(int bookId, int loanedCopiesChange, int availableCopiesChange) throws DAOException {
        String query = "UPDATE books SET loaned_copies = loaned_copies + ?, available_copies = available_copies + ? WHERE book_id = ?;";
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
        String query = "UPDATE books SET isActive = false WHERE title = ?;";
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
