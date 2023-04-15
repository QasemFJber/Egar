package com.example.egar.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.egar.controllers.AppController;

public class AppSharedPreferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private AppSharedPreferences() {
        sharedPreferences = AppController.getInstance().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private static AppSharedPreferences instance;

    public static AppSharedPreferences getInstance() {
        if (instance == null) {
            instance = new AppSharedPreferences();
        }
        return instance;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }


    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
