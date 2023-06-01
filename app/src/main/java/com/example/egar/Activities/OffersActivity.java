package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.egar.R;
import com.example.egar.databinding.ActivityOffersBinding;

public class OffersActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityOffersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityOffersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setOnclick();
    }


    private void setOnclick(){
        binding.btnBackOffers.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_category){
            onBackPressed();
            finish();
        }
    }
}