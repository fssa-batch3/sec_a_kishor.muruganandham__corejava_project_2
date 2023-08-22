package com.fssa.library_management.validation;

import com.fssa.library_management.exceptions.ValidationException;
import com.fssa.library_management.model.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidator {
	private User user;

	public UserValidator(User user) {
		this.user = user;
	}

	public UserValidator() {

	}

	public void validateAll() throws ValidationException {
		if (validateMobileNo(user.getMobileNo()) && validatePassword(user.getPassword()) && validateGender(user.getGender()) && validateEmail(user.getEmail()) && validateProfileImage(user.getProfileImage()) && validateName(user.getName())) {
			validateDateOfBirth(user.getDob());
		}
	}

	public boolean validateEmail(String email) throws ValidationException {
		final String emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		boolean result = Pattern.compile(emailRegEx).matcher(email).matches();
		if (email.isEmpty()) {
			throw new ValidationException("Email cannot be empty");
		}
		if (!result) {
			throw new ValidationException("Invalid Email Format");
		}
		return true;
	}

	public boolean validateMobileNo(long mobileNo) throws ValidationException {
		if (mobileNo == 0) {
			throw new ValidationException("Mobile number cannot be empty");
		}
		return true;
	}

	public boolean validatePassword(String password) throws ValidationException {
		if (password == null || password.isEmpty()) {
			throw new ValidationException("Password cannot be empty");
		} else if (password.length() < 8) {
			throw new ValidationException("Password is less than the expected length of 8 characters");
		}
		return true;
	}

	public boolean validateGender(char gender) throws ValidationException {
		if (gender != 'M' && gender != 'F') {
			throw new ValidationException("Invalid gender. Gender must be 'M' or 'F'.");
		}
		return true;
	}

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
			throw new ValidationException("Date of birth cannot be in future");
		}
		return true;
	}
}


