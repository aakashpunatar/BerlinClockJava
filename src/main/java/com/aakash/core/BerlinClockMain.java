package com.aakash.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BerlinClockMain {
    public static void main(String[] args) {
        BerlinDateFormat setTheoryClock = new BerlinDateFormat();
        try {
            System.out.println(parse(args, setTheoryClock));
        } catch (ParseException e) {
            System.err.println("Incorrect format, should be: HH:mm:ss");
        }
    }

    static String parse(String[] args, BerlinDateFormat setTheoryClock) throws ParseException {
        String output = "";
        if (args.length > 0) {
            if (args[0].equals("now"))
                output = setTheoryClock.format();
            else {
                DateFormat format = new SimpleDateFormat("HH:mm:ss");
                format.setLenient(false);
                format.parse(args[0]);
                String[] time = args[0].split(":");
                int hours = Integer.parseInt(time[0]);
                int minutes = Integer.parseInt(time[1]);
                int seconds = Integer.parseInt(time[2]);
                output = setTheoryClock.format(hours, minutes, seconds);
            }
        } else
            output = "Usage:\n now [displays the local time as the Berlin Uhr Theory clock format] \n"
                    + " HH:mm:ss [displays the specified time as the Berlin Uhr clock format, HH:0-23, mm:standard "
                    + "minutes, ss:standard seconds]";
        return output;
    }
}
