package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.databinding.ActivityServiceProviderStoreDetailsBinding;

public class Service_Provider_Store_Details extends AppCompatActivity {
    ActivityServiceProviderStoreDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderStoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}