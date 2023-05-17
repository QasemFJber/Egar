package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.databinding.ActivityServiceProviderBinding;

public class ServiceProviderActivity extends AppCompatActivity {
    ActivityServiceProviderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}