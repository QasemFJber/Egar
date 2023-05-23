package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.databinding.ActivityDetermineRentStandardsBinding;

public class DetermineRentStandardsActivity extends AppCompatActivity {
    ActivityDetermineRentStandardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetermineRentStandardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}