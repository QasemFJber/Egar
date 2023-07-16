package com.example.egar.Models;

public class Notification {
    private String title;
    private String body;
    private String date;
    private  String time;

    public Notification(String title, String body, String date, String currentTime) {
        this.title = title;
        this.body = body;
        this.date=date;
        time=currentTime;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Notification() {
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

