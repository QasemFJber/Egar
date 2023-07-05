package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.egar.Models.Category;
import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;
import com.example.egar.SharedPreferences.AppSharedPreferences;
import com.example.egar.controllers.CategoryController;
import com.example.egar.databinding.ActivitySplashBinding;
import com.example.egar.interfaces.ProcessCallback;
import com.example.egar.interfaces.SignInStatusListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;
    private List<Category> categoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }



    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                AppSharedPreferences appSharedPreferences = AppSharedPreferences.getInstance();
                boolean isFirstRun = appSharedPreferences.getSharedPreferences().getBoolean("isFirstRun", false);

                if (isFirstRun) {
                    FirebaseAuthController.getInstance().isSignedIn(new SignInStatusListener() {
                        @Override
                        public void onUserSignedInAsRegularUser(String id) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onUserSignedInAsAdminUser(String id) {
                            Snackbar.make(binding.getRoot(),"Please Create User Account",Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onUserNotSignedIn(String uid) {
                            Snackbar.make(binding.getRoot(),"Please Create  Account",Snackbar.LENGTH_LONG).show();
                            Intent intent =new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                    });

                } else if (!isFirstRun) {
                    Intent intent = new Intent(Splash.this, Pager_GetStarted.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Splash.this, "Pager Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, 3000);
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
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}