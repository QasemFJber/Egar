package com.example.egar.adapters.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

     List<Product> products ;


    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowBinding binding=ItemProductShowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.savaData(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }




}
