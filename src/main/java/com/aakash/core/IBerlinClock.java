package com.aakash.core;

public interface IBerlinClock {
    char UNLIT = '.';
    char RED_LIGHT = 'R';
    char YELLOW_LIGHT = 'Y';

    String getTopRow() throws NumberFormatException;

    String getBottomRow() throws NumberFormatException;

    void setCurrentHourUnit(int currentHourUnit);
}
