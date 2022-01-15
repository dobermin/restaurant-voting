package ru.mail.dobermin.voting.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "HH:mm dd-MM-yyyy";
    public static final String DATE_PATTERN = "dd-MM-yyyy";

    public static boolean canVote(int hour, int minute) {

        Calendar calendar = new GregorianCalendar(Locale.getDefault());

        return canVote(hour, minute, calendar.getTime());
    }

    public static boolean canVote(String hourTime, Date date) {
        try {
            String separate = String.valueOf(hourTime.charAt(2));
            List<String> list = List.of(hourTime.split(separate));
            return canVote(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)), date);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean canVote(int hour, int minute, Date date) {

        Calendar calendar = new GregorianCalendar(Locale.getDefault());
        calendar.setTime(date);

        int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
        if (hourNow > hour) return false;
        int minuteNow = calendar.get(Calendar.MINUTE);
        return hourNow != hour || minuteNow <= minute;
    }

    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
