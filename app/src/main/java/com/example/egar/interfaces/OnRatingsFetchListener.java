package com.example.egar.interfaces;

import com.example.egar.Models.Rating;

import java.util.List;

public interface OnRatingsFetchListener {
    void onRatingsFetched(List<Rating> ratings);
    void onFailure(String errorMessage);

    void onRatingsFetchFailure(String message);

}
