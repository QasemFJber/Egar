package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.egar.R;
import com.example.egar.databinding.ActivityShowAllItemsBinding;

public class ShowAll_Items extends AppCompatActivity {
    ActivityShowAllItemsBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        operationsSccren();
        binding.tvTitle.setText(intent.getStringExtra("category"));
    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowAll_Items.this, R.color.black));
    }
}