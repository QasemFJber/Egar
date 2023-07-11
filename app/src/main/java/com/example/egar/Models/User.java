package com.example.egar.Models;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String profileImageUri;
    private String governorate ;

    public User() {
    }



    public User(String name, String email, String selectedGovernorate, String pass, String phone) {
        this.name = name;
        this.email =email;
        this.governorate=selectedGovernorate;
        this.password=pass;
        this.phoneNumber=phone;
    }

    public User(String name, String email, String phoneNumber, String profileImageUri) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImageUri = profileImageUri;
    }

    public User(String id, String name, String email, String phoneNumber, String profileImageUri, String governorate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImageUri = profileImageUri;
        this.phoneNumber = phoneNumber;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProfileImageUri(String profileImageUri) {
        this.profileImageUri = profileImageUri;
    }
}

