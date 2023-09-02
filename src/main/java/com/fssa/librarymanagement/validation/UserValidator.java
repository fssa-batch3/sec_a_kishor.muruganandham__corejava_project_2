package com.fssa.librarymanagement.validation;

import com.fssa.librarymanagement.constants.UserConstants;
import com.fssa.librarymanagement.exceptions.ValidationException;
import com.fssa.librarymanagement.model.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

/**
 * This class provides Validations for all User data.
 *
 * @author KishorMuruganandham
 */
public class UserValidator {

	private User user; // The user object to be validated

	/**
	 * Constructs a UserValidator instance with a specific user to validate.
	 *
	 * @param user The User object to be validated.
	 */
	public UserValidator(User user) {
		this.user = user;
	}

	/**
	 * Constructs a UserValidator instance without a specific user.
	 * This constructor can be used when validating users with individual validation methods.
	 */
	public UserValidator() {
		// Default constructor
	}

	/**
	 * Validates all User attributes.
	 *
	 * @throws ValidationException If any validation fails
	 */
	public void validateAll() throws ValidationException {
		if (validateMobileNo(user.getMobileNo()) && validatePassword(user.getPassword())
				&& validateGender(user.getGender()) && validateEmail(user.getEmail())
				&& validateProfileImage(user.getProfileImage()) && validateName(user.getName())) {
			validateDateOfBirth(user.getDob());
		}
	}

	/**
	 * Validates an email address.
	 *
	 * @param email The email address to validate
	 * @return true if email is valid
	 * @throws ValidationException If email is invalid or empty
	 */
	public boolean validateEmail(String email) throws ValidationException {
		if (email == null) {
			throw new ValidationException(UserConstants.EMAIL_CANNOT_BE_EMPTY);
		}
		final String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		email = email.trim();
		if (email.isEmpty()) {
			throw new ValidationException(UserConstants.EMAIL_CANNOT_BE_EMPTY);
		}
		boolean result = Pattern.compile(emailRegEx).matcher(email).matches();
		if (!result) {
			throw new ValidationException(UserConstants.INVALID_EMAIL_FORMAT);
		}
		return true;
	}

	/**
	 * Validates a mobile number.
	 *
	 * @param mobileNo The mobile number to validate
	 * @return true if mobile number is valid
	 * @throws ValidationException If mobile number is empty
	 */
	public boolean validateMobileNo(long mobileNo) throws ValidationException {
		String mobileNoStr = String.valueOf(mobileNo);
		if (mobileNo == 0) {
			throw new ValidationException(UserConstants.MOBILE_NUMBER_CANNOT_BE_EMPTY);
		}
		// Length check
		if (mobileNoStr.length() != 10) {
			throw new ValidationException(UserConstants.INVALID_MOBILE_NUMBER_LENGTH);
		}

		return true;
	}

	/**
	 * Validates a password.
	 *
	 * @param password The password to validate
	 * @return true if the password is valid
	 * @throws ValidationException If the password is empty or too short
	 */
	public boolean validatePassword(String password) throws ValidationException {
		if (password == null) {
			throw new ValidationException(UserConstants.PASSWORD_CANNOT_BE_EMPTY);
		}
		password = password.trim();
		if (password.isEmpty()) {
			throw new ValidationException(UserConstants.PASSWORD_CANNOT_BE_EMPTY);
		} else if (password.length() < 8) {
			throw new ValidationException(UserConstants.PASSWORD_IS_LESS_THAN_THE_EXPECTED_LENGTH_OF_8_CHARACTERS);
		} else if (!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$").matcher(password).matches()) {
			throw new ValidationException(UserConstants.INVALID_PASSWORD_FORMAT);
		}
		return true;
	}


	/**
	 * Validates a gender.
	 *
	 * @param gender The gender to validate
	 * @return true if gender is valid
	 * @throws ValidationException If gender is invalid
	 */
	public boolean validateGender(char gender) throws ValidationException {
		if (gender != 'M' && gender != 'F') {
			throw new ValidationException(UserConstants.INVALID_GENDER_GENDER_MUST_BE_M_OR_F);
		}
		return true;
	}

	/**
	 * Validates a profile image URL.
	 *
	 * @param profileImage The profile image URL to validate
	 * @return true if profile image URL is valid
	 * @throws ValidationException If profile image URL is empty or invalid
	 */
	public boolean validateProfileImage(String profileImage) throws ValidationException {
		if (profileImage == null) {
			throw new ValidationException(UserConstants.PROFILE_IMAGE_URL_CANNOT_BE_EMPTY);
		}
		profileImage = profileImage.trim();
		if (profileImage.isEmpty()) {
			throw new ValidationException(UserConstants.PROFILE_IMAGE_URL_CANNOT_BE_EMPTY);
		}
		try {
			new URL(profileImage);
		} catch (MalformedURLException e) {
			throw new ValidationException(UserConstants.INVALID_PROFILE_IMAGE_URL);
		}
		return true;
	}

	/**
	 * Validates a name.
	 *
	 * @param name The name to validate
	 * @return true if the name is valid
	 * @throws ValidationException If the name is empty or has an invalid format
	 */
	public boolean validateName(String name) throws ValidationException {
		if (name == null) {
			throw new ValidationException(UserConstants.NAME_CANNOT_BE_EMPTY);
		}
		name = name.trim();
		boolean isMatch = Pattern.compile("^[A-Z' -]+$", Pattern.CASE_INSENSITIVE).matcher(name).find();
		if (name.isEmpty()) {
			throw new ValidationException(UserConstants.NAME_CANNOT_BE_EMPTY);
		}
		if (!isMatch) {
			throw new ValidationException(UserConstants.INVALID_NAME_FORMAT);
		}
		return true;
	}

	/**
	 * Validates a date of birth.
	 *
	 * @param dob The date of birth to validate
	 * @return true if the date of birth is valid
	 * @throws ValidationException If the date of birth is invalid
	 */
	public boolean validateDateOfBirth(LocalDate dob) throws ValidationException {

		if (dob == null) {
			throw new ValidationException(UserConstants.DATE_OF_BIRTH_CANNOT_BE_EMPTY);
		}
		LocalDate currentDate = LocalDate.now();
		LocalDate maximumValidDob = currentDate.minus(Period.ofYears(100));
		LocalDate minimumValidDob = currentDate.minus(Period.ofYears(10));
		if (dob.isAfter(minimumValidDob)) {
			throw new ValidationException(UserConstants.INVALID_DATE_OF_BIRTH_MUST_BE_AT_LEAST_10_YEARS_OLD);
		}
		if (dob.isBefore(maximumValidDob)) {
			throw new ValidationException(UserConstants.INVALID_DATE_OF_BIRTH_CANNOT_EXCEED_100_YEARS);
		}
		return true;
	}
}
