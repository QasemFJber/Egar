package com.example.egar.adapters.productHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.adapters.product.ProductViewHolder;
import com.example.egar.databinding.ItemProductsBinding;
import com.example.egar.interfaces.ItemCallback;


import java.util.ArrayList;
import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeViewHolder> {

     List<Product> products =new ArrayList<>();
    private ItemCallback callback;

    public ProductHomeAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductsBinding binding=ItemProductsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductHomeViewHolder(binding,callback);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeViewHolder holder, int position) {
        holder.savaData(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setCallback(ItemCallback callback) {
        this.callback = callback;
    }
}
