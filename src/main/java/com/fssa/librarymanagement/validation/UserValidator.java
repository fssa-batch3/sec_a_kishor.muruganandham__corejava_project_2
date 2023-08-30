package com.fssa.librarymanagement.validation;

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
	 * @return true if all attributes are valid, false otherwise
	 * @throws ValidationException If any validation fails
	 */
	public boolean validateAll() throws ValidationException {
		if (validateMobileNo(user.getMobileNo()) && validatePassword(user.getPassword())
				&& validateGender(user.getGender()) && validateEmail(user.getEmail())
				&& validateProfileImage(user.getProfileImage()) && validateName(user.getName())) {
			validateDateOfBirth(user.getDob());
			return true;
		}
		return false;
	}

	/**
	 * Validates an email address.
	 *
	 * @param email The email address to validate
	 * @return true if email is valid
	 * @throws ValidationException If email is invalid or empty
	 */
	public boolean validateEmail(String email) throws ValidationException {
		final String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		if (email.isEmpty()) {
			throw new ValidationException("Email cannot be empty");
		}
		boolean result = Pattern.compile(emailRegEx).matcher(email).matches();
		if (!result) {
			throw new ValidationException("Invalid Email Format");
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
		if (mobileNo == 0) {
			throw new ValidationException("Mobile number cannot be empty");
		}
		return true;
	}

	/**
	 * Validates a password.
	 *
	 * @param password The password to validate
	 * @return true if password is valid
	 * @throws ValidationException If password is empty or too short
	 */
	public boolean validatePassword(String password) throws ValidationException {
		if (password == null || password.isEmpty()) {
			throw new ValidationException("Password cannot be empty");
		} else if (password.length() < 8) {
			throw new ValidationException("Password is less than the expected length of 8 characters");
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
			throw new ValidationException("Invalid gender. Gender must be 'M' or 'F'.");
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
		if (profileImage == null || profileImage.isEmpty()) {
			throw new ValidationException("Profile Image cannot be empty");
		}
		try {
			new URL(profileImage);
		} catch (MalformedURLException e) {
			throw new ValidationException("Invalid profile image URL");
		}
		return true;
	}

	/**
	 * Validates a name.
	 *
	 * @param name The name to validate
	 * @return true if name is valid
	 * @throws ValidationException If name is empty or has an invalid format
	 */
	public boolean validateName(String name) throws ValidationException {
		boolean isMatch = Pattern.compile("^[A-Z' -]+$", Pattern.CASE_INSENSITIVE).matcher(name).find();
		if (name.isEmpty()) {
			throw new ValidationException("Name cannot be Empty");
		}
		if (!isMatch) {
			throw new ValidationException("Invalid Name Format");
		}
		return true;
	}

	/**
	 * Validates a date of birth.
	 *
	 * @param dob The date of birth to validate
	 * @return true if date of birth is valid
	 * @throws ValidationException If date of birth is invalid
	 */
	public boolean validateDateOfBirth(LocalDate dob) throws ValidationException {
		if (dob == null) {
			throw new ValidationException("Date of birth cannot be empty");
		}

		LocalDate currentDate = LocalDate.now();
		LocalDate minimumValidDob = currentDate.minus(Period.ofYears(10));
		if (dob.isAfter(minimumValidDob)) {
			throw new ValidationException("Invalid date of birth. Must be at least 10 years old.");
		}
		if (dob.isAfter(currentDate)) {
			throw new ValidationException("Date of birth cannot be in the future");
		}
		return true;
	}
}
