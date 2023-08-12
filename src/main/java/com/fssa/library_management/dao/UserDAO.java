package com.fssa.library_management.dao;

import com.fssa.library_management.exceptions.DAOException;
import com.fssa.library_management.model.User;
import com.fssa.library_management.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fssa.library_management.utils.ResultSetUtils.buildUserFromResultSet;

public class UserDAO {

    private UserDAO() {

    }

    public static boolean createUser(User user) throws DAOException {
        String query = "INSERT INTO users (user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            throw new DAOException(e);
        }
    }


    public static User getUser(String searchValue) throws DAOException {
        User user = null;
        String query = "SELECT user_id, user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image " +
                "FROM users WHERE email_id = ? AND isActive = true";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, searchValue);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = buildUserFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    public static List<User> getAllUsers() throws DAOException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT user_id, user_name, email_id, mobile_no, password, gender, dob, created_date, isActive, isAdmin, profile_image " +
                "FROM users";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                User user = buildUserFromResultSet(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return userList;
    }

    public static boolean updateUser(User user) throws DAOException {
        String query = "UPDATE users SET " +
                "user_name = ?, profile_image = ?, mobile_no = ?, gender = ?, dob = ? " +
                "WHERE email_id = ?";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, user.getName());
            pst.setString(2, user.getProfileImage());
            pst.setLong(3, user.getMobileNo());
            pst.setString(4, String.valueOf(user.getGender()));
            pst.setDate(5, Date.valueOf(user.getDob()));
            pst.setString(6, user.getEmail());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    public static boolean deleteUser(String stringValue) throws DAOException {
        String query = "UPDATE users SET isActive = false WHERE email_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setString(1, stringValue);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


}
