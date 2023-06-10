package com.example.egar.adapters.ProviderAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egar.Models.Provider;
import com.example.egar.databinding.ItemProviderBinding;

public class ProviderViewHolder extends RecyclerView.ViewHolder {

    ItemProviderBinding binding;

    public ProviderViewHolder(ItemProviderBinding binding) {
        super(binding.getRoot());
        this.binding= binding;
    }

    public void savaData(Provider provider){
        //binding.imageProvider.setBackgroundResource(provider.);
        binding.textNameProvider.setText(provider.getName());
        binding.textProviderType.setText(provider.getProviderType());

    }
}
