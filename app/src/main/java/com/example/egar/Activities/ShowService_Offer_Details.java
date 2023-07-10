package com.example.egar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.egar.Models.Offer;
import com.example.egar.Models.Product;
import com.example.egar.R;
import com.example.egar.databinding.ActivityShowServiceOfferDetailsBinding;
import com.example.egar.databinding.ActivityShowServiceProductDetailsBinding;
import com.squareup.picasso.Picasso;

public class ShowService_Offer_Details extends AppCompatActivity implements View.OnClickListener {

    ActivityShowServiceOfferDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowServiceOfferDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getOffer();
        setOnClick();

    }
    private void operationsSccren() {
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowService_Offer_Details.this, R.color.black));
    }

    @Override
    protected void onStart() {
        super.onStart();
       // operationsSccren();
    }


    private Offer offer(){
        Offer offer = (Offer) getIntent().getSerializableExtra("offer");
        return offer;
    }

    private  void setOnClick() {
        binding.buttonToSet.setOnClickListener(this::onClick);
    }

    private void getOffer(){
        binding.tvTextProductName.setText(offer().getProduct().getName());
        binding.tvTextDescription.setText(offer().getProduct().getDescription());
        binding.tvPrice.setText(String.valueOf(offer().getProduct().getPrice()));
        binding.tvTextproviderName.setText(offer().getProduct().getProvider().getName());
        Picasso.get().load(offer().getProduct().getImageUrl()).into(binding.productImg);
        Picasso.get().load(offer().getProduct().getProvider().getImage()).into(binding.imageProviderImg);

        if (offer().getProduct().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_24);

        }else if(!offer().getProduct().isFavorite()){
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_toSet){
            Intent intent =new Intent(getApplicationContext(), DetermineRentStandardsActivity.class);
            intent.putExtra("id_product",offer().getProduct().getId());
            startActivity(intent);
            finish();
        }
    }
}