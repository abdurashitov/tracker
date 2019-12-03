package com.example.gps_tracker;

import java.util.Date;

public class Message {

    private String text;
    private String name;
    private long time;

    public Message() {
    }

    public Message(String name, String text) {
        this.text = text;
        this.name = name;
        this.time = new Date().getTime();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}