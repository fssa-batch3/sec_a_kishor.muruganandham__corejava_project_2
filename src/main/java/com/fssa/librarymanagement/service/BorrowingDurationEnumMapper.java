package com.fssa.librarymanagement.service;

import com.fssa.librarymanagement.enums.BorrowingDuration;
import com.fssa.librarymanagement.exceptions.ValidationException;

public class BorrowingDurationEnumMapper {

	private BorrowingDurationEnumMapper() {

	}

	public static BorrowingDuration mapToBorrowingDuration(int days) throws ValidationException {
		for (BorrowingDuration duration : BorrowingDuration.values()) {
			if (duration.getValue() == days) {
				return duration;
			}
		}
		throw new ValidationException("No Borrowing Duration enum found for the given value");
	}
}
