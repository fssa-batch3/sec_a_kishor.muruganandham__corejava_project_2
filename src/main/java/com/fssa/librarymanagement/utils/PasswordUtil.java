package com.fssa.librarymanagement.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for hashing and checking passwords using BCrypt.
 */
public class PasswordUtil {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private PasswordUtil() {
		// Do nothing (empty constructor)
	}

	/**
	 * Hashes a plain password using BCrypt.
	 *
	 * @param plainPassword The plain password to hash
	 * @return The hashed password
	 */
	public static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	/**
	 * Checks if a plain password matches a hashed password using BCrypt.
	 *
	 * @param plainPassword  The plain password to check
	 * @param hashedPassword The hashed password to compare against
	 * @return True if the plain password matches the hashed password, false
	 * otherwise
	 */
	public static boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
}
