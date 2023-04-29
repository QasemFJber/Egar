package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.egar.R;
import com.example.egar.databinding.ActivityNotificationsBinding;

public class Notifications extends AppCompatActivity implements View.OnClickListener {
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_notifications);

    }
    private void operationsSccren() {
        setOnClick();
        getWindow().setStatusBarColor(ContextCompat.getColor(Notifications.this,R.color.white));
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

    private void  setOnClick(){
        binding.backToMain.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_to_main:
                onBackPressed();
                break;
        }
    }
}