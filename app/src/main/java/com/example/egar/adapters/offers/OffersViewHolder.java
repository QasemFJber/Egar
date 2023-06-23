package com.example.egar.adapters.offers;

import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.databinding.ItemOffersBinding;
import com.squareup.picasso.Picasso;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    public OffersViewHolder(ItemOffersBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

/*    public void savaData(Offer offer){
        binding.textNameProduct.setText(offer.getProductName());
        binding.textPrice.setText(String.valueOf(offer.getPrice()));
        binding.textDiscountPercentage.setText("10-");
        binding.textPriceDiscount.setText(String.valueOf(offer.getPrice()));
        Picasso.get().load("").into(binding.imageProduct);
        Picasso.get().load("").into(binding.imageProvider);



    }*/
}
