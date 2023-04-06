package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.egar.R;
import com.example.egar.databinding.ActivityNotificationsBinding;

public class Notifications extends AppCompatActivity {
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_notifications);
        operationsSccren();
    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(Notifications.this,R.color.white));
    }
}