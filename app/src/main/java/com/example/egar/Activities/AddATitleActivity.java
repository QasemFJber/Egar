package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.R;
import com.example.egar.databinding.ActivityAddAtitleBinding;

public class AddATitleActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityAddAtitleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAtitleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddATitleActivity.this, "Add a title", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        binding.btnBack.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back){
            onBackPressed();
        }

    }
}