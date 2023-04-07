package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.R;
import com.example.egar.databinding.ActivityFollowOrederBinding;

public class FollowOrder extends AppCompatActivity {
    ActivityFollowOrederBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowOrederBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}