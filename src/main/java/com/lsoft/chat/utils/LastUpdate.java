package com.lsoft.chat.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LastUpdate {
    public static long getCurrentLong(){
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return now.toInstant().toEpochMilli();

    }

    public static String getCurrentDate(){
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
