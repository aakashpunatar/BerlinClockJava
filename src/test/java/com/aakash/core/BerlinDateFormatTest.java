package com.aakash.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BerlinDateFormatTest {
    BerlinDateFormat setTheoryClock;

    @Before
    public void setup() {
        setTheoryClock = Mockito.spy(new BerlinDateFormat());
    }

    @Test
    public void shouldProcessOrdinaryTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("(%s:%s:%ss) Berlin Uhr Theory current time:\n%s\n\n", now.getHour(), now.getMinute(),
                now.getSecond(), setTheoryClock.format());
        System.out.println("Performing simple checks with the following times: 9:54:50  and 16:18:33");
        assertEquals("Unexpected result with the time 16:18:33",
                setTheoryClock.format(16, 18, 33), "  .\nRRR.\nR...\nYYR........\nRRR.");
    }

    @Test(expected = NumberFormatException.class)
    public void shouldFailInvalidRanges() {
        setTheoryClock.format(45, 12, 34);
        setTheoryClock.format(45, 12, 34);
        setTheoryClock.format(12, 400, 34);
        setTheoryClock.format(12, 12, 999);
    }

    @Test
    public void testSeconds() {
        String output = setTheoryClock.format(1, 1, 1);
        assertTrue(output.startsWith("  ."));
        output = setTheoryClock.format(1, 1, 4);
        assertTrue(output.startsWith("  Y"));
    }
}
