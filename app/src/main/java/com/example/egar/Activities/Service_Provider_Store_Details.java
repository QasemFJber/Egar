package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.egar.Models.Provider;
import com.example.egar.R;
import com.example.egar.databinding.ActivityServiceProviderStoreDetailsBinding;
import com.squareup.picasso.Picasso;

public class Service_Provider_Store_Details extends AppCompatActivity {
    ActivityServiceProviderStoreDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceProviderStoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDetails();
    }
/*    @Override
    public void onBackPressed() {
       // onBackPressed();
        // Create an exit dialog
*//*        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setIcon(R.drawable.baseline_exit_to_app_24);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and continue with the application
                dialog.dismiss();
            }
        });
        // Create the dialog and show it
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Do nothing
            }
        });
        dialog.show();*//*
    }*/

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getDetails(){
        Provider provider = (Provider) getIntent().getSerializableExtra("provider");

        binding.tvNameAdmin.setText(provider.getName());
        binding.tvLoction.setText(provider.getAddress());
        Picasso.get().load(provider.getImage()).into(binding.imgProvider);
    }
}