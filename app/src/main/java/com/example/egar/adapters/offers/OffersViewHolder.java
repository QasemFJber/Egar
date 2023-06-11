package com.example.egar.adapters.offers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.databinding.ItemOffersBinding;
import com.squareup.picasso.Picasso;

public class OffersViewHolder extends RecyclerView.ViewHolder {
    ItemOffersBinding binding;
    public OffersViewHolder(ItemOffersBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void savaData(){
        binding.textNameProduct.setText("");
        binding.textPrice.setText("");
        binding.textDiscountPercentage.setText("");
        binding.textPriceDiscount.setText("");
        Picasso.get().load("").into(binding.imageProduct);
        Picasso.get().load("").into(binding.imageProvider);



    }
}
