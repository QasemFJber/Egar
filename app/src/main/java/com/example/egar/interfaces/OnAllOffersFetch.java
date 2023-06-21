package com.example.egar.interfaces;

import com.example.egar.Models.Product;

import java.util.List;

public interface OnAllOffersFetch {
    void onSuccess(List<Product> productsOffers);

    void onFailure(String message);

}
