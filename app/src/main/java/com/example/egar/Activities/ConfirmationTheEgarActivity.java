package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.databinding.ActivityConfirmationTheEgarBinding;

public class ConfirmationTheEgarActivity extends AppCompatActivity {
    ActivityConfirmationTheEgarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmationTheEgarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}