package com.example.egar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.egar.Fragments.BottomNavigationFragments.Categories;
import com.example.egar.Fragments.BottomNavigationFragments.Favorite;
import com.example.egar.Fragments.BottomNavigationFragments.Home;
import com.example.egar.Fragments.BottomNavigationFragments.Profile;
import com.example.egar.R;
import com.example.egar.adapters.ViewPagerAdapter;
import com.example.egar.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Categories categories = new Categories();
    Favorite favorite = new Favorite();
    Home home = new Home();
    Profile profile = new Profile();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.home:
                        item.setIcon(R.drawable.homeyi);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,home).commit();
                        break;
                    case R.id.profile:
                        item.setIcon(R.drawable.useryi);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,profile).commit();
                        break;
                    case R.id.favorite:
                        item.setIcon(R.drawable.baseline_favoriteyi_24);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,favorite).commit();
                        break;
                    case R.id.category:
                        item.setIcon(R.drawable.baseline_categoryyl_24);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,categories).commit();
                        break;
                }

                return false;
            }
        });


    }
    private void operationsSccren() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        operationsSccren();
        setOnClick();
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
        binding.imageNotification.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_notification:
                Intent intent = new Intent(getApplicationContext(),Notifications.class);
                startActivity(intent);
                break;

        }
    }
}