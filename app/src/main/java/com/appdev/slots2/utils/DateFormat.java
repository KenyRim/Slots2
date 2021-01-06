package com.appdev.slots2.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {


    public static String getFormattedDate(String dateToFormat,boolean forSore) {

        String pattern = "";

        if (forSore){
            pattern = "yyMMddHHmmss";
        }else {
            pattern = "dd.MM.yy в HH:mm";
        }

        try {
            SimpleDateFormat fromFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.UK);
            SimpleDateFormat toFormat = new SimpleDateFormat(pattern, Locale.UK);
            Date date = fromFormat.parse(dateToFormat);
            if (date != null) {
                dateToFormat = toFormat.format(date);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.e("",e.getMessage());
        }

        return dateToFormat;
    }

    public static String getDate(String dateToFormat) {

        try {
            SimpleDateFormat fromFormat = new SimpleDateFormat("yyMMddHHmmss", Locale.UK);
            SimpleDateFormat toFormat = new SimpleDateFormat("dd.MM.yy в HH:mm", Locale.UK);
            Date date = fromFormat.parse(dateToFormat);
            if (date != null) {
                dateToFormat = toFormat.format(date);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.e("",e.getMessage());
        }

        return dateToFormat;
    }
}
