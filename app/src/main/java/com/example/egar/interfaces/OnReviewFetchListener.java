package com.example.egar.interfaces;

import com.example.egar.Models.Review;

public interface OnReviewFetchListener {
    void onFetchSuccess(Review review);
    void onFetchFailure(String errorMessage);
}

