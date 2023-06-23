package com.example.egar.interfaces;


import com.example.egar.Models.Offer;

import java.util.List;

public interface OnOfferFetchListener {
    void onAddOfferSuccess(String offerId);
    void onAddOfferFailure(String message);
    void onUpdateOfferSuccess();
    void onUpdateOfferFailure(String message);
    void onDeleteOfferSuccess();
    void onDeleteOfferFailure(String message);
    void onGetOffersByServiceProviderIdSuccess(List<Offer> offers);
    void onGetOffersByServiceProviderIdFailure(String message);

    void onListFetchSuccess(List<Offer> offersList);

    void onListFetchFailure(String message);

}

