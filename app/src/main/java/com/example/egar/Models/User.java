package com.example.egar.Models;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String profileImageUri;

    public User(String id, String name, String email, String password, String phoneNumber, String profileImageUri) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImageUri = profileImageUri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }

    // يمكنك إضافة مزيد من الدوال والعمليات اللازمة حسب احتياجاتك
}

