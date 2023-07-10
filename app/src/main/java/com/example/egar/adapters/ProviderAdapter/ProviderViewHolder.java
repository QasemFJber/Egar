package com.example.egar.adapters.ProviderAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Provider;

import com.example.egar.databinding.ItemProviderBinding;
import com.example.egar.interfaces.ItemCallbackProvider;
import com.squareup.picasso.Picasso;

public class ProviderViewHolder extends RecyclerView.ViewHolder {

    ItemProviderBinding binding;
    ItemCallbackProvider callbackProvider;

    public ProviderViewHolder(ItemProviderBinding binding,ItemCallbackProvider callbackProvider) {
        super(binding.getRoot());
        this.binding= binding;
        this.callbackProvider = callbackProvider;
    }

    public void savaData(Provider provider){
        //binding.imageProvider.setBackgroundResource(provider.getImage());
        binding.textNameProvider.setText(provider.getName());
        binding.textProviderType.setText(provider.getProviderType());
        binding.tvPhone.setText(provider.getPhoneNumber());
        Picasso.get().load(provider.getImage()).into(binding.imageProvider);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackProvider.onItemClick(provider);
            }
        });

    }
}
