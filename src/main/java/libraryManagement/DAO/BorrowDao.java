package libraryManagement.DAO;

import libraryManagement.exceptions.ServiceException;
import libraryManagement.model.Borrow;
import libraryManagement.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao {

    private static final String USER_ID = "user_id";
    private static final String RETURN_DATE = "return_date";
    private static final String BORROW_DATE = "borrow_date";
    private static final String BOOK_ID = "book_id";
    // Private constructor to prevent instantiation
    private BorrowDao() {
        // // Do nothing (empty constructor)
    }

    public static boolean borrowBook(Borrow borrow) throws ServiceException {
        String query = "INSERT INTO borrows (user_id, book_id, borrow_date, due_date) " +
                "VALUES (?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, borrow.getUserId());
            pst.setInt(2, borrow.getBookId());
            pst.setDate(3, Date.valueOf(borrow.getBorrowDate()));
            pst.setDate(4, Date.valueOf(borrow.getDueDate()));

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new ServiceException("Error fetching borrow data: " + e.getMessage(), e);
        }
    }

    public static boolean returnBook(Borrow borrow) throws ServiceException {
        String query = "UPDATE borrows SET isReturned = true, return_date = ?, fine = ? WHERE user_id = ? AND book_id = ? AND isReturned = false;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setDate(1, Date.valueOf(borrow.getReturnDate()));
            pst.setDouble(2, borrow.getFine());
            pst.setInt(3, borrow.getUserId());
            pst.setInt(4, borrow.getBookId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceException("Error fetching return data: " + e.getMessage(), e);
        }
    }


    public static List<Borrow> getBorrowsByUser(int userId) throws ServiceException {
        List<Borrow> borrowList = null;
        String query = "SELECT user_id, book_id, borrow_date, return_date " +
                "FROM borrows " +
                "WHERE user_id = ?;";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    borrowList = new ArrayList<>();
                    Borrow borrow = new Borrow();
                    borrow.setUserId(rs.getInt(USER_ID));
                    borrow.setBookId(rs.getInt(BOOK_ID));
                    borrow.setBorrowDate(rs.getDate(BORROW_DATE).toLocalDate());
                    Date returnDate = rs.getDate(RETURN_DATE);
                    if (returnDate != null) {
                        borrow.setReturnDate(returnDate.toLocalDate());
                    }
                    borrowList.add(borrow);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching borrow data of the user: " + e.getMessage(), e);
        }
        return borrowList;
    }

    public static List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
        List<Borrow> borrowList = null;
        String query = "SELECT user_id, book_id, borrow_date, return_date " +
                "FROM borrows " +
                "WHERE book_id = ?;";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, bookId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    borrowList = new ArrayList<>();
                    Borrow borrow = new Borrow();
                    borrow.setUserId(rs.getInt(USER_ID));
                    borrow.setBookId(rs.getInt(BOOK_ID));
                    borrow.setBorrowDate(rs.getDate(BORROW_DATE).toLocalDate());
                    Date returnDate = rs.getDate(RETURN_DATE);
                    if (returnDate != null) {
                        borrow.setReturnDate(returnDate.toLocalDate());
                    }
                    borrowList.add(borrow);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching borrow data of the book: " + e.getMessage(), e);
        }
        return borrowList;
    }

    public static Borrow getBorrowByUserAndBook(int userId, int bookId) throws ServiceException {
        String query = "SELECT user_id, book_id, borrow_date, return_date " +
                "FROM borrows " +
                "WHERE user_id = ? AND book_id = ? AND isReturned = FALSE;";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, userId);
            pst.setInt(2, bookId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Borrow borrow = new Borrow();
                    borrow.setUserId(rs.getInt(USER_ID));
                    borrow.setBookId(rs.getInt(BOOK_ID));
                    borrow.setBorrowDate(rs.getDate(BORROW_DATE).toLocalDate());
                    Date returnDate = rs.getDate(RETURN_DATE);
                    if (returnDate != null) {
                        borrow.setReturnDate(returnDate.toLocalDate());
                    }
                    return borrow;
                }
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching borrow data of user and book: " + e.getMessage(), e);
        }
        return null;
    }

    public static List<Borrow> getAllBorrows() throws ServiceException {
        List<Borrow> borrowList = new ArrayList<>();
        String query = "SELECT * FROM borrows;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Borrow borrow = new Borrow();
                borrow.setUserId(rs.getInt(USER_ID));
                borrow.setBookId(rs.getInt(BOOK_ID));
                borrow.setBorrowDate(rs.getDate(BORROW_DATE).toLocalDate());
                borrow.setReturnDate(rs.getDate(RETURN_DATE).toLocalDate());
                borrowList.add(borrow);
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching all borrow data's: " + e.getMessage(), e);
        }
        return borrowList;
    }

    public static int getAvailableCopiesCount(int bookId) throws ServiceException {
        String query = "SELECT available_copies FROM books WHERE book_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, bookId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching available copies count: " + e.getMessage(), e);
        }
        return 0;
    }

    public static int getBorrowedBooksCountByUser(int userId) throws ServiceException {
        String query = "SELECT COUNT(*) FROM borrows WHERE user_id = ? AND isReturned = false;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new ServiceException("Error fetching borrowed books count: " + e.getMessage(), e);
        }
        return 0;
    }
}
