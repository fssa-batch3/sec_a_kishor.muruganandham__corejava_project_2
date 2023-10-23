/**
 * 
 */
package com.fssa.librarymanagement.enums;

/**
 * 
 */
public enum BorrowingDuration {
	FIVE_DAYS(5), FIFTEEN_DAYS(15), TWENTY_DAYS(20), THIRTY_DAYS(30);

	private int value;

	BorrowingDuration(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
