package com.example.egar.Models;

import java.util.Date;

public class Review {
    private String reviewId;
    private String userId;
    private String serviceId;
    private String reviewText;
    private int rating;
    private Date date;

    public Review() {
        // Required empty constructor for Firebase
    }

    public Review(String reviewId, String userId, String serviceId, String reviewText, int rating, Date date) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.serviceId = serviceId;
        this.reviewText = reviewText;
        this.rating = rating;
        this.date = date;
    }

    // Getters and Setters
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

