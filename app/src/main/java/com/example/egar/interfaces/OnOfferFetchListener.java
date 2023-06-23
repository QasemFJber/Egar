package com.example.egar.interfaces;


import com.example.egar.Models.Offer;

import java.util.List;

public interface OnOfferFetchListener {


    void onListFetchSuccess(List<Offer> offersList);

    void onListFetchFailure(String message);

}

