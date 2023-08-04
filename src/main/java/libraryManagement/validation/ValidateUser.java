package libraryManagement.validation;

import libraryManagement.exceptions.ValidationException;
import libraryManagement.model.User;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidateUser {
    private User user;

    public ValidateUser(User user) {
        this.user = user;
    }

    public ValidateUser() {

    }

    public static boolean validateEmail(String email) throws ValidationException {
        final String emailRegEx = "^(.+)@(\\S+)$";
        if (email == null || email.isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }
        return Pattern.compile(emailRegEx).matcher(email).matches();
    }

    public boolean validateAll() throws ValidationException {
        return validateMobileNo(user.getMobileNo()) &&
                validatePassword(user.getPassword()) &&
                validateGender(user.getGender()) &&
                validateEmail(user.getEmail()) &&
                validateProfileImage(user.getProfileImage()) &&
                validateName(user.getName()) &&
                validateDateOfBirth(user.getDob());
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
        if (name.isEmpty() || !isMatch) {
            throw new ValidationException("Invalid Name");
        }
        return true;
    }

    public boolean validateDateOfBirth(LocalDate dob) throws ValidationException {
        if (dob == null) {
            throw new ValidationException("Date of birth cannot be empty");
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate minimumValidDob = currentDate.minus(Period.ofYears(10));
        if (dob.isAfter(currentDate) || dob.isAfter(minimumValidDob)) {
            throw new ValidationException("Invalid date of birth. Must be at least 10 years old.");
        }
        return true;
    }
}


