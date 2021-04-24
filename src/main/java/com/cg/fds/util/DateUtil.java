package com.cg.fds.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	private final String pattern = "dd/MM/yyyy hh:mm:ss";
	private final String pattern2 = "yyyy-MM-dd";

	public String toText(LocalDateTime datetime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String text = formatter.format(datetime);
		return text;
	}

	public String toText(LocalDateTime datetime) {
		String text = toText(datetime, pattern);
		return text;
	}

	public LocalDate toLocalDate(String datetimeText, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate datetime = LocalDate.parse(datetimeText, formatter);
		return datetime;
	}

	public LocalDate toLocalDate(String dateText) {
		LocalDate date = toLocalDate(dateText, pattern2);
		return date;
	}

}
