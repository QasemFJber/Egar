package com.example.egar.interfaces;

import com.example.egar.Models.Order;

public interface OnOrderByIdFetchListener {
    void onGetOrderSuccess(Order order);

    void onGetOrderFailure(String order_not_found);

}
