package com.aakash.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BerlinClockTest {

    @Test
    public void testGetTopRow() {
        int timeUnit = 10;
        BerlinClock bHours = new BerlinClock.Hours(timeUnit);
        BerlinClock bMinutes = new BerlinClock.Minutes(timeUnit);

        String hoursTopRowStr = bHours.getTopRow();
        String minTopRowStr = bMinutes.getTopRow();
        assertNotNull(hoursTopRowStr);
        assertNotNull(minTopRowStr);

        assertFalse("10 hours or 10 minutes formats don't seem right",
                !hoursTopRowStr.startsWith("RR") && !minTopRowStr.startsWith("YY"));

        //checking completed quarter-hours
        bMinutes.setCurrentHourUnit(15);
        minTopRowStr = bMinutes.getTopRow();
        assertFalse("15 format doesn't seem right, should be YYR but reported as " + minTopRowStr,
                !minTopRowStr.startsWith("YYR"));


        //checking for correct hour top row
        bHours.setCurrentHourUnit(21);
        hoursTopRowStr = bHours.getTopRow();
        assertFalse("21 hours format doesn't seem right, should be RRRR (containing 4 Rs) but reported as " + hoursTopRowStr,
                (hoursTopRowStr.chars().filter(ch -> ch == 'R').count()) != 4);


        //checking more completed quarter-hours
        bMinutes.setCurrentHourUnit(45);
        minTopRowStr = bMinutes.getTopRow();
        assertFalse("45 minutes format doens't seem right, should be YYRYYRYYR.. (containing 3 Rs) but reported as " + minTopRowStr,
                ((minTopRowStr.chars().filter(ch -> ch == 'R').count()) != 3));

    }

    @Test
    public void testGetBottomRow() {
        int timeUnit = 14;
        IBerlinClock bHours = new BerlinClock.Hours(timeUnit);
        IBerlinClock bMinutes = new BerlinClock.Minutes(timeUnit);

        String hoursBottomRowStr = bHours.getBottomRow();
        String minBottomRowStr = bMinutes.getBottomRow();
        assertNotNull(hoursBottomRowStr);
        assertNotNull(minBottomRowStr);
        assertFalse("14 hours or 14 minutes formats don't seem right",
                (!hoursBottomRowStr.equals("RRRR") && !minBottomRowStr.equals("RRRR")));

        //checking for correct hours and minutes bottom row
        bHours.setCurrentHourUnit(21);
        bMinutes.setCurrentHourUnit(28);
        hoursBottomRowStr = bHours.getTopRow();
        minBottomRowStr = bMinutes.getBottomRow();
        assertFalse("21 hours format doens't seem right, should be RRRR (containing 4 Rs) but reported as " + hoursBottomRowStr,
                (hoursBottomRowStr.chars().filter(ch -> ch == 'R').count()) != 4);
        assertFalse("28 minutes format doens't seem right, should be RRR. (containing 3 Ys) but reported as " + minBottomRowStr,
                ((minBottomRowStr.chars().filter(ch -> ch == 'R').count()) != 3));

    }

    @Test
    public void testTopAndBottomRows() {
        int hours = 17;
        int minutes = 54;
        BerlinClock bHours = new BerlinClock.Hours(hours);
        BerlinClock bMinutes = new BerlinClock.Minutes(minutes);

        String hoursTopRowStr = bHours.getTopRow();
        String minTopRowStr = bMinutes.getTopRow();
        assertNotNull(hoursTopRowStr);
        assertNotNull(minTopRowStr);

        String hoursBottomRowStr = bHours.getBottomRow();
        String minBottomRowStr = bMinutes.getBottomRow();
        assertNotNull(hoursBottomRowStr);
        assertNotNull(minBottomRowStr);

        assertTrue(String.format("%s\n%s", hoursTopRowStr, hoursBottomRowStr).equals("RRR.\nRR.."));
        assertTrue(String.format("%s\n%s", minTopRowStr, minBottomRowStr).equals("YYRYYRYYRY.\nRRRR"));
    }
}
