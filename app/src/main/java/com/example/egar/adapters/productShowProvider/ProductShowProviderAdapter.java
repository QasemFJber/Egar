package com.example.egar.adapters.productShowProvider;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowProviderBinding;

import java.util.List;

public class ProductShowProviderAdapter extends RecyclerView.Adapter<ProductShowProviderViewHolder> {

    List<Product> products;

    public ProductShowProviderAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductShowProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowProviderBinding binding=ItemProductShowProviderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductShowProviderViewHolder(binding);
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
