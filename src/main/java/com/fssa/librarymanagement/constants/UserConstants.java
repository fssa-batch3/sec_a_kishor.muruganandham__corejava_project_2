package com.fssa.librarymanagement.constants;

public class UserConstants {

	public static final String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty";
	public static final String INVALID_EMAIL_FORMAT = "Invalid email format. Email should be in the format " +
			"'user@example.com'";
	public static final String MOBILE_NUMBER_CANNOT_BE_EMPTY = "Mobile number cannot be empty";
	public static final String PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty";
	public static final String PASSWORD_IS_LESS_THAN_THE_EXPECTED_LENGTH_OF_8_CHARACTERS = "Password is less than the " +
			"expected length of 8 characters";
	public static final String INVALID_PASSWORD_FORMAT = "Password must contain at least one uppercase letter, one " +
			"lowercase letter, and one digit";
	public static final String INVALID_GENDER_GENDER_MUST_BE_M_OR_F = "Invalid gender. Gender must be 'M' or 'F'.";
	public static final String PROFILE_IMAGE_URL_CANNOT_BE_EMPTY = "Profile image URL cannot be empty";
	public static final String INVALID_PROFILE_IMAGE_URL = "Invalid profile image URL. URL should be in the format " +
			"'http://www.example.com/index.html'";
	public static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty";
	public static final String INVALID_NAME_FORMAT = "Invalid name format. Name should start with a capital letter and" +
			" may contain letters, spaces, hyphens, and apostrophes.";
	public static final String DATE_OF_BIRTH_CANNOT_BE_EMPTY = "Date of birth cannot be empty";
	public static final String INVALID_DATE_OF_BIRTH_MUST_BE_AT_LEAST_10_YEARS_OLD = "Invalid date of birth. Must be " +
			"at least 10 years old.";
	public static final String DATE_OF_BIRTH_CANNOT_BE_IN_THE_FUTURE = "Date of birth cannot be in the future";
	private UserConstants() {
		// Private constructor to prevent instantiation
	}
}
