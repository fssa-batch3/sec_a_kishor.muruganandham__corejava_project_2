package com.fssa.librarymanagement.utils;



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

		dbUrl = System.getenv("DB_URL");
		dbUser = System.getenv("DB_USER");
		dbPassword = System.getenv("DB_PASSWORD");
 
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	}

}
