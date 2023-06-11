package com.example.egar.adapters.productShowProvider;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowProviderBinding;
import com.squareup.picasso.Picasso;

public class ProductShowProviderViewHolder extends RecyclerView.ViewHolder {
    ItemProductShowProviderBinding binding;
    public ProductShowProviderViewHolder(ItemProductShowProviderBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }
    public void savaData(Product product){
        binding.textNameproductShowProvider.setText(product.getName());
        binding.textPriceShowProvider.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(binding.imageProductShowProvider);


    }
}
