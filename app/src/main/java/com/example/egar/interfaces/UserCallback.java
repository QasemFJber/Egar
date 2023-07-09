package com.example.egar.interfaces;

import com.example.egar.Models.User;

public interface UserCallback {
    void onSuccess(User user);

    void onFailure(String s);
}
