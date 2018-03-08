package com.utils;

import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {


    public static List<String> getDays(int year, int month){

       // String[] days = {};
        List<String> days = new ArrayList<>();

        YearMonth yearMonthObject = YearMonth.of(year,month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        for(int i = 0; i<daysInMonth; i++){
            days.add(String.valueOf(i+1));
        }

        return days;
    }

    public static String[] getMonths(){

        String[] months = (new DateFormatSymbols().getMonths());
        return months;
    }


    public static String getJanMonth(){
        return "January";
    }


    public static String[] getYears(){
        final String[] years = {"2018","2019","2020"};
        return years;
    }
}
