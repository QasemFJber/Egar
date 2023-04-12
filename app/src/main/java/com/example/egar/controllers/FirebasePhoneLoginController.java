package com.example.egar.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class FirebasePhoneLoginController {
    private final FirebaseAuth auth;

    public FirebasePhoneLoginController() {
        auth = FirebaseAuth.getInstance();
    }

    public String registerNewUser(String phoneNumber) throws FirebaseAuthException {
//        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                .setPhoneNumber(phoneNumber);
//
//        UserRecord userRecord = auth.createUser(request);

        return "";
    }

    public String loginUser(String phoneNumber) throws FirebaseAuthException {
//        String session = auth.createSessionCookie(phoneNumber, null);

        // Store the session cookie in the client-side session storage for subsequent requests.
        // You can also set it as a response cookie to allow other client-side apps to access it.
        return "";
    }

    public void logoutUser(String session) throws FirebaseAuthException {
//        auth.revokeRefreshTokens(session);
    }
}
