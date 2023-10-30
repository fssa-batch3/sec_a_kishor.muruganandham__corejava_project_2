package com.fssa.librarymanagement.enums;

/**
 * An enum representing different borrowing durations for books.
 */
public enum BorrowingDuration {
	/**
	 * Represents a borrowing duration of 5 days.
	 */
	FIVE_DAYS(5),
	/**
	 * Represents a borrowing duration of 15 days.
	 */
	FIFTEEN_DAYS(15),
	/**
	 * Represents a borrowing duration of 20 days.
	 */
	TWENTY_DAYS(20),
	/**
	 * Represents a borrowing duration of 30 days.
	 */
	THIRTY_DAYS(30);

	private final int value;

	/**
	 * Constructs a BorrowingDuration enum with the specified numeric value.
	 *
	 * @param value The numeric value associated with the borrowing duration.
	 */
	BorrowingDuration(int value) {
		this.value = value;
	}

	/**
	 * Get the numeric value associated with the borrowing duration.
	 *
	 * @return The numeric value of the borrowing duration.
	 */
	public int getValue() {
		return value;
	}
}
