package com.example.egar.adapters.product;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.databinding.ItemProductShowBinding;
import com.example.egar.interfaces.ItemCallbackProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

     List<Product> products ;
    ItemCallbackProduct callbackProduct;


    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    public void setCallbackProduct(ItemCallbackProduct callbackProduct) {
        this.callbackProduct = callbackProduct;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowBinding binding=ItemProductShowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductViewHolder(binding,callbackProduct);
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
