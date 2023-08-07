package library_management.DAO;

import library_management.exceptions.ServiceException;
import library_management.model.User;
import library_management.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    // Private constructor to prevent instantiation
    private UserDao() {
        // // Do nothing (empty constructor)
    }

    public static boolean createUser(User user) throws ServiceException {
        String query = "INSERT INTO users (user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setLong(3, user.getMobileNo());
            pst.setString(4, user.getPassword());
            pst.setString(5, String.valueOf(user.getGender()));
            pst.setDate(6, Date.valueOf(user.getDob()));
            pst.setTimestamp(7, user.getCreatedDate());
            pst.setBoolean(8, true);
            pst.setBoolean(9, user.isAdmin());
            pst.setString(10, user.getProfileImage());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }


    public static User getUser(String searchValue) throws ServiceException {
        User userFromDB = null;
        String query = "SELECT user_id, user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image " +
                "FROM users WHERE email_id = ? AND isActive = true;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, searchValue);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    userFromDB = new User();
                    userFromDB.setName(rs.getString("user_name"));
                    userFromDB.setEmail(rs.getString("email_id"));
                    userFromDB.setMobileNo(rs.getLong("mobile_no"));
                    userFromDB.setPassword(rs.getString("password"));
                    userFromDB.setGender(rs.getString("gender").charAt(0));
                    userFromDB.setDob(rs.getDate("dob").toLocalDate());
                    userFromDB.setCreatedDate(rs.getTimestamp("created_date"));
                    userFromDB.setActive(rs.getBoolean("isActive"));
                    userFromDB.setAdmin(rs.getBoolean("isAdmin"));
                    userFromDB.setProfileImage(rs.getString("profile_image"));
                }
            }

        } catch (SQLException e) {
            throw new ServiceException(e);
        }
        return userFromDB;
    }

    public static List<User> getAllUsers() throws ServiceException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT user_id, user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image " +
                "FROM users;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email_id"));
                user.setMobileNo(rs.getLong("mobile_no"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender").charAt(0));
                user.setDob(rs.getDate("dob").toLocalDate());
                user.setCreatedDate(rs.getTimestamp("created_date"));
                user.setActive(rs.getBoolean("isActive"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setProfileImage(rs.getString("profile_image"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
        return userList;
    }

    public static User updateUser(User user) throws ServiceException {
        StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
        List<String> setColumns = new ArrayList<>();
        List<Object> setValues = new ArrayList<>();

        if (user.getName() != null) {
            setColumns.add("user_name = ?");
            setValues.add(user.getName());
        }
        if (user.getProfileImage() != null) {
            setColumns.add("profile_image = ?");
            setValues.add(user.getEmail());
        }
        if (user.getMobileNo() != 0) {
            setColumns.add("mobile_no = ?");
            setValues.add(user.getMobileNo());
        }
        if (user.getPassword() != null) {
            setColumns.add("password = ?");
            setValues.add(user.getPassword());
        }
        if (user.getGender() != 0) {
            setColumns.add("gender = ?");
            setValues.add(String.valueOf(user.getGender()));
        }
        if (user.getDob() != null) {
            setColumns.add("dob = ?");
            setValues.add(user.getDob());
        }
        if (user.getCreatedDate() != null) {
            setColumns.add("created_date = ?");
            setValues.add(user.getCreatedDate());
        }

        if (setColumns.isEmpty()) {
            return user;
        }
        queryBuilder.append(String.join(", ", setColumns));
        queryBuilder.append(" WHERE email_id = ?;");
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(queryBuilder.toString())) {

            int index = 1;
            for (Object value : setValues) {
                pst.setObject(index++, value);
            }
            pst.setString(index, user.getEmail());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                return UserDao.getUser(user.getEmail());
            } else {
                return user;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }


    public static boolean deleteUser(String stringValue) throws ServiceException {
        String query = "UPDATE users SET isActive = false WHERE email_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, stringValue);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }


}
