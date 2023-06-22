package com.example.egar.interfaces;

import com.example.egar.Models.Order;

import java.util.ArrayList;
import java.util.List;

public interface OnOrderFetchListener {
    void onAddOrderSuccess(String orderId);
    void onAddOrderFailure(String message);
    void onUpdateOrderSuccess();
    void onUpdateOrderFailure(String message);
    void onDeleteOrderSuccess();
    void onDeleteOrderFailure(String message);
    void onGetOrdersByServiceProviderIdSuccess(List<Order> orders);
    void onGetOrdersByServiceProviderIdFailure(String message);
}

