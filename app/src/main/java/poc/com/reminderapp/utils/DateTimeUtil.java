package poc.com.reminderapp.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private static final SimpleDateFormat HOUR_24_TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private static final SimpleDateFormat DATE_AND_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
    private static final SimpleDateFormat DATE_AND_TIME_WITH_SECONDS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
    private static final SimpleDateFormat READABLE_DAY_MONTH_FORMAT = new SimpleDateFormat("d MMMM", Locale.getDefault());
    private static final SimpleDateFormat READABLE_DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());
    private static final SimpleDateFormat READABLE_TIME_24_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final SimpleDateFormat READABLE_TIME_FORMAT = new SimpleDateFormat("h:mm a", Locale.getDefault());
    private static final SimpleDateFormat READABLE_DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-YYYY hh:mm a", Locale.getDefault());
    private static final SimpleDateFormat DEVICE_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);


    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return DATE_FORMAT.format(calendar.getTime());
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return TIME_FORMAT.format(calendar.getTime());
    }

    public static String getDateInReadableFormat(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.YEAR, year);
        return DATE_FORMAT.format(date.getTime());
    }

    public static String getTimeInReadableFormat(int hourOfDay, int minute) {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.HOUR_OF_DAY,hourOfDay);
            date.set(Calendar.MINUTE,minute);
            return TIME_FORMAT.format(date.getTime());
    }

    public static String toDateTime(String date, String time) {
        try {
            Date d = DATE_FORMAT.parse(date);
            Date t = TIME_FORMAT.parse(time);
            Calendar aDate = Calendar.getInstance();
            aDate.setTime(d);
            Calendar aTime = Calendar.getInstance();
            aTime.setTime(t);
            Calendar dateTime = Calendar.getInstance();
            dateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
            dateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
            dateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
            dateTime.set(Calendar.HOUR, aTime.get(Calendar.HOUR));
            dateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));
            return dateTime.getTime().toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toReadableDateTime(String dateTime){
        try {
            Date d = DEVICE_DATE_FORMAT.parse(dateTime);
            return READABLE_DATE_TIME_FORMAT.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toStringReadableDate(Calendar calendar) {
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static String toStringReadableTime(Calendar calendar, Context context) {
        if (DateFormat.is24HourFormat(context)) {
            return READABLE_TIME_24_FORMAT.format(calendar.getTime());
        } else {
            return READABLE_TIME_FORMAT.format(calendar.getTime());
        }
    }

    public static Long toLongDateAndTime(Calendar calendar) {
        return Long.parseLong(DATE_AND_TIME_FORMAT.format(calendar.getTime()));
    }

}
