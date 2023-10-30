package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.enums.BorrowingDuration;
import com.fssa.librarymanagement.exceptions.ValidationException;

/**
 * A utility class for mapping between integers and BorrowingDuration enum values.
 *
 * @author KishorMuruganandham
 */
public class BorrowingDurationEnumMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private BorrowingDurationEnumMapper() {
		// Do nothing (empty constructor)
	}

	/**
	 * Maps an integer value to a BorrowingDuration enum value.
	 *
	 * @param days The number of days to map to a BorrowingDuration enum
	 * @return The mapped BorrowingDuration enum value
	 * @throws ValidationException If no matching BorrowingDuration enum is found for the given value
	 */
	public static BorrowingDuration mapToBorrowingDuration(int days) throws ValidationException {
		for (BorrowingDuration duration : BorrowingDuration.values()) {
			if (duration.getValue() == days) {
				return duration;
			}
		}
		throw new ValidationException("No Borrowing Duration enum found for the given value");
	}
}
