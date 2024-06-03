package com.bookingdetailsservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

	public static long daysBetween(Date fromDate, Date toDate) {
		LocalDate start = convertToLocalDateViaInstant(fromDate);
		LocalDate end = convertToLocalDateViaInstant(toDate);
		return ChronoUnit.DAYS.between(start, end);
	}

	private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
