package com.fssa.library_management.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {


    // Private constructor to prevent instantiation
    private ConnectionUtil() {
        // Do nothing (empty constructor)
    }

    // Call the database connection
    public static Connection getConnection() throws SQLException {

        // Database URL and credentials
        final String dbUrl;
        final String dbUser;
        final String dbPassword;

        if (System.getenv("CI") != null) {
            dbUrl = System.getenv("DB_URL");
            dbUser = System.getenv("DB_USER");
            dbPassword = System.getenv("DB_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            dbUrl = env.get("DB_URL");
            dbUser = env.get("DB_USER");
            dbPassword = env.get("DB_PASSWORD");
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }


}
