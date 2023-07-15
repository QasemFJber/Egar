package com.example.egar.interfaces;

import com.example.egar.Models.ProductFavorite;

import java.util.List;

public interface OnProductFavoritesFetchListener {
    void onProductFavoritesFetched(List<ProductFavorite> productFavorites);

    void onProductFavoritesFetchFailure(String message);
}
