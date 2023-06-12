package com.example.egar.adapters.productShowProvider;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowProviderBinding;
import com.example.egar.interfaces.ItemCallbackProduct;
import com.squareup.picasso.Picasso;

public class ProductShowProviderViewHolder extends RecyclerView.ViewHolder {
    ItemProductShowProviderBinding binding;
    ItemCallbackProduct callbackProduct;
    public ProductShowProviderViewHolder(ItemProductShowProviderBinding binding,ItemCallbackProduct callbackProduct) {
        super(binding.getRoot());
        this.binding=binding;
        this.callbackProduct=callbackProduct;
    }
    public void savaData(Product product){
        binding.textNameproductShowProvider.setText(product.getName());
        binding.textPriceShowProvider.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getImageUrl()).into(binding.imageProductShowProvider);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackProduct.onItemClick(product);
            }
        });


    }
}
