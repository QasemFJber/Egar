package com.example.egar.adapters.offers;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Offer;
import com.example.egar.databinding.ItemOffersBinding;
import com.example.egar.interfaces.ItemCallbackOffer;
import com.squareup.picasso.Picasso;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    ItemCallbackOffer callbackOffer;
    public OffersViewHolder(ItemOffersBinding binding, ItemCallbackOffer callbackOffer) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackOffer=callbackOffer;
    }

    public void savaData(Offer offer){
        binding.textNameProduct.setText(offer.getProduct().getName());
        binding.textPrice.setText(String.valueOf(offer.getPrice()));
        binding.textDiscountPercentage.setText("10-");
        binding.textPriceDiscount.setText(String.valueOf(offer.getPrice()));
        Picasso.get().load(offer.getProduct().getImageUrl()).into(binding.imageProduct);
        Picasso.get().load(offer.getProduct().getProvider().getImage()).into(binding.imageProvider);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackOffer.onItemClick(offer);

            }
        });

    }
}
