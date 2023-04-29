package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.egar.R;
import com.example.egar.FirebaseManger.FirebaseAuthController;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


    }


    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), FirebaseAuthController.getInstance().isSignedIn() ? MainActivity.class : Login.class);
                startActivity(intent);
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
    private void controlSplashActivity() {
        //3000ms - 3s
//        boolean isFirstRun = AppSharedPreferences.getInstance().getSharedPreferences().getBoolean("isFirstRun", true);
//        if (isFirstRun) {
////           قم بعرض ViewPager
////            AppSharedPreferences.getInstance().getEditor().putBoolean("isFirstRun", false).apply();
//            Intent intent = new Intent(getApplicationContext(),Pager_GetStarted.class);
//            startActivity(intent);
//        } else {
        }


}