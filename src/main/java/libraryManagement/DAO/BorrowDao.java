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

    public static boolean borrowBook(Borrow borrow) throws ServiceException {
        String query = "INSERT INTO borrows (user_id, book_id, borrow_date) " +
                "VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, borrow.getUserId());
            pst.setInt(2, borrow.getBookId());
            pst.setDate(3, Date.valueOf(borrow.getBorrowDate()));

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    
    public static boolean returnBook(Borrow borrow) throws ServiceException {
        String query = "UPDATE borrows SET isReturned = true, return_date = ? WHERE user_id = ? AND book_id = ? AND isReturned = false;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setDate(1, Date.valueOf(borrow.getReturnDate()));
            pst.setInt(2, borrow.getUserId());
            pst.setInt(3, borrow.getBookId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }


    public static List<Borrow> getBorrowsByUser(int userId) throws ServiceException {
        List<Borrow> borrowList = new ArrayList<>();
        String query = "SELECT b.user_id, b.book_id, b.borrow_date, b.return_date " +
                "FROM borrows b " +
                "INNER JOIN users u ON b.user_id = u.user_id " +
                "INNER JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE u.user_id = ?;";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
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
            throw new ServiceException(e);
        }
        return borrowList;
    }

    public static List<Borrow> getBorrowsByBook(int bookId) throws ServiceException {
        List<Borrow> borrowList = null;
        String query = "SELECT b.user_id, b.book_id, b.borrow_date, b.return_date " +
                "FROM borrows b " +
                "INNER JOIN users u ON b.user_id = u.user_id " +
                "INNER JOIN books bk ON b.book_id = bk.book_id " +
                "WHERE bk.book_id = ?;";

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
            throw new ServiceException(e);
        }
        return borrowList;
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
            throw new ServiceException(e);
        }
        return borrowList;
    }
}
