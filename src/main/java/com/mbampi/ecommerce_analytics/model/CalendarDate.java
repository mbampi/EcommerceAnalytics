package com.mbampi.ecommerce_analytics.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CalendarDate {
    private int day;
    private int month;
    private int year;

    public CalendarDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.year = localDate.getYear();
        this.month = localDate.getMonthValue();
        this.day = localDate.getDayOfMonth();
    }

    public CalendarDate(String date) {
        String[] dateElements = date.split("/");
        this.year = Integer.parseInt(dateElements[2]);
        this.month = Integer.parseInt(dateElements[1]);
        this.day = Integer.parseInt(dateElements[0]);
    }

    @Override
    public String toString() {
        return this.day + "/" + this.month + "/" + this.year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        CalendarDate date = (CalendarDate) o;
        return (this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear());
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }
}
