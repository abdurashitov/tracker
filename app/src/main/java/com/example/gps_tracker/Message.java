package com.example.gps_tracker;

import java.util.Date;

public class Message {

    private double latitude;
    private double longitude;
    private long time;

    public Message() {
    }

    public Message(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = new Date().getTime();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}