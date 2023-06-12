package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.databinding.ActivityShowServiceProductDetailsBinding;
import com.squareup.picasso.Picasso;

public class ShowService_Product_Details extends AppCompatActivity {

    ActivityShowServiceProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowServiceProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDetails();

    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Product_Details.this, R.color.black));
    }
/*
    @Override
    public void onBackPressed() {
        // Create an exit dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        dialog.show();
    }
*/

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }

    private Product product(){
        Product product = (Product) getIntent().getSerializableExtra("product");
        return product;
    }

    private void getDetails(){
        binding.tvTextProductName.setText(product().getName());
        binding.tvTextDescription.setText(product().getDescription());
        binding.tvPrice.setText(String.valueOf(product().getPrice()));
        binding.tvTextproviderName.setText(product().getProvider().getName());
        Picasso.get().load(product().getImageUrl()).into(binding.productImg);
        Picasso.get().load(product().getProvider().getImage()).into(binding.imageProviderImg);

    }
}