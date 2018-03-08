package com.model;

import com.service.Rates;

public class Trip {

    private String monthName;

    private int month = 1; //By default set to January

    private int year = 2018; //By Default set to 2018

    private int day = 1;

    private int duration;

    private Rates.HIKE tripName = Rates.HIKE.GARDINER;

    private Long cost;



    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }


    public Rates.HIKE getTripName() {
        return tripName;
    }

    public void setTripName(Rates.HIKE tripName) {
        this.tripName = tripName;
    }
}
