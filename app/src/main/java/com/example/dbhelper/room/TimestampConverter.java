package com.example.dbhelper.room;

import android.arch.persistence.room.TypeConverter;
import android.provider.SyncStateContract;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 11/4/18.
 */

public class TimestampConverter {
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String dateToTimestamp(Date value) {

        return value == null ? null : df.format(value);
    }

}
