package com.qihuan.find.kit;

import com.blankj.utilcode.util.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DateKit
 * Created by Qi on 2017/7/12.
 */

public class DateKit {

    private static final String F0 = "yyyyMMdd";

    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(F0, Locale.getDefault());
        return TimeUtils.getNowString(simpleDateFormat);
    }

    public static String getNowDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return TimeUtils.getNowString(simpleDateFormat);
    }

    public static String timeSub(String date, int dayAddNum, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date newDate = new Date(simpleDateFormat.parse(date).getTime() - dayAddNum * 24 * 60 * 60 * 1000);
            return simpleDateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeAdd(String date, int dayAddNum, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date newDate = new Date(simpleDateFormat.parse(date).getTime() + dayAddNum * 24 * 60 * 60 * 1000);
            return simpleDateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeSub(String date) {
        return timeSub(date, 1, F0);
    }

    public static String timeAdd(String date) {
        return timeAdd(date, 1, F0);
    }

    public static String parseDate(String date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat format = new SimpleDateFormat(F0, Locale.getDefault());
        if (date.equals(TimeUtils.getNowString(format))) {
            return "今天";
        }

        Date formatDate = null;
        try {
            formatDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (formatDate == null) {
            return "";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("- yyyy/MM/dd EEEE -", Locale.getDefault());
        return simpleDateFormat.format(formatDate);
    }
}
