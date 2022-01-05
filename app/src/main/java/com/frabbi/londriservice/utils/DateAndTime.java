package com.frabbi.londriservice.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        return localDateTime.format(dateTimeFormatter);
    }
}
