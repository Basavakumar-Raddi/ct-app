package com.ct.demo.stake.util;

import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtil {

    public static final String UTC_TIME_ZONE = "UTC";
    private static final String format = "YYYY-MM-dd";
    private static final String formatCTDemo = "YYYY-MMM-dd";

    public static DateTimeFormatter formatter = DateTimeFormat.forPattern(format)
            .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE)));

    public static DateTimeFormatter formatterCTDemo = DateTimeFormat.forPattern(formatCTDemo)
            .withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE)));


    private DateUtil() {
    }

    public static Long getMillisFromString(String dateStr) {
        DateTime parsedDate = formatter.parseDateTime(dateStr);
        return parsedDate.getMillis();
    }

    public static String getStringFromMillis(Long millis) {
        DateTime parsedMillis = new DateTime(millis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE)));
        return parsedMillis.toString(formatter);
    }

    public static Long getMillisFromStringCTDemo(String dateStr) {
        DateTime parsedDate = formatterCTDemo.parseDateTime(dateStr);
        return parsedDate.getMillis();
    }

    public static String getStringFromMillisCTDemo(Long millis) {
        DateTime parsedMillis = new DateTime(millis, DateTimeZone.forTimeZone(TimeZone.getTimeZone(UTC_TIME_ZONE)));
        return parsedMillis.toString(formatterCTDemo);
    }

}
