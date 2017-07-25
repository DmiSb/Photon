package ru.dmisb.photon.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {
    private static SimpleDateFormat resFormat;

    @SuppressLint("SimpleDateFormat")
    public static void initFormats() {
        resFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.ENGLISH);
    }

    public static SimpleDateFormat getResFormat() {
        return resFormat;
    }

    public static String resFormatDate(Date date) {
        return resFormat.format(date);
    }
}
