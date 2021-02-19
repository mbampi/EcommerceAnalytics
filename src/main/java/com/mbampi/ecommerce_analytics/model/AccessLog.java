package com.mbampi.ecommerce_analytics.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.StringJoiner;

public class AccessLog {

    private String urlPath;
    private String callerCountry;
    private Timestamp timestamp;
    private ActionType action;

    public AccessLog(String log) {
        String[] logElements = log.split("\\|");
        this.urlPath = logElements[0];
        this.callerCountry = logElements[1];
        this.timestamp = new Timestamp(Long.parseLong(logElements[2]));
        this.action = ActionType.valueOf(logElements[3]);
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public String getCallerCountry() {
        return this.callerCountry;
    }

    public Date getDate() {
        return new Date(this.timestamp.getTime());
    }

    public CalendarDate getCalendarDate() {
        return new CalendarDate(new Date(this.timestamp.getTime()));
    }

    @Override
    public String toString() {
        StringJoiner accessLog = new StringJoiner("|");

        accessLog.add(this.urlPath).add(this.callerCountry).add(Long.toString(this.timestamp.getTime()))
                .add(this.action.toString());
        return accessLog.toString();
    }

    public String toReadableString() {
        String access;
        access = "URL_PATH: " + this.urlPath;
        access += "\nCALLER_COUNTRY: " + this.callerCountry;
        access += "\nTIMESTAMP: " + this.timestamp.toString();
        access += "\nACTION: " + this.action.toString();
        return access;
    }

}
