package com.sudha.precipitationstudyinsubsaharanafrica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataRecord {
    public Date day;
    public double lat;
    public double lon;
    public double prec;

    public DataRecord(Date day, double lat, double lon, double prec) {
        this.day = day;
        this.lat = lat;
        this.lon = lon;
        this.prec = prec;
    }

    public DataRecord() {
    }

    public Date getDay() {
        return day;
    }
    public String getDayString(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(day);
    }

    public void setDay(Date day) {
        this.day = day;
    }
    public void setDay(String dayString, String format) {
        try {
            this.day = new SimpleDateFormat(format).parse(dayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getPrec() {
        return prec;
    }

    public void setPrec(double prec) {
        this.prec = prec;
    }
}
