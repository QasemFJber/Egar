package com.example.egar.Models;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private Drawable imageUrl;

    public Category(String name, Drawable imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category(String name, int coworking) {

    }

    public String getName() {
        return name;
    }

    public Drawable getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(Drawable imageUrl) {
        this.imageUrl = imageUrl;
    }
}

