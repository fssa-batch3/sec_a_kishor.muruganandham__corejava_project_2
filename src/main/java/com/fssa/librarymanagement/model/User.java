package com.fssa.librarymanagement.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a user in the library management system.
 *
 * @author KishorMuruganandham
 */
public class User {

	private String name;
	private String email;
	private long mobileNo;
	private String password;
	private char gender;
	private LocalDate dob;
	private Timestamp createdDate;
	private boolean isActive;
	private boolean isAdmin;
	private String profileImage = "https://ui-avatars.com/api/?name=" + name + "&rounded=true&uppercase=false" +
			"&background=random";
	private int userId;

	/**
	 * Constructs a new User object with default values.
	 */
	public User() {
		// Default constructor
	}

	/**
	 * Check if the user is an administrator.
	 *
	 * @return true if the user is an administrator, false otherwise.
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * Set the user's administrator status.
	 *
	 * @param admin true if the user should be an administrator, false otherwise.
	 */
	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	/**
	 * Get the user's profile image URL.
	 *
	 * @return The URL of the user's profile image.
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * Set the user's profile image URL.
	 *
	 * @param profileImage The URL of the user's profile image.
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	/**
	 * Set the user's account activation status.
	 *
	 * @param active true if the user's account should be active, false otherwise.
	 */
	public void setActive(boolean active) {
		isActive = active;
	}

	/**
	 * Get the user's name.
	 *
	 * @return The user's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the user's name.
	 *
	 * @param name The user's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the user's email address.
	 *
	 * @return The user's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the user's email address.
	 *
	 * @param email The user's email address.
	 */
	public void setEmail(String email) {
		this.email = email;
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
	}

	/**
	 * Get the user's mobile number.
	 *
	 * @return The user's mobile number.
	 */
	public long getMobileNo() {
		return mobileNo;
	}

	/**
	 * Set the user's mobile number.
	 *
	 * @param mobileNo The user's mobile number.
	 */
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Get the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the user's password.
	 *
	 * @param password The user's password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the user's gender.
	 *
	 * @return The user's gender.
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Set the user's gender.
	 *
	 * @param gender The user's gender.
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Get the user's date of birth.
	 *
	 * @return The user's date of birth.
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * Set the user's date of birth.
	 *
	 * @param dob The user's date of birth.
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	/**
	 * Get the timestamp when the user's account was created.
	 *
	 * @return The timestamp of the user's account creation.
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the timestamp when the user's account was created.
	 *
	 * @param createdDate The timestamp of the user's account creation.
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the user's unique ID.
	 *
	 * @return The user's unique ID.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set the user's unique ID.
	 *
	 * @param userId The user's unique ID.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Returns a string representation of the User object.
	 *
	 * @return A string representation of the User object.
	 */
	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", mobileNo=" + mobileNo +
				", password='" + password + '\'' +
				", gender=" + gender +
				", dob=" + dob +
				", createdDate=" + createdDate +
				", isActive=" + isActive +
				", isAdmin=" + isAdmin +
				", profileImage='" + profileImage + '\'' +
				'}';
	}
}
