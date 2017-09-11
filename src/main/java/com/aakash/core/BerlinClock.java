package com.aakash.core;

import java.util.stream.IntStream;

public abstract class BerlinClock implements IBerlinClock {
    private final char bottomRowLitColor;
    protected int currentHourUnit, minAllowed, maxAllowed;

    public BerlinClock(char bottomRowLitColor, int currentTimeUnit, int minAllowed, int maxAllowed) {
        this.bottomRowLitColor = bottomRowLitColor;
        this.currentHourUnit = currentTimeUnit;
        this.minAllowed = minAllowed;
        this.maxAllowed = maxAllowed;
    }

    public abstract String getTopRow();

    public String getBottomRow() {
        checkRange();
        StringBuilder row = new StringBuilder(String.format("%c%c%c%c", UNLIT, UNLIT, UNLIT, UNLIT));
        if ((currentHourUnit % 5) != 0)
            IntStream.range(0, currentHourUnit % 5).forEach(idx -> row.setCharAt(idx, bottomRowLitColor));
        return row.toString();
    }

    protected void checkRange() {
        if (currentHourUnit < minAllowed || currentHourUnit > maxAllowed)
            throw new NumberFormatException("Time unit out of range: " + currentHourUnit);
    }

    public void setCurrentHourUnit(int currentHourUnit) {
        this.currentHourUnit = currentHourUnit;
    }

    static final class Hours extends BerlinClock {
        public Hours(int hour) {
            super(RED_LIGHT, hour, 0, 23);
        }

        public String getTopRow() {
            checkRange();
            StringBuilder row = new StringBuilder(String.format("%c%c%c%c", UNLIT, UNLIT, UNLIT, UNLIT));
            for (int i = 5, index = 0; i <= currentHourUnit; i += 5, index++)
                row.setCharAt(index, RED_LIGHT);
            return row.toString();
        }
    }

    static final class Minutes extends BerlinClock {
        public Minutes(int minute) {
            super(RED_LIGHT, minute, 0, 59);
        }

        public String getTopRow() {
            checkRange();
            StringBuilder row = new StringBuilder(String.format("%c%c%c%c%c%c%c%c%c%c%c",
                    UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT, UNLIT));
            for (int i = 5, index = 0; i <= currentHourUnit; i += 5, index++)
                row.setCharAt(index, (index > 0 && i % 15 == 0) ? RED_LIGHT : YELLOW_LIGHT);
            return row.toString();
        }
    }
}
