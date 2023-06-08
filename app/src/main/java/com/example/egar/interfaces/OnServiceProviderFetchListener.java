package com.example.egar.interfaces;

import com.example.egar.Models.Provider;

import java.util.List;

public interface OnServiceProviderFetchListener {
    void onFetchSuccess(List<Provider> serviceProviders);
    void onFetchFailure(String errorMessage);
}

