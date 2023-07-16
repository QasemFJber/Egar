package com.example.egar.adapters.fP;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Product;
import com.example.egar.Models.ProductFavorite;
import com.example.egar.databinding.ItemProductShowBinding;
import com.example.egar.interfaces.ItemCallbackProduct;

import java.util.List;

public class ProductfAdapter extends RecyclerView.Adapter<ProductfViewHolder> {

    private List<ProductFavorite> productFavorites ;
    private ItemCallbackProduct callbackProduct;


    public ProductfAdapter(List<ProductFavorite> productFavorites) {
        this.productFavorites = productFavorites;
    }

    public void setCallbackProduct(ItemCallbackProduct callbackProduct) {
        this.callbackProduct = callbackProduct;
    }

    @NonNull
    @Override
    public ProductfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowBinding binding=ItemProductShowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductfViewHolder(binding,callbackProduct);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductfViewHolder holder, int position) {
        holder.savaData(productFavorites.get(position));

    }

    @Override
    public int getItemCount() {
        if (productFavorites == null) {
            return 0;
        } else {
            return productFavorites.size();
        }
    }




}
