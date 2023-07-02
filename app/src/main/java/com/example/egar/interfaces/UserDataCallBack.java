package com.example.egar.interfaces;

public interface UserDataCallBack {
    void onSuccess(String name, String address, String number, String providerImage, String email);

    void onFailure(String message);
}
