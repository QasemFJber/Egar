package com.example.egar.interfaces;

import com.example.egar.Models.Category;

import java.util.List;

public interface CategoryCallback {
    void onSuccess(List<Category> categories);
    void onFailure(String error);
}

