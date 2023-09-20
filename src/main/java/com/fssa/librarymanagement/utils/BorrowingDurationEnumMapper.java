package com.fssa.librarymanagement.utils;

import com.fssa.librarymanagement.enums.BorrowingDuration;

public class BorrowingDurationEnumMapper {
	public static BorrowingDuration mapToBorrowingDuration(int days) {
		for (BorrowingDuration duration : BorrowingDuration.values()) {
			if (duration.getValue() == days) {
				return duration;
			}
		}
		throw new IllegalArgumentException("No BorrowingDuration enum found for the given value: " + days);
	}
}
