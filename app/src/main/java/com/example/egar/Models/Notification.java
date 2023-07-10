package com.example.egar.Models;

public class Notification {
    private String title;
    private String body;

    public Notification(String title, String body) {
        this.title = title;
        this.body = body;
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

