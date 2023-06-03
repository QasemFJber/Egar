package com.example.egar.interfaces;


import com.example.egar.Models.Product;

import java.util.ArrayList;

public interface OnProductFetchListener {
    void onFetchLListSuccess(ArrayList<Product> list);
    void onFetchSuccess(Product product);
    void onFetchFailure(String message);

}
