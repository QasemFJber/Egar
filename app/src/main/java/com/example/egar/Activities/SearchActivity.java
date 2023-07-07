package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.egar.Dialog.MyDialogFragment;
import com.example.egar.R;
import com.example.egar.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;

    MyDialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=MyDialogFragment.newInstance("");
                fragment.show(getSupportFragmentManager(),null);
            }
        });
    }
}