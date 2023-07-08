package com.example.egar.Models;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private int imageUrl;

    private int icon;

    public Category(String name, int imageUrl, int icon) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.icon = icon;
    }

    public Category(String name, int imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}

