package com.dailysnapshotbackend.enums;

public enum WeatherCondition {

    CLEAR,
    CLOUDY,
    FOG,
    DRIZZLE,
    RAIN,
    SNOW,
    STORM,
    UNKNOWN;

    public static WeatherCondition fromCode(int code) {
        return switch (code) {
            case 0 -> CLEAR;
            case 1, 2, 3 -> CLOUDY;
            case 45, 48 -> FOG;
            case 51, 53, 55, 56, 57 -> DRIZZLE;
            case 61, 63, 65, 66, 67, 80, 81, 82 -> RAIN;
            case 71, 73, 75, 77, 85, 86 -> SNOW;
            case 95, 96, 99 -> STORM;
            default -> UNKNOWN;
        };
    }
}

