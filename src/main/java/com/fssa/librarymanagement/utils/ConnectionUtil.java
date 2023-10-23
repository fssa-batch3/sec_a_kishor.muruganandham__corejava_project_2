package com.fssa.librarymanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.librarymanagement.exceptions.DatabaseConnectionException;

/**
 * A utility class for managing database connections.
 */
public class ConnectionUtil {

	// Private constructor to prevent instantiation
	private ConnectionUtil() {
		// Do nothing (empty constructor)
	}

	/**
	 * Gets a connection to the database.
	 *
	 * @return A database connection
	 * @throws RuntimeException If unable to connect to the database
	 */
	public static Connection getConnection() throws DatabaseConnectionException {

		// Database URL and credentials
		final String dbUrl;
		final String dbUser;
		final String dbPassword;

		// Fetch database configuration from environment variables
		dbUrl = System.getenv("DB_URL");
		dbUser = System.getenv("DB_USER");
		dbPassword = System.getenv("DB_PASSWORD");

		try {
			// Load the MySQL database driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish and return a connection
			return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Unable to connect to the database", e);
		} catch (ClassNotFoundException e) {
			throw new DatabaseConnectionException("Database driver class not found", e);
		}
	}
}
