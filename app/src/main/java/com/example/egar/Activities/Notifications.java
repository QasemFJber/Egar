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



    private void  setOnClick(){
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

    }
}