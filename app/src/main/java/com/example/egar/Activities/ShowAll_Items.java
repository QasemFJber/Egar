package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.egar.R;
import com.example.egar.databinding.ActivityShowAllItemsBinding;

public class ShowAll_Items extends AppCompatActivity implements View.OnClickListener {
    ActivityShowAllItemsBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        binding.tvTitle.setText(intent.getStringExtra("category"));
    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowAll_Items.this, R.color.black));
    }


    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void setOnClick(){
        binding.btnBack.setOnClickListener(this::onClick);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
}