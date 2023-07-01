package com.example.egar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.egar.Models.Offer;
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
       // getOffer();

    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Product_Details.this, R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }

    private Product product(){
        Product product = (Product) getIntent().getSerializableExtra("product");
        return product;
    }

/*    private Offer offer(){
        Offer offer = (Offer) getIntent().getSerializableExtra("offer");
        return offer;
    }*/

/*    private void getOffer(){
        binding.tvTextProductName.setText(offer().getProduct().getName());
        binding.tvTextDescription.setText(offer().getProduct().getDescription());
        binding.tvPrice.setText(String.valueOf(offer().getProduct().getPrice()));
        binding.tvTextproviderName.setText(offer().getProduct().getProvider().getName());
        Picasso.get().load(offer().getProduct().getImageUrl()).into(binding.productImg);
        Picasso.get().load(offer().getProduct().getProvider().getImage()).into(binding.imageProviderImg);

    }*/

    private void getDetails(){
        binding.tvTextProductName.setText(product().getName());
        binding.tvTextDescription.setText(product().getDescription());
        binding.tvPrice.setText(String.valueOf(product().getPrice()));
        binding.tvTextproviderName.setText(product().getProvider().getName());
        Picasso.get().load(product().getImageUrl()).into(binding.productImg);
        Picasso.get().load(product().getProvider().getImage()).into(binding.imageProviderImg);
        if (product().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);

        }else if(!product().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);
        }
    }
}