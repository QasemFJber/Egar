package com.example.egar.controllers;

import android.app.Application;

public class AppController extends Application {

    private static AppController instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppController getInstance() {
        return instance;
    }
}
