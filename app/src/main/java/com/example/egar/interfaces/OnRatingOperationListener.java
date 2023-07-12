package com.example.egar.interfaces;

public interface OnRatingOperationListener {
    void onRatingOperationSuccess(String ratingId);

    void onRatingOperationFailure(String message);
}
