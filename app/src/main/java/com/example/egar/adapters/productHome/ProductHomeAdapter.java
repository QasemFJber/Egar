package com.example.egar.adapters.productHome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egar.Models.Product;
import com.example.egar.adapters.product.ProductViewHolder;
import com.example.egar.databinding.ItemProductsBinding;
import com.example.egar.interfaces.ItemCallback;
import com.example.egar.interfaces.ItemCallbackProduct;


import java.util.ArrayList;
import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeViewHolder> {


    private List<Product> products  ;
    private ItemCallbackProduct callback;

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
        if (products == null) {
            return 0;
        } else {
            return products.size();
        }
    }


    public void setCallback(ItemCallbackProduct callback) {
        this.callback = callback;
    }
}
