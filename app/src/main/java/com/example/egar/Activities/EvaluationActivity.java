package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.egar.databinding.ActivityEvaluationBinding;

public class EvaluationActivity extends AppCompatActivity {
    ActivityEvaluationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvaluationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}