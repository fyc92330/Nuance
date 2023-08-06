package org.chun.somnus.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

public class DateTimeUtil {

	private static final Locale DEFAULT_LOCALE = Locale.TRADITIONAL_CHINESE;

	private static final class Pattern {
		//DateTime
		static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
		static final DateTimeFormatter DATE_TIME_ISO_8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", DEFAULT_LOCALE);
		static final DateTimeFormatter DATE_TIME_UNDER_LINE = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss", DEFAULT_LOCALE);

		//Date
		static final DateTimeFormatter DATE_STASH = DateTimeFormatter.ofPattern("yyyy-MM-dd", DEFAULT_LOCALE);
		static final DateTimeFormatter DATE_SLASH = DateTimeFormatter.ofPattern("yyyy/MM/dd", DEFAULT_LOCALE);
		static final DateTimeFormatter DATE_UNDER_LINE = DateTimeFormatter.ofPattern("yyyy/MM/dd", DEFAULT_LOCALE);
		static final DateTimeFormatter DATE_BLANK = DateTimeFormatter.ofPattern("yyyy/MM/dd", DEFAULT_LOCALE);
	}


	public enum DateTime implements Compute<LocalDateTime>, Storage<Timestamp> {

		DATE_TIME(Pattern.DATE_TIME::format, x -> LocalDateTime.parse(x, Pattern.DATE_TIME)),
		ISO_8601(Pattern.DATE_TIME_ISO_8601::format, x -> LocalDateTime.parse(x, Pattern.DATE_TIME_ISO_8601)),
		UNDER_LINE(Pattern.DATE_TIME_UNDER_LINE::format, x -> LocalDateTime.parse(x, Pattern.DATE_TIME_UNDER_LINE));

		private final Function<LocalDateTime, String> formatter;
		private final Function<String, LocalDateTime> parser;

		DateTime(Function<LocalDateTime, String> formatter, Function<String, LocalDateTime> parser) {
			this.formatter = formatter;
			this.parser = parser;
		}

		@Override
		public String format(LocalDateTime localDateTime) {
			return this.formatter.apply(localDateTime);
		}

		@Override
		public LocalDateTime parse(String dateTime) {
			return this.parser.apply(dateTime);
		}

		@Override
		public String timestampFormat(Timestamp timestamp) {
			return this.format(timestamp.toLocalDateTime());
		}

		@Override
		public Timestamp parse2TimeStamp(String dateTime) {
			return Timestamp.valueOf(this.parse(dateTime));
		}
	}

	public enum Date implements Compute<LocalDate>, Storage<Timestamp> {

		STASH(Pattern.DATE_STASH::format, x -> LocalDate.parse(x, Pattern.DATE_STASH)),
		SLASH(Pattern.DATE_SLASH::format, x -> LocalDate.parse(x, Pattern.DATE_SLASH)),
		UNDER_LINE(Pattern.DATE_UNDER_LINE::format, x -> LocalDate.parse(x, Pattern.DATE_UNDER_LINE)),
		BLANK(Pattern.DATE_BLANK::format, x -> LocalDate.parse(x, Pattern.DATE_BLANK));

		private final Function<LocalDate, String> formatter;
		private final Function<String, LocalDate> parser;

		Date(Function<LocalDate, String> formatter, Function<String, LocalDate> parser) {
			this.formatter = formatter;
			this.parser = parser;
		}

		@Override
		public String format(LocalDate localDate) {
			return this.formatter.apply(localDate);
		}

		@Override
		public LocalDate parse(String date) {
			return this.parser.apply(date);
		}

		@Override
		public String timestampFormat(Timestamp timestamp) {
			return this.format(timestamp.toLocalDateTime().toLocalDate());
		}

		@Override
		public Timestamp parse2TimeStamp(String date) {
			return Timestamp.valueOf(this.parse(date).atStartOfDay());
		}
	}


	interface Compute<T> {

		String format(T localDateTime);

		T parse(String dateTime);
	}

	interface Storage<Timestamp> {

		String timestampFormat(Timestamp timestamp);

		Timestamp parse2TimeStamp(String dateTime);
	}
}
