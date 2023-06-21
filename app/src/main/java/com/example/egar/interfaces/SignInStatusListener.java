package com.example.egar.interfaces;

public interface SignInStatusListener {
    void onUserSignedInAsRegularUser(String id);
    void onUserSignedInAsAdminUser(String id);
    void onUserNotSignedIn(String uid);
}

