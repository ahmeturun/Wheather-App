package android.test.com.theweatherapp;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ahmet on 9/11/2016.
 */
public class convertStringToDate {
    public static Date convertDate(String dateToConvert) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.ENGLISH);
        Date result=null;
        {
            try {
                result = df.parse(dateToConvert);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String convertDay(String dateToConvert) {
        Date df = null;
        try {
            df = new SimpleDateFormat("yyyy-MM-dd").parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfWeek = new SimpleDateFormat("EEEE").format(df);
        return dayOfWeek;
    }



}
