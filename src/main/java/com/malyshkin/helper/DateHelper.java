package com.malyshkin.helper;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DateHelper {

    public static Map<Integer, String> getMonthsNames(Set<Integer> monthsNumbers) {
        Map<Integer, String> monthNames = new HashMap<>();
        for (int monthNumber : monthsNumbers) {
            monthNames.put(monthNumber, getMonth(monthNumber));
        }

        return monthNames;
    }

    private static String getMonth(int month) {
        List<String> months = Arrays.asList("January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December");

        return months.get(month);
    }

    public static int getCurrentMonth(){
        Calendar currentDate = Calendar.getInstance();
        return currentDate.get(Calendar.MONTH);
    }

}
