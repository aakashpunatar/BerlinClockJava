package com.aakash.core;

import java.time.LocalDateTime;

public class BerlinDateFormat {

    public String format() {
        LocalDateTime now = LocalDateTime.now();
        return format(now.getHour(), now.getMinute(), now.getSecond());
    }

    public String format(int hour, int minute, int second) throws NumberFormatException {
        StringBuilder row = new StringBuilder();
        row.append(String.format("  %c\n", (second % 2 == 0 ? IBerlinClock.YELLOW_LIGHT : IBerlinClock.UNLIT)));

        IBerlinClock hours = new BerlinClock.Hours(hour);
        row.append(String.format("%s\n%s\n", hours.getTopRow(), hours.getBottomRow()));
        IBerlinClock minutes = new BerlinClock.Minutes(minute);
        row.append(String.format("%s\n%s", minutes.getTopRow(), minutes.getBottomRow()));

        return row.toString();
    }
}
