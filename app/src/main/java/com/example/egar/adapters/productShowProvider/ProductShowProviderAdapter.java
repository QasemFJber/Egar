package com.example.egar.adapters.productShowProvider;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowProviderBinding;
import com.example.egar.interfaces.ItemCallbackProduct;

import java.util.List;

public class ProductShowProviderAdapter extends RecyclerView.Adapter<ProductShowProviderViewHolder> {

    List<Product> products;
    ItemCallbackProduct callbackProduct;

    public ProductShowProviderAdapter(List<Product> products) {
        this.products = products;
    }

    public void setCallbackProduct(ItemCallbackProduct callbackProduct) {
        this.callbackProduct = callbackProduct;
    }

    @NonNull
    @Override
    public ProductShowProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowProviderBinding binding=ItemProductShowProviderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductShowProviderViewHolder(binding,callbackProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductShowProviderViewHolder holder, int position) {
        holder.savaData(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
