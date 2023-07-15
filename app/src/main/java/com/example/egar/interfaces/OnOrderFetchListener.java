package com.example.egar.interfaces;

import com.example.egar.Models.Order;

import java.util.List;

public interface OnOrderFetchListener {
    void onAddOrderSuccess(String orderId, String id);
    void onAddOrderFailure(String message);

    void onDeleteOrderSuccess();
    void onDeleteOrderFailure(String message);
    void onGetOrdersByServiceProviderIdSuccess(List<Order> orders);
    void onGetOrdersByServiceProviderIdFailure(String message);
}

