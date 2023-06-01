package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar.R;
import com.example.egar.databinding.ActivityShowCategoriesBinding;

public class ShowCategoriesActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityShowCategoriesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String category_name= intent.getStringExtra("category_name");
        binding.tvTitleCategory.setText(category_name);
        setOnclick();
    }

    private void setOnclick(){
        binding.btnBackCategory.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_category){
           onBackPressed();
           finish();
        }
    }
}