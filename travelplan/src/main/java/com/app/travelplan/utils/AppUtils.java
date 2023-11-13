package com.app.travelplan.utils;

import java.time.LocalTime;

public class AppUtils {
    public static String getBaseUrl() {
        return "http://localhost:8081";
    }

    public static LocalTime[] getTimeInit() {
        return new LocalTime[]{LocalTime.of(0, 0),
                LocalTime.of(6, 0),
                LocalTime.of(8, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 30),
                LocalTime.of(17, 30),
                LocalTime.of(18, 30),
                LocalTime.of(20, 0),
                LocalTime.of(23,59)
        };
    }

    public static long[] getTimeMinutes() {
        return new long[]{360l, 120l, 240l, 90l, 240l, 60l, 90l, 240l};
    }

    // 0h    6h    8h     12h
}
