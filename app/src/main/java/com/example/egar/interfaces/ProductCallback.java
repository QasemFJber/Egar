package com.example.egar.interfaces;

import com.example.egar.Models.Product;

import java.util.List;

public interface ProductCallback {
    void onSuccess(List<Product> productList);
    void onFailure(String message);

    void onProductFetchSuccess(Product product);

}
