package com.deutsche.tradeservice.dateutils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {

	public static boolean isDateLaterThanToday(LocalDate maturityDate) {
		if (maturityDate.isAfter(LocalDate.now()) || maturityDate.isEqual(LocalDate.now())) {
			return true;
		}
		return false;
	}

	public static LocalDate getLocalDateFromString(String maturityDateAsString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // assuming this format, because format
																					// really depends on the region you
																					// are catering to or to the user

		return LocalDate.parse(maturityDateAsString, formatter);

	}

	public static boolean hasMaturityDateCrossToday(LocalDate maturityDate) {
		if (maturityDate.isBefore(LocalDate.now())) {
			return true;
		}
		return false;
	}

}